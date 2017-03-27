package org.tinkerbell.security.entity.bo;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/13.
 */

public class UserBean {

    private String userName;
    private String password;
    private List<RoleBean> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBean> roles) {
        this.roles = roles;
    }

}
