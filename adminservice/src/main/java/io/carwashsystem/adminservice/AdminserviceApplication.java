package io.carwashsystem.adminservice;



import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.carwashsystem.adminservice.service.AdminServiceImpl;
import io.carwashsystem.adminservice.service.RatingServiceImpl;
import io.carwashsystem.adminservice.service.WashPackServiceImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AdminserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminserviceApplication.class, args);
	}
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("io.carwashsystem.adminservice.controller"))
	      .paths(PathSelectors.ant("/admin/*"))
	      .build()
	      .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "On Demand Car Wash System", 
	      "Case Study Post My Training", 
	      "satya", 
	      "Terms of service", 
	      new Contact("satya", "www.example.com", "qwerty@company.com"), 
	      "Free to use", "API license URL", Collections.emptyList());
	}
	@Bean
	public AdminServiceImpl getadminservice(){
		return new AdminServiceImpl();
	}
	@Bean
	public RatingServiceImpl getratingservice(){
		return new RatingServiceImpl();
	}
	@Bean
	public WashPackServiceImpl getwashpackservice()
	{
		return new WashPackServiceImpl();
	}

}
