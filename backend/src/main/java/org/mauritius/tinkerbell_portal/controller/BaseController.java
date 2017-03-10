package org.mauritius.tinkerbell_portal.controller;

import org.mauritius.tinkerbell_portal.common.RegisterException;
import org.mauritius.tinkerbell_portal.common.ResultEnum;
import org.mauritius.tinkerbell_portal.common.UserValidator;
import org.mauritius.tinkerbell_security.entity.po.AuthUser;
import org.mauritius.tinkerbell_security.service.spi.SecurityService;
import org.mauritius.tinkerbell_security.service.spi.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nn_liu on 2017/2/24.
 */

/**
 * @RestController 和 @Controller的区别：
 * 1、@RestController = @ResponseBody + @Controller；
 * 2、只有使用@Controller和视图解析器InternalResourceViewResolver，才可以实现模板渲染；
 * 3、@ResponseBody会返回JSON，XML或自定义mediaType内容到页面。
 */
@RestController
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestBody AuthUser authUser) {

        try {
            BindingResult bindingResult = new BeanPropertyBindingResult(authUser,"binding_object");
            /*userValidator.validate(authUser, bindingResult);

            if (bindingResult.hasErrors()) {
                logger.error(bindingResult.getFieldError().toString());
                return ResultEnum.FAILURE.getValue();
            }

            userService.save(authUser);*/

            securityService.autologin(authUser.getUserName(), authUser.getPasswordConfirm());

            securityService.findLoggedInUsername();

            return ResultEnum.SUCCESS.getValue();
        }catch (RegisterException e){
            logger.error("register error:",e);
            return ResultEnum.FAILURE.getValue();
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
