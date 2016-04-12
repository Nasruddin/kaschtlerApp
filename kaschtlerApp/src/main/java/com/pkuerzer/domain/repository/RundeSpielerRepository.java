package com.pkuerzer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkuerzer.domain.model.RundeSpieler;

@Repository
public interface RundeSpielerRepository extends JpaRepository<RundeSpieler, Long>{

}
