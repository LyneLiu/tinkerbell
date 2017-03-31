package org.tinkerbell.security.auth.jwt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tinkerbell.security.service.UserDetailsServiceImpl;
import org.tinkerbell.security.util.TokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nn_liu on 2017/3/30.
 */

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 当用户JWT的token传输至service端的时候，解析token，并授权当前用户，实现JWT的认证、授权。
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = getToken( request );

        /**
         **************************************************
         *
         *  仅仅使用JWT认证的方式：将token保存至数据库，并且对比token是否超期、用户名是否一致等；
         *  如果不一致则不放行。
         *
         **************************************************
         */

        // get username from token
        String username = tokenUtil.getUserNameFromToken( authToken );
        if ( username != null && !"".equals(username)) {
            // get user
            UserDetails userDetails = userDetailsService.loadUserByUsername( username );
            // create authentication
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken( authToken );
            authentication.setAuthenticated( true );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        /**
         *  Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName( request, AUTH_COOKIE );
        if ( authCookie != null ) {
            return authCookie.getValue();
        }
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if ( authHeader != null && authHeader.startsWith("Tinkerbell ")) {
            return authHeader.substring(11);
        }

        return null;
    }

    private Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }
}
