package ru.itis.judgeassistant.security.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.judgeassistant.security.utils.AuthorizationTokenUtil;
import ru.itis.judgeassistant.security.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.itis.judgeassistant.security.filters.AuthenticationFilter.AUTHENTICATION_URL;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AuthorizationTokenUtil authorizationHeaderUtil;

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(request.getServletPath().equals(AUTHENTICATION_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        if(authorizationHeaderUtil.hasAuthorizationTokenInHeaders(request)) {
            String jwt = authorizationHeaderUtil.getTokenFromHeader(request);

            handleJwt(request, response, filterChain, jwt);

        } else if (session.getAttribute("auth") != null) {
            Authentication authentication = (Authentication) session.getAttribute("auth");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    private void handleJwt(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String jwt) throws IOException, ServletException {
        try {
            Authentication authentication = jwtUtil.buildAuthentication(jwt);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            logger.info(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
