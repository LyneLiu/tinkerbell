package org.tinkerbell.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by nn_liu on 2017/3/30.
 */
public class TokenUtilTest {

    private TokenUtil tokenUtil;

    @Before
    public void init(){
        tokenUtil = new TokenUtil();
        ReflectionTestUtils.setField(tokenUtil, "expiresTimeMills", 1);
        ReflectionTestUtils.setField(tokenUtil, "secret", "luffy");
    }

    /**
     * Unexpected exception, expected<io.jsonwebtoken.ExpiredJwtException> but was<io.jsonwebtoken.SignatureException>
     */
    @Test(expected=ExpiredJwtException.class)
    public void testGenerateTokenExpired() {
        String token = tokenUtil.generateJsonWerbToken("lyne");
        Claims claims = Jwts.parser()
                .setSigningKey("mySecret")
                .parseClaimsJws(token)
                .getBody();
        Assert.assertEquals("lyne",claims.getSubject());
    }

}
