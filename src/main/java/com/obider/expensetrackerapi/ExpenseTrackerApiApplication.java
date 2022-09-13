package com.obider.expensetrackerapi;

import com.obider.expensetrackerapi.middleware.AuthMiddleware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthMiddleware> filterRegistrationBean(){
		FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
		AuthMiddleware authMiddleware = new AuthMiddleware();
		registrationBean.setFilter(authMiddleware);
		registrationBean.addUrlPatterns("/api/categories/*");
		return registrationBean;

	}

}
