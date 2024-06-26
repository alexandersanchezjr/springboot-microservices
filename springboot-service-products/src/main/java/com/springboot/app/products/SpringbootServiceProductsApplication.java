package com.springboot.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.springboot.app.commons.model.entity"})
public class SpringbootServiceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceProductsApplication.class, args);
	}

}
