package ru.itis.judgeassistant.security.utils;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationTokenUtil {
    boolean hasAuthorizationTokenInHeaders(HttpServletRequest request);
    boolean hasAuthorizationTokenInCookies(HttpServletRequest request);

    String getTokenFromHeader(HttpServletRequest request);
    String getTokenFromCookie(HttpServletRequest request);
}
