package com.pkuerzer.service;

import org.springframework.stereotype.Service;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.RundeSpieler;
import com.pkuerzer.domain.model.Spiel;

@Service
public class PunkteService {

	public void calculatePointsForEveryPlayer(Spiel spiel, final Integer rundenNummer, final Integer multiplication){
		
		spiel.getCurrentRound(rundenNummer).getSpieler().stream().forEach(spieler -> {
			if(spieler.getPunkte() == null){
				spieler.setPunkte(0);
			}
			if(spieler.isGegangen()){
				spieler.setPunkte(spiel.getPreviousRound(rundenNummer).getSpieler().stream()
						.filter(spieler2 -> spieler2.getSpieler().getId() == spieler.getSpieler().getId())
						.findFirst().get().getPunkte() + (2 * multiplication));
			}
			else if(spieler.isZerrissen()){
				spieler.setPunkte(spiel.getPreviousRound(rundenNummer).getSpieler().stream()
						.filter(spieler2 -> spieler2.getSpieler().getId() == spieler.getSpieler().getId())
						.findFirst().get().getPunkte() + (10 * multiplication));
			}
			else if(spieler.getPunkte() == 0){
				spieler.setPunkte(spiel.getPreviousRound(rundenNummer).getSpieler().stream()
						.filter(spieler2 -> spieler2.getSpieler().getId() == spieler.getSpieler().getId())
						.findFirst().get().getPunkte() + (5 * multiplication));
			}
			else{
				spieler.setPunkte(spiel.getPreviousRound(rundenNummer).getSpieler().stream()
						.filter(spieler2 -> spieler2.getSpieler().getId() == spieler.getSpieler().getId())
						.findFirst().get().getPunkte() - (spieler.getPunkte() * multiplication));
			}
		});
		
	}
	
	public void setPointsToZero(Runde runde){
		runde.getSpieler().stream().forEach(spieler -> {
			if(spieler.getPunkte() < 0){
				spieler.setPunkte(0);
			}
		});
	}
	
	public void calculatePointsIfGameEnds(Spiel spiel, final Integer rundenNummer){
		int minPoints = 10000;
		for(RundeSpieler spieler : spiel.getCurrentRound(rundenNummer).getSpieler()){
			if(minPoints >= spieler.getPunkte()){
				minPoints = spieler.getPunkte();
			}
		}
		
		for(RundeSpieler spieler : spiel.getCurrentRound(rundenNummer).getSpieler()){
			spieler.setPunkte(spieler.getPunkte() - minPoints);
		}
		
	}
}
