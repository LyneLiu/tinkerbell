package org.mauritius.tinkerbell_portal.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/13.
 */
@Getter
@Setter
public class UserBean {

    private String name;
    private String password;
    private List<RoleBean> roles;

}
