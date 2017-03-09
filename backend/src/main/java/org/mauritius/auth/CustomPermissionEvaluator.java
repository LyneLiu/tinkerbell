package org.mauritius.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by nn_liu on 2017/3/9.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(CustomPermissionEvaluator.class);


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        logger.info("hasPermission info");
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {

        logger.info("hasPermission info");
        return false;
    }
}
