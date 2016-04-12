package com.pkuerzer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkuerzer.domain.model.Spieler;
import com.pkuerzer.domain.repository.SpielerRepository;

@Service
public class SpielerServive {
	
	private final SpielerRepository spielerRepository;
	
	@Autowired
	public SpielerServive(SpielerRepository spielerRepository){
		this.spielerRepository = spielerRepository;
	}

	public Spieler saveSpieler(Spieler spieler){
		return spielerRepository.save(spieler);
	}
	
	public List<Spieler> findAllSpieler(){
		return spielerRepository.findAll();
	}
	
	public void deleteSpieler(Spieler spieler){
		spielerRepository.delete(spieler);
	}

	public SpielerRepository getSpielerRepository() {
		return spielerRepository;
	}
}
