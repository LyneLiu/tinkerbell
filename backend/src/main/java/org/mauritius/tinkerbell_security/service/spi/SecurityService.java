package org.mauritius.tinkerbell_security.service.spi;

/**
 * Created by nn_liu on 2017/3/9.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);

}
