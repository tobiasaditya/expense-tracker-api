package com.obider.expensetrackerapi;

import com.obider.expensetrackerapi.middleware.AuthMiddleware;
import com.obider.expensetrackerapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApiApplication {
	private final UserService userService;

	@Autowired
	public ExpenseTrackerApiApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthMiddleware> filterRegistrationBean(){
		FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
		AuthMiddleware authMiddleware = new AuthMiddleware(userService);
		registrationBean.setFilter(authMiddleware);
		registrationBean.addUrlPatterns("/api/categories/*");
		return registrationBean;

	}

}
