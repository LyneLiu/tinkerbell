package org.tinkerbell.common;

/**
 * Created by nn_liu on 2017/4/5.
 */
public enum HealthResultEnum {

    HEALTHY(0, "healthy"), LOCAL_ERROR(-1, "local healthy check error"), DNS_ERROR(-2, "dns healthy check error");

    private int code;

    private String codeDesc;

    HealthResultEnum(int code, String codeDesc) {
        this.code = code;
        this.codeDesc =codeDesc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
