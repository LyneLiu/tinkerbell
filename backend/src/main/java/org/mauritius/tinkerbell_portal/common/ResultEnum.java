package org.mauritius.tinkerbell_portal.common;

/**
 * Created by nn_liu on 2017/3/9.
 */
public enum ResultEnum {

    SUCCESS("success"),FAILURE("failure");

    private String value;

    ResultEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
