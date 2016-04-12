package com.pkuerzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String spielerCreate(@ModelAttribute Spieler spieler, Model model){
		spielerService.saveSpieler(spieler);
		model.addAttribute("spielerList", spielerService.findAllSpieler());
		return "redirect:/spieler";
	}
	
	@RequestMapping(value="/spieler/delete", method=RequestMethod.POST)
	public String spielerDelete(){
		return "";
	}
}
