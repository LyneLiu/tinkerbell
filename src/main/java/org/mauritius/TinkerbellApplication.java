package org.mauritius;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class TinkerbellApplication {

	private static ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TinkerbellApplication.class, args);
		springContext = context;
	}

	/**
	 * 获取当前应用的context
	 * @return
     */
	public static ConfigurableApplicationContext getContext(){
		return springContext;
	}

}
