package com.pkuerzer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.RundeSpieler;
import com.pkuerzer.domain.model.Spieler;
import com.pkuerzer.domain.repository.SpielRepository;
import com.pkuerzer.domain.repository.SpielerRepository;
import com.pkuerzer.exception.NumberOfSpielerException;
import com.pkuerzer.util.Util;

@Service
public class SpielService {
	
	private final SpielRepository spielRepository;
	private final SpielerRepository spielerRepository;
	private final RundeService rundeService;
	
	@Autowired
	public SpielService(SpielRepository spielRepository, SpielerRepository spielerRepository, RundeService rundeService) {
		super();
		this.spielRepository = spielRepository;
		this.spielerRepository = spielerRepository;
		this.rundeService = rundeService;
	}
	
	public Runde createSpielIncludingSpieler(Map<String, String> allRequestParams) throws NumberOfSpielerException{
		Runde runde0 = new Runde(new Date(), 2);
		runde0 = rundeService.getRundeRepository().save(runde0);
		List<RundeSpieler> rundeSpielers = new ArrayList<>();
		int position = 1;
		
		for(int i = 1; i < 7; i++){
			if(!Util.isInteger(allRequestParams.get("Spieler"+i), 10)){
				continue;
			}
			Spieler spieler = spielerRepository.findOne(Long.valueOf(allRequestParams.get("Spieler"+i)));
			RundeSpieler rundeSpieler = new RundeSpieler();
			rundeSpieler.setPosition(position++);
			rundeSpieler.setPunkte(15);
			rundeSpieler.setSpieler(spieler);
			rundeSpieler.setRunde(runde0);
			rundeSpielers.add(rundeSpieler);
		}
		
		runde0.setSpieler(rundeSpielers);
		
		if(runde0.getSpieler().size() < 3 || runde0.getSpieler().isEmpty() || runde0.getSpieler().size() > 6){
			throw new NumberOfSpielerException("Anzahl der Spieler ist nicht valide.") ;
		}
		
		return runde0;
		
	}
	
	public SpielRepository getSpielRepository() {
		return spielRepository;
	}

	public SpielerRepository getSpielerRepository() {
		return spielerRepository;
	}

	public RundeService getRundeService() {
		return rundeService;
	}
	
}
