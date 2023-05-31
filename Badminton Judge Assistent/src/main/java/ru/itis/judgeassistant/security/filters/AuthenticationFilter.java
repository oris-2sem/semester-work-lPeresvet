package ru.itis.judgeassistant.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.models.users.User;
import ru.itis.judgeassistant.security.authentication.RefreshTokenAuthentication;
import ru.itis.judgeassistant.security.details.UserDetailsImpl;
import ru.itis.judgeassistant.security.utils.AuthorizationTokenUtil;
import ru.itis.judgeassistant.security.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String USERNAME_PARAMETER = "login";

    public static final String AUTHENTICATION_URL = "/auth/token";

    private final ObjectMapper objectMapper;

    private final JwtUtil jwtUtil;

    private final AuthorizationTokenUtil authorizationHeaderUtil;


    public AuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                JwtUtil jwtUtil,
                                ObjectMapper objectMapper,
                                AuthorizationTokenUtil authorizationHeaderUtil) throws Exception{
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.authorizationHeaderUtil = authorizationHeaderUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (authorizationHeaderUtil.hasAuthorizationTokenInHeaders(request)) {
            String refreshToken = authorizationHeaderUtil.getTokenFromHeader(request);

            RefreshTokenAuthentication authentication = new RefreshTokenAuthentication(refreshToken);

            return super.getAuthenticationManager().authenticate(authentication);

        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType("application/json");
        GrantedAuthority authority = authResult.getAuthorities().stream().findFirst().orElseThrow();

        String login = ((UserDetailsImpl)authResult.getPrincipal()).getUsername();
        String issuer = request.getRequestURI();
        addAuthToSession(request, authority, login);

        Map<String, String> tokens = jwtUtil.generateTokens(login, authority.toString(), issuer);

        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    private void addAuthToSession(HttpServletRequest request, GrantedAuthority authority, String login) {
        HttpSession session = request.getSession();

        UserDetails userDetails = new UserDetailsImpl(
                User.builder()
                        .role(User.Role.valueOf(authority.getAuthority()))
                        .login(login)
                        .build());


        session.setAttribute("auth", new UsernamePasswordAuthenticationToken(userDetails, null,
                Collections.singleton(authority)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
