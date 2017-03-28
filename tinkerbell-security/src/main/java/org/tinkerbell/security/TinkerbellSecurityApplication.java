package org.tinkerbell.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.tinkerbell.security.config.SecurityJPAManager;

/**
 * Created by nn_liu on 2017/3/24.
 */

@SpringBootApplication
@Import(value = {SecurityJPAManager.class})
@ComponentScan(basePackages = "org.tinkerbell")
@EnableAutoConfiguration
public class TinkerbellSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkerbellSecurityApplication.class, args);
    }

}
