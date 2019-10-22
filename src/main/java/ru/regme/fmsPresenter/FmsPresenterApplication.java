package ru.regme.fmsPresenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FmsPresenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmsPresenterApplication.class, args);
	}

}
