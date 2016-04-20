package com.pkuerzer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.RundeSpieler;
import com.pkuerzer.domain.model.Spiel;
import com.pkuerzer.domain.model.Spieler;
import com.pkuerzer.domain.repository.RundeRepository;
import com.pkuerzer.domain.repository.SpielRepository;

@Service
public class RundeService {

	private final RundeRepository rundeRepository;
	private final SpielRepository spielRepository;
	
	@Autowired
	public RundeService(RundeRepository rundeRepository, SpielRepository spielRepository){
		this.rundeRepository = rundeRepository;
		this.spielRepository = spielRepository;
	}
	
	public void endRunde(Spiel spiel, Runde runde){
		Runde previousRunde = rundeRepository.findBySpielIdAndRundenNummer(spiel.getId(), runde.getRundenNummer() -1);
		List<RundeSpieler> previousRundeSpieler = previousRunde.getSpieler();
		
		for(RundeSpieler prevoisRundeSpieler : previousRundeSpieler){
			
		}
		
	}
	
	public Runde createNewRunde(Spiel spiel, Integer rundenNummer){
		spiel = spielRepository.findOne(spiel.getId());
		List<RundeSpieler> rundeSpielerList = spiel.getRunden().iterator().next().getSpieler();
		List<RundeSpieler> newRundeSpielerList = new ArrayList<>();
		
		Runde runde = new Runde(new Date(), 1);
		runde.setRundenNummer(rundenNummer);
		
		if(rundenNummer == 1){
			runde.setMultiplikation(2);
		}
		
		for(RundeSpieler rundeSpieler : rundeSpielerList){
			RundeSpieler newRundeSpieler = new RundeSpieler();
			newRundeSpieler.setPosition(rundeSpieler.getPosition());
			newRundeSpieler.setRunde(runde);
			newRundeSpieler.setSpieler(rundeSpieler.getSpieler());
			
			newRundeSpielerList.add(newRundeSpieler);
		}
		
		runde.setSpieler(newRundeSpielerList);
		runde = rundeRepository.save(runde);
		
		return runde;
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

	public SpielRepository getSpielRepository() {
		return spielRepository;
	}
	
	
}
