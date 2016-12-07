package com.premkumar.spring.boot_rest_apis;

import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.premkumar.spring.boot_rest_apis", exclude = { DispatcherServletAutoConfiguration.class })
@ImportResource(locations = { "classpath:cxf.xml" })
public class App {

	@Value(value="${swagger-ui.enabled}")
	private boolean swaggerEnabled;
	
	public static void main(String[] args) {
		System.setProperty("spring.profiles.default", "dev");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public Swagger2Feature swagger() {
		Swagger2Feature swagger = new Swagger2Feature();
		swagger.setContact("no-reply@asdf.blalba");
		swagger.setBasePath("/services");
		// swagger.setDynamicBasePath(true);
		swagger.setTitle("Spring Boot CXF rest APIs");
		swagger.setSupportSwaggerUi(swaggerEnabled);
		swagger.setDescription("an example of rest apis built with spring boot and apache cxf");
		System.out.println("in swagger() method");
		return swagger;
	}

}
