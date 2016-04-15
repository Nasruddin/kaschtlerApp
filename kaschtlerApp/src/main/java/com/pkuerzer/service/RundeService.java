package com.pkuerzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkuerzer.domain.repository.RundeRepository;

@Service
public class RundeService {

	private final RundeRepository rundeRepository;
	
	@Autowired
	public RundeService(RundeRepository rundeRepository){
		this.rundeRepository = rundeRepository;
	}

	public RundeRepository getRundeRepository() {
		return rundeRepository;
	}
	
	
}
