package com.springboot.app.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.springboot.app.commons.user.models.entity"})
public class SpringbootServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceUserApplication.class, args);
	}

}
