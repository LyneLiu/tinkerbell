package org.tinkerbell.security.auth.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by nn_liu on 2017/3/30.
 */
public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;

    private UserDetails principle;

    public TokenBasedAuthentication(UserDetails principle) {
        super(principle.getAuthorities());
        this.principle = principle;
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
