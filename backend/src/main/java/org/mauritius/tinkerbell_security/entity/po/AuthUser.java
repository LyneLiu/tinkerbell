package org.mauritius.tinkerbell_security.entity.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * 参考链接：
 * https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany
 * https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 *
 * Created by nn_liu on 2017/3/8.
 */


@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "passwordconfirm")
    private String passwordConfirm;

    @Column(name = "description")
    private String description;

    @Column(name = "datachange_lasttime")
    private Timestamp dataChange_LastTime;

    //关系维护端，负责多对多关系的绑定和解除
    //@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(AuthUser)
    //inverseJoinColumns指定外键的名字，要关联的关系被维护端(AuthRole)
    //其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //比如表名：user_role
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即userid
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即roleid
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    @ManyToMany
    @JoinTable(name="auth_user_role",joinColumns=@JoinColumn(name="userid"),
            inverseJoinColumns=@JoinColumn(name="roleid"))
    private List<AuthRole> authRoles;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public List<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(List<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }
}
