package com.pkuerzer.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pkuerzer.domain.model.Spiel;
import com.pkuerzer.service.SpielService;

@Controller
public class SpielController {
	
	private final SpielService spielService;
	
	@Autowired
	public SpielController(SpielService spielService) {
		super();
		this.spielService = spielService;
	}

	@RequestMapping("/spiel")
	public String spielIndex(Model model){
		return "spiel/spielIndex";
	}
	
	@RequestMapping("spiel/{id}")
	public String spiel(@PathVariable Long id, Model model){
		Spiel spiel = spielService.getSpielRepository().findOne(id);
		model.addAttribute("spiel", spiel);
		return "spiel/spielIndex";
	}
	
	@RequestMapping("/spiel/create")
	public String spielCreate(Model model){
		Spiel spiel = new Spiel(new Date());
		spiel = spielService.getSpielRepository().save(spiel);
		model.addAttribute("spiel", spiel);
		return "redirect:spiel/" + spiel.getId();
	}
	
	public SpielService getSpielService() {
		return spielService;
	}
}
