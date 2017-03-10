package org.mauritius.tinkerbell_portal.common;

/**
 * Created by nn_liu on 2017/3/9.
 */
public class RegisterException extends RuntimeException {

    public RegisterException(String msg) {
        super(msg);
    }

    public RegisterException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
