package com.banco.futuro.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages="com.banco.futuro.app.repositorio")
@EntityScan(basePackages = {"com.banco.futuro.app.modelo"})
public class BancoFuturoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoFuturoApplication.class, args);
	}

}
