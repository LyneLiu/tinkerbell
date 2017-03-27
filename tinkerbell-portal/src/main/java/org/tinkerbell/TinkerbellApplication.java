package org.tinkerbell;

import org.tinkerbell.config.TinkerbellJPAManager;
import org.tinkerbell.config.SpringDemoJPAManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @Import注解导入依赖等信息；
 * @ComponentScan指定扫描路径。
 */
@SpringBootApplication
@Import(value = {SpringDemoJPAManager.class, TinkerbellJPAManager.class})
@ComponentScan(basePackages = "org.tinkerbell")
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

	/**
	 * UTF-8编码
	 * @return
     */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setForceEncoding(true);
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

}
