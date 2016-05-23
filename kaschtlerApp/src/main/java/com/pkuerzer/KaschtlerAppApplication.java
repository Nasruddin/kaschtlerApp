package com.pkuerzer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KaschtlerAppApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(KaschtlerAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
}
