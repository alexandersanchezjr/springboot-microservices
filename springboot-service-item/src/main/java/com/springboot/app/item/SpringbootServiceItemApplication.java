package com.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringbootServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceItemApplication.class, args);
	}

}
