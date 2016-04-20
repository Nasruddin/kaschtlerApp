package com.pkuerzer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.Spiel;
import com.pkuerzer.service.RundeService;
import com.pkuerzer.service.SpielService;

@Controller
public class RundeController {
	
	private final SpielService spielService;
	private final RundeService rundeService;
	
	@Autowired
	public RundeController(SpielService spielService, RundeService rundeService) {
		this.spielService = spielService;
		this.rundeService = rundeService;
	}

	
	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}", method=RequestMethod.GET)
	public String currentRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model){
		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spiel.getId(), rundenNummer);
		
		model.addAttribute("spiel", spiel);
		model.addAttribute("runde", runde);
		model.addAttribute("spielerList", runde.getSpieler());
		model.addAttribute("rundenList", spiel.getRunden());
		
		return "spiel/spielRunning";
	}
	
	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}", method=RequestMethod.POST)
	public String endRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model, @RequestParam Map<String,String> allRequestParams){
		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spiel.getId(), rundenNummer);
		
		model.addAttribute("spiel", spiel);
		model.addAttribute("runde", runde);
		model.addAttribute("spielerList", runde.getSpieler());
		model.addAttribute("rundenList", spiel.getRunden());
		
		return "spiel/spielRunning";
	}

	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}/doppelt")
	public String doubleRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model){
		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spielId, rundenNummer);
		if(runde == null){
			runde = rundeService.createNewRunde(spiel, rundenNummer);
			if(rundenNummer == 1){
				runde.setMultiplikation(2);
			}
		}
		
		if(runde.getMultiplikation() < 32){
			runde.setMultiplikation(runde.getMultiplikation() * 2);
		}
		
		spiel.getRunden().add(runde);
		spiel = spielService.getSpielRepository().save(spiel);
		
		return "redirect:/spiel/" + spiel.getId() + "/runde/" + runde.getRundenNummer();
	}
	
	


	public SpielService getSpielService() {
		return spielService;
	}


	public RundeService getRundeService() {
		return rundeService;
	}
	
	
}
