package com.eventoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.eventoapp"})
//@EnableJpaRepositories("com.eventoapp.repository")
//@EnableAutoConfiguration
//@EntityScan("com.eventoapp.models")
public class EventoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoappApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
