package org.mauritius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by nn_liu on 2017/2/24.
 */

/**
 * @RestController 和 @Controller的区别：
 * 1、@RestController = @ResponseBody + @Controller；
 * 2、只有使用@Controller和视图解析器InternalResourceViewResolver，才可以实现模板渲染；
 * 3、@ResponseBody会返回JSON，XML或自定义mediaType内容到页面。
 */
@Controller
public class BaseController {

    @RequestMapping("/home")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }

    @RequestMapping("/resource")
    @ResponseBody
    public Map<String,Object> home(){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content","Hello World");

        return model;
    }

}
