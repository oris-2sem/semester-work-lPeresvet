package ru.itis.judgeassistant.security.utils.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.models.users.User;
import ru.itis.judgeassistant.security.details.UserDetailsImpl;
import ru.itis.judgeassistant.security.utils.JwtUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtilAuth0Impl implements JwtUtil {
    private static final long ACCESS_TOKEN_EXPIRES_TIME = 2 * 60 * 1000;

    private static final long REFRESH_TOKEN_EXPIRES_TIME = 30 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secretSign;

    @Override
    public Map<String, String> generateTokens(String subject, String authority, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(secretSign.getBytes(StandardCharsets.UTF_8));

        String accessToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES_TIME))
                .withClaim("role", authority)
                .withIssuer(issuer)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES_TIME))
                .withClaim("role", authority)
                .withIssuer(issuer)
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;

    }

    @Override
    public Authentication buildAuthentication(String token) throws JWTVerificationException {
        ParsedToken parsedToken = parse(token);

        UserDetails userDetails = new UserDetailsImpl(
                User.builder()
                        .role(User.Role.valueOf(parsedToken.getRole()))
                        .login(parsedToken.getLogin())
                        .build());

        return new UsernamePasswordAuthenticationToken(userDetails, null,
                Collections.singleton(new SimpleGrantedAuthority(parsedToken.getRole())));

    }

    private ParsedToken parse(String token) throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(secretSign.getBytes(StandardCharsets.UTF_8));

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        String login = decodedJWT.getSubject();

        String role = decodedJWT.getClaim("role").asString();

        return ParsedToken.builder()
                .login(login)
                .role(role)
                .build();
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ParsedToken {
        private String login;
        private String role;
    }

}
