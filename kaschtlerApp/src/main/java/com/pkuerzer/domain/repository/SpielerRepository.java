package com.pkuerzer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkuerzer.domain.model.Spieler;

@Repository
public interface SpielerRepository extends JpaRepository<Spieler, Long>{

}
