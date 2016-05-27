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
import com.pkuerzer.domain.repository.RundeRepository;
import com.pkuerzer.domain.repository.SpielRepository;

@Service
public class RundeService {

	private final RundeRepository rundeRepository;
	private final SpielRepository spielRepository;
	private final PunkteService punkteService;
	
	@Autowired
	public RundeService(RundeRepository rundeRepository, SpielRepository spielRepository, PunkteService punkteService){
		this.rundeRepository = rundeRepository;
		this.spielRepository = spielRepository;
		this.punkteService = punkteService;
	}
	
	public boolean checkIfGameIsFinished(Spiel spiel, final Integer rundenNummer){
		if(spiel.getCurrentRound(rundenNummer).isOnePlayerWinner()){
			spiel.setFinished(true);
			return true;
		}
		if(spiel.getCurrentRound(rundenNummer).isLastRound() || spiel.getCurrentRound(rundenNummer).isOnePlayerOver100()){
			spiel.setFinished(true);
			punkteService.calculatePointsIfGameEnds(spiel, rundenNummer);
			return true;
		}
		return false;
	}
	
	public void endRunde(Spiel spiel, final Integer rundenNummer){
		Runde currentRound = spiel.getCurrentRound(rundenNummer);
		final Integer multiplication = getMultiplication(currentRound);
		punkteService.calculatePointsForEveryPlayer(spiel, rundenNummer, multiplication);
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
	
	public RundeRepository getRundeRepository() {
		return rundeRepository;
	}

	public SpielRepository getSpielRepository() {
		return spielRepository;
	}

	public PunkteService getPunkteService() {
		return punkteService;
	}
	
	
}
