package org.tinkerbell.controller.tinkerbell;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nn_liu on 2017/3/2.
 */
@RestController
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PreAuthorize("hasPermission(#request,#perm)")
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public String index(HttpServletRequest request, @RequestParam("perm") String perm) {
        return "index";
    }

    @PreAuthorize("@consumerSPELPermissionValidator.hasDescPermission(#roleName, #desc)")
    @RequestMapping(value = "/description", method = RequestMethod.POST)
    public String getDesc(@RequestParam("roleName") String roleName, @RequestParam("desc") String desc) {
        return "this is description";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> resource() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");

        return model;
    }

}
