package org.mauritius.tinkerbell_security.auth;

import org.mauritius.tinkerbell_security.repository.AuthRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConsumerSPELPermissionValidator {

  private static final Logger logger = LoggerFactory.getLogger(ConsumerSPELPermissionValidator.class);

  @Autowired
  private AuthRoleRepository authRoleRepository;

  public boolean hasDescPermission(String roleName, String desc) {

    try{
      if (desc.equalsIgnoreCase(authRoleRepository.findByRoleName(roleName).getDescription())){
        return true;
      }
    }catch (Exception e){
      logger.error("No Desc Permission error:",e);
      return false;
    }
    return false;

  }

}
