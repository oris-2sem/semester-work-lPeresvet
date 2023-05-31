package ru.itis.judgeassistant.security.utils.impl;

import org.springframework.stereotype.Component;
import ru.itis.judgeassistant.security.utils.AuthorizationTokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class RequestUtilImpl implements AuthorizationTokenUtil {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_COOKIE_NAME = "jwt";
    private static final String COOKIE_BEARER = "JWT ";

    @Override
    public boolean hasAuthorizationTokenInHeaders(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public boolean hasAuthorizationTokenInCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTHORIZATION_COOKIE_NAME)) {
                return cookie.getValue().startsWith(COOKIE_BEARER);
            }
        }
        return false;
    }

    @Override
    public String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header.substring(BEARER.length());
    }

    @Override
    public String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTHORIZATION_COOKIE_NAME)) {
                return cookie.getValue().substring(COOKIE_BEARER.length());
            }
        }
        return null;
    }
}
