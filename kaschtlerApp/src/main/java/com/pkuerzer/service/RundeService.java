package com.pkuerzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.Spiel;
import com.pkuerzer.domain.repository.RundeRepository;

@Service
public class RundeService {

	private final RundeRepository rundeRepository;
	
	@Autowired
	public RundeService(RundeRepository rundeRepository){
		this.rundeRepository = rundeRepository;
	}
	
	public Model initialRunde(Spiel spiel, Integer rundenNummer, Model model){
		Runde runde = rundeRepository.findBySpielIdAndRundenNummer(spiel.getId(), rundenNummer);
		model.addAttribute("runde", runde);
		model.addAttribute("spielerList", runde.getSpieler());
		
		return model;
	}
	
	public Model normalRunde(Spiel spiel, Integer rundenNummer, Model model){
		
		return model;
	}

	public RundeRepository getRundeRepository() {
		return rundeRepository;
	}
	
	
}
