package org.tinkerbell.security.auth.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nn_liu on 2017/3/30.
 */

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // This is invoked when user tries to access a secured REST resource without supplying any credentials
    // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
