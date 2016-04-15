package com.pkuerzer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkuerzer.domain.model.Runde;

@Repository
public interface RundeRepository extends JpaRepository<Runde, Long>{
	
	public Runde findBySpielIdAndRundenNummer(Long spielId, Integer rundenNummer);

}
