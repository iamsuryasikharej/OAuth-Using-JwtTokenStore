package com.example.resserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan({"com.example.resserver.contollers","com.example.resserver.resserverconfig"})
public class ResserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResserverApplication.class, args);
	}

}
