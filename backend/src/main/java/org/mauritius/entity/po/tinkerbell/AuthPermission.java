package org.mauritius.entity.po.tinkerbell;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by nn_liu on 2017/3/9.
 */

@Entity
@Table(name = "auth_perm")
public class AuthPermission {

    @Id
    @Column(name = "permissionid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer permissionId;

    @Column(name = "permissionname")
    private String permissionName;

    @Column(name = "description")
    private String description;

    @Column(name = "datachange_lasttime")
    private Timestamp dataChange_LastTime;

    @ManyToMany(mappedBy = "authPerms")
    private Set<AuthRole> authRoles;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDataChange_LastTime() {
        return dataChange_LastTime;
    }

    public void setDataChange_LastTime(Timestamp dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }

    public Set<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Set<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }
}
