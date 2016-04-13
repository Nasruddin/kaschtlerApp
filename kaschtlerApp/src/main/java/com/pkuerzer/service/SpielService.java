package com.pkuerzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkuerzer.domain.repository.SpielRepository;

@Service
public class SpielService {
	
	private final SpielRepository spielRepository;
	
	@Autowired
	public SpielService(SpielRepository spielRepository) {
		super();
		this.spielRepository = spielRepository;
	}


	public SpielRepository getSpielRepository() {
		return spielRepository;
	}

}
