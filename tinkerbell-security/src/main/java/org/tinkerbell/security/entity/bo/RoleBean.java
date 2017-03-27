package org.tinkerbell.security.entity.bo;


import java.util.List;

/**
 * Created by nn_liu on 2017/3/15.
 */
public class RoleBean {

    private String roleName;
    private List<PermBean> Perms;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<PermBean> getPerms() {
        return Perms;
    }

    public void setPerms(List<PermBean> perms) {
        Perms = perms;
    }
}
