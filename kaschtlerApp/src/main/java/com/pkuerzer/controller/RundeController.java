package com.pkuerzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pkuerzer.domain.model.Runde;
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
	public String currentRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model){
		model.addAttribute("spiel", spielService.getSpielRepository().findOne(spielId));
		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spielId, rundenNummer);
		model.addAttribute("runde", runde);
		
		return "spiel/spielRunning";
	}


	public SpielService getSpielService() {
		return spielService;
	}


	public RundeService getRundeService() {
		return rundeService;
	}
	
	
}
