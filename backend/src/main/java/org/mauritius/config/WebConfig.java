package org.mauritius.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring4.2版本以后可以使用注解@CrossOrigin
 *
 * Created by nn_liu on 2017/3/3.
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedHeaders("Access-Control-Allow-Origin");
    }
}
