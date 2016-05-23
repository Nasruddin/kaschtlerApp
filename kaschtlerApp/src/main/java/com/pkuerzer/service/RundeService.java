package com.pkuerzer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.RundeSpieler;
import com.pkuerzer.domain.model.Spiel;
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
	
	public void endRunde(Spiel spiel, Integer rundenNummer){
		
		
		
		
		Runde currentRound = getCurrentRound(spiel, rundenNummer);
		final Integer multiplication = getMultiplication(currentRound);
		
		calculatePoints(currentRound, getPreviousRound(spiel, rundenNummer));
	}
	
	private void calculatePoints(Runde currentRound, Runde previousRound){
		currentRound.getSpieler().stream().forEach(spieler -> {
			
		});
	}
	
	private Integer getMultiplication(Runde currentRound){
		Integer muliplication = currentRound.getMultiplikation();
		if(currentRound.isHerz()){
			muliplication = muliplication * 2;
		}
		if(currentRound.isMuli()){
			muliplication = muliplication * 2;
		}
		return muliplication;
	}
	
	public Runde endRunde(Spiel spiel, Runde runde, Map<String,String> allRequestParams){
		Runde previousRunde = rundeRepository.findBySpielIdAndRundenNummer(spiel.getId(), runde.getRundenNummer() -1);
		List<RundeSpieler> previousRundeSpielerList = previousRunde.getSpieler();
		
		for(RundeSpieler previousRundeSpieler : previousRundeSpielerList){
			Integer punkte = Integer.parseInt(allRequestParams.get(previousRundeSpieler.getSpieler().getId().toString()));
			for(RundeSpieler rundeSpieler : runde.getSpieler()){
				if(rundeSpieler.getSpieler().getId() == previousRundeSpieler.getSpieler().getId()){
					rundeSpieler.setPunkte(calculatePunkte(runde, punkte, previousRundeSpieler.getPunkte()));
				}
			}
		}
		return runde;
	}
	
	public Runde getCurrentRound(Spiel spiel, Integer rundenNummer){
		return spiel.getRunden().stream().filter(runde -> runde.getRundenNummer() == rundenNummer).findFirst().get();
	}
	
	public Runde getPreviousRound(Spiel spiel, Integer rundenNummer){
		return spiel.getRunden().stream().filter(runde -> runde.getRundenNummer() == rundenNummer -1).findFirst().get();
	}
	
	private Integer calculatePunkte(Runde runde, Integer punkte, Integer previousPunkte){
		int multiplikatorHerzMuli = 1;
		if(runde.isHerz()){
			multiplikatorHerzMuli = multiplikatorHerzMuli * 2;
		}
		if(runde.isMuli()){
			multiplikatorHerzMuli = multiplikatorHerzMuli * 2;
		}
		punkte = runde.getMultiplikation() * punkte * multiplikatorHerzMuli;
		return previousPunkte - punkte;
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
