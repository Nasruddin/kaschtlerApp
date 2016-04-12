package com.pkuerzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pkuerzer.domain.repository.SpielRepository;

@SpringBootApplication
public class KaschtlerAppApplication implements CommandLineRunner{
	
	@Autowired
	SpielRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(KaschtlerAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
