package org.tinkerbell.controller;

import org.tinkerbell.entity.vo.UserInfo;
import org.tinkerbell.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@RequestBody UserInfo userInfo) {
        return authService.register(userInfo);
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
