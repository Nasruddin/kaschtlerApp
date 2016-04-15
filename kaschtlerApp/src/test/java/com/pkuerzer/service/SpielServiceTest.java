package com.pkuerzer.service;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Splitter;
import com.pkuerzer.KaschtlerAppApplication;
import com.pkuerzer.domain.model.Runde;
import com.pkuerzer.exception.NumberOfSpielerException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KaschtlerAppApplication.class)
public class SpielServiceTest {

	@Autowired
	SpielService spielService;
	
	@Test
	public void testCreateSpielIncludingSpieler() throws NumberOfSpielerException {
		Map<String, String> requestParam = splitToMap("Spieler1=243, Spieler2=-, Spieler3=199, Spieler4=245, Spieler5=-, Spieler6=-");
		Runde runde = spielService.createSpielIncludingSpieler(requestParam);
		Assert.assertEquals(3, runde.getSpieler().size());
		Assert.assertEquals(1, runde.getSpieler().get(0).getPosition().intValue());
		Assert.assertEquals(2, runde.getSpieler().get(1).getPosition().intValue());
		Assert.assertEquals(3, runde.getSpieler().get(2).getPosition().intValue());
	}
	
	private Map<String, String> splitToMap(String s){
		return Splitter.on(", ").withKeyValueSeparator("=").split(s);
	}

}
