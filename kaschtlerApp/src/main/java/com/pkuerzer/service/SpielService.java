package com.pkuerzer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.RundeSpieler;
import com.pkuerzer.domain.model.Spieler;
import com.pkuerzer.domain.repository.SpielRepository;
import com.pkuerzer.domain.repository.SpielerRepository;

@Service
public class SpielService {
	
	private final SpielRepository spielRepository;
	private final SpielerRepository spielerRepository;
	
	@Autowired
	public SpielService(SpielRepository spielRepository, SpielerRepository spielerRepository) {
		super();
		this.spielRepository = spielRepository;
		this.spielerRepository = spielerRepository;
	}
	
	public Runde createSpielIncludingSpieler(Map<String, String> allRequestParams){
		Runde runde0 = new Runde();
		List<RundeSpieler> rundeSpielers = new ArrayList<>();
		
		for(int i = 1; i < 7; i++){
			Spieler spieler = spielerRepository.findOne(Long.valueOf(allRequestParams.get("Spieler"+i)));
			RundeSpieler rundeSpieler = new RundeSpieler();
			rundeSpieler.setPosition(i);
			rundeSpieler.setPunkte(15);
			rundeSpieler.setSpieler(spieler);
			rundeSpieler.setRunde(runde0);
			rundeSpielers.add(rundeSpieler);
		}
		
		runde0.setSpieler(rundeSpielers);
		
		return runde0;
		
	}
	
	public SpielRepository getSpielRepository() {
		return spielRepository;
	}

	public SpielerRepository getSpielerRepository() {
		return spielerRepository;
	}
	
}
