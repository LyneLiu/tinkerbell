package org.tinkerbell.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/15.
 */
@Getter
@Setter
public class RoleBean {
    private String name;
    private List<PermBean> Perms;
}
