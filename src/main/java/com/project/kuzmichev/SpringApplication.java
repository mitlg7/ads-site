package com.project.kuzmichev;

import com.project.kuzmichev.service.email.EmailService;
import com.project.kuzmichev.service.email.EmailServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication {
	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

}
