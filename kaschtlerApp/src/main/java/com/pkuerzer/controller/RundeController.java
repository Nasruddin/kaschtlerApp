package com.pkuerzer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	
	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}")
	public String currentRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model, @RequestParam Map<String,String> allRequestParams){
		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
		model.addAttribute("spiel", spiel);
		if(rundenNummer == 0){
			model = rundeService.initialRunde(spiel, rundenNummer, model);
		} else{
			
		}
		
		
		
		
		model.addAttribute("rundenList", spiel.getRunden());
		
		return "spiel/spielRunning";
	}


	public SpielService getSpielService() {
		return spielService;
	}


	public RundeService getRundeService() {
		return rundeService;
	}
	
	
}
