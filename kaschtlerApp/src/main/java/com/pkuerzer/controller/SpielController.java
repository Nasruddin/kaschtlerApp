package com.pkuerzer.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.domain.model.Spiel;
import com.pkuerzer.exception.NumberOfSpielerException;
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
		model.addAttribute("spielerList", spielService.getSpielerRepository().findAll());
		model.addAttribute("errorMessage", null);
		return "spiel/spielIndex";
	}
	
	@RequestMapping(value="spiel/{id}", method=RequestMethod.POST)
	public String spielSpielerSet(@PathVariable Long id, Model model, @RequestParam Map<String,String> allRequestParams){
		Spiel spiel = spielService.getSpielRepository().findOne(id);
		Set<Runde> runden = new HashSet<Runde>();
		Runde runde = null;
		try {
			runde = spielService.createSpielIncludingSpieler(allRequestParams);
			runden.add(runde);
			spiel.setRunden(runden);
			
			spielService.getSpielRepository().save(spiel);
			
		} catch (NumberOfSpielerException e) {
			model.addAttribute("spiel", spiel);
			model.addAttribute("spielerList", spielService.getSpielerRepository().findAll());
			model.addAttribute("errorMessage", e.getMessage());
			return "spiel/spielIndex";
		}
		return "redirect:/spiel/" + spiel.getId() + "/runde/" + runde.getRundenNummer() ;
	}
	
	
	@RequestMapping("/spiel/create")
	public String spielCreate(Model model){
		Spiel spiel = new Spiel(new Date());
		spiel = spielService.getSpielRepository().save(spiel);
		model.addAttribute("spiel", spiel);
		return "redirect:/spiel/" + spiel.getId();
	}
	
	public SpielService getSpielService() {
		return spielService;
	}
}
