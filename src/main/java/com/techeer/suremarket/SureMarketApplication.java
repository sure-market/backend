package com.techeer.suremarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SureMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SureMarketApplication.class, args);
	}

}
