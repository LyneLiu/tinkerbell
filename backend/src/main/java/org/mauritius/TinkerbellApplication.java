package org.mauritius;

import org.mauritius.tinkerbell_portal.config.SpringDemoJPAManager;
import org.mauritius.tinkerbell_portal.config.TinkerbellJPAManager;
import org.mauritius.tinkerbell_security.config.SecurityJPAManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Import注解导入依赖等信息；
 * @ComponentScan指定扫描路径。
 */
@SpringBootApplication
@Import(value = {SpringDemoJPAManager.class, TinkerbellJPAManager.class, SecurityJPAManager.class})
@ComponentScan
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
