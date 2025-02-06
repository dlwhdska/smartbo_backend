package com.bo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc 
public class MyMVCContext implements WebMvcConfigurer{
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver r = new InternalResourceViewResolver();
		r.setPrefix("/WEB-INF/view/");
		r.setSuffix(".jsp");
		return r;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedOrigins("http://localhost:5173")
				.allowedOrigins("http://192.168.1.12:5500")
				.allowedOrigins("http://192.168.1.91:5500")
				.allowedMethods("GET","POST","PUT","DELETE","OPTIONS"); 
	}
}
