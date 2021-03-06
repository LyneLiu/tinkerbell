package org.tinkerbell.security.auth;

import org.tinkerbell.security.entity.po.AuthPermission;
import org.tinkerbell.security.entity.po.AuthRole;
import org.tinkerbell.security.repository.AuthRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nn_liu on 2017/3/9.
 */
@Component
public class CustomerPermissionEvaluator implements PermissionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(CustomerPermissionEvaluator.class);

    @Autowired
    private AuthRoleRepository authRoleRepository;

    /**
     * authentication     :: 当前登录用户的授权信息
     * targetDomainObject :: 权限验证的Object信息，即hasPermission(#entiry,'edit')中的entity
     * permission         :: 权限验证的Permission信息，即hasPermission(#entiry,'edit')中的'edit'
     * @param authentication
     * @param targetDomainObject
     * @param permission
     * @return
     */
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

       try{
           Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
           //String requestURL = ((RequestFacade) targetDomainObject).getRequestURL().toString();

           for (GrantedAuthority auth : authorities) {
               String roleName = auth.getAuthority();
               AuthRole authRole = authRoleRepository.findByRoleName(roleName);
               List<AuthPermission> perms = authRole.getAuthPerms();
               List<String> permissions = new ArrayList<String>();
               for (AuthPermission perm : perms) {
                   permissions.add(perm.getPermissionName() == null? "":perm.getPermissionName());
               }
               if (permissions.contains(permission)){
                   return true;
               }
           }

       }catch (Exception e){
           logger.info("NO Permission!");
           return false;
       }

       return false;
    }

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {

        logger.info("No Permission!");
        return false;
    }

}
