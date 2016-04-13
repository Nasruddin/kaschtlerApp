package com.pkuerzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pkuerzer.domain.model.Spieler;
import com.pkuerzer.service.SpielerServive;

@Controller
public class SpielerController {
	
	private final SpielerServive spielerService;
	
	@Autowired
	public SpielerController(SpielerServive spielerService) {
		super();
		this.spielerService = spielerService;
	}

	@RequestMapping("/spieler")
	public String spielerIndex(Model model){
		model.addAttribute("spielerList", spielerService.findAllSpieler());
		model.addAttribute("spieler", new Spieler());
		return "spieler/spielerIndex";
	}

	@RequestMapping(value="/spieler/create", method=RequestMethod.POST)
	public String spielerCreate(@ModelAttribute Spieler spieler){
		spielerService.saveSpieler(spieler);
		return "redirect:/spieler";
	}
	
	@RequestMapping(value="/spieler/{id}", method=RequestMethod.GET)
	public String spiederEdit(@PathVariable Long id, Model model){
		Spieler spieler = spielerService.getSpielerRepository().findOne(id);
		model.addAttribute("spielerList", spielerService.findAllSpieler());
		model.addAttribute("spieler", spieler);
		return "spieler/spielerIndex";
	}
	
	
	
	@RequestMapping(value="/spieler/delete/{id}", method=RequestMethod.DELETE)
	public String spielerDelete(@PathVariable Long id){
		spielerService.deleteSpieler(id);
		return "redirect:/spieler";
	}
}
