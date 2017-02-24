package org.mauritius.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nn_liu on 2017/2/24.
 */

@RestController
public class BaseController {

    @RequestMapping("/index")
    public String index(){
        return "Index Page!";
    }

}
