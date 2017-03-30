package org.tinkerbell.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT token util
 * Created by nn_liu on 2017/3/30.
 */

@Component
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    @Value("${spring.application.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    /**
     * token中获取user name
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String userName;

        Claims claims = this.getClaimsFromToken(token);
        if (claims != null) {
            userName = claims.getSubject();
        } else {
            userName = "";
        }

        return userName;
    }


    /**
     * generate JWT token
     *
     * @param userName
     * @return
     */
    public String generateJsonWerbToken(String userName) {

        String jwt = Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(userName)
                .setIssuedAt(new Date(DateUtil.getCurrentTimeMills()))
                .setExpiration(new Date(DateUtil.getCurrentTimeMills() + EXPIRES_IN))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;

    }

    private Claims getClaimsFromToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.warn("parse token exception:", e);
            claims = null;
        }

        return claims;
    }

}
