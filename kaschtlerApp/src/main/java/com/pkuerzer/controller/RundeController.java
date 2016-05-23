package com.pkuerzer.controller;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("/test")
	public String test(Model model){
		Spiel spiel = spielService.getSpielRepository().findOne(45L);
		Runde runde1 = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spiel.getId(), 6);
		
		spiel.getRunden().stream().filter(runde -> runde.getRundenNummer() == 6).findFirst().get()
		.getSpieler().stream().forEach(spieler -> {
			spieler.setPunkte(spiel.getRunden().stream().filter(runde -> runde.getRundenNummer() == 5).findFirst().get()
				.getSpieler().stream()
				.filter(spieler1 -> spieler1.getSpieler().getId() == spieler.getSpieler().getId()).findFirst()
				.get().getPunkte() * 2);
		});
	
	spiel.getCreated();
		
		model.addAttribute("spiel", spiel);
		return "index";
	}
	@RequestMapping(value="/test1", method=RequestMethod.POST)
	public String test1(Model model, @ModelAttribute("spiel") Spiel spiel,
			@PathVariable Integer rundennummer, @PathVariable Long spielId){
		Runde runde = spiel.getRunden().stream().filter(s -> s.getRundenNummer() == rundennummer).findFirst().get();
		return "redirect:/test";
	}

	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}", method=RequestMethod.POST)
	public String endRunde(Model model, @PathVariable Long spielId, @PathVariable Integer rundenNummer,
			@ModelAttribute("spiel") Spiel spiel){
		rundeService.endRunde(spiel, rundenNummer);
		Runde newRunde = rundeService.createNewRunde(spiel, rundenNummer + 1);
		
		return "redirect:/spiel/" + spiel.getId() + "/runde/" + newRunde.getRundenNummer();
	}
	
	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}", method=RequestMethod.GET)
	public String currentRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model){
		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spiel.getId(), rundenNummer);
		
		model.addAttribute("spiel", spiel);
		model.addAttribute("rundenNummer", runde.getRundenNummer());
		
		return "spiel/spielRunning";
	}
	
//	@RequestMapping(value="/spiel/{spielId}/runde/{rundenNummer}", method=RequestMethod.POST)
//	public String endRunde(@PathVariable Long spielId, @PathVariable Integer rundenNummer, Model model, 
//			@RequestParam Map<String,String> allRequestParams, 
//			@RequestParam(value="herz", defaultValue="false") Boolean herz, 
//			@RequestParam(value="muli", defaultValue="false") Boolean muli,
//			@RequestParam(value="zerissen",required=false) Integer zerissenSpielerId){
//		Spiel spiel = spielService.getSpielRepository().findOne(spielId);
//		Runde runde = rundeService.getRundeRepository().findBySpielIdAndRundenNummer(spiel.getId(), rundenNummer);
//		runde.setHerz(herz);
//		runde.setMuli(muli);
//		runde = rundeService.endRunde(spiel, runde, allRequestParams);
//		
//		spiel.getRunden().add(runde);
//		spielService.getSpielRepository().save(spiel);
//		
//		Runde newRunde = rundeService.createNewRunde(spiel, rundenNummer + 1);
//		spiel.getRunden().add(newRunde);
//		spielService.getSpielRepository().save(spiel);
//		
//		return "redirect:/spiel/" + spiel.getId() + "/runde/" + newRunde.getRundenNummer();
//	}

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
