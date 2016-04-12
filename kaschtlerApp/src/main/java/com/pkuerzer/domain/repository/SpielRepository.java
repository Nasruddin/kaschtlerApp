package com.pkuerzer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkuerzer.domain.model.Spiel;

@Repository
public interface SpielRepository extends JpaRepository<Spiel, Long>{

}
