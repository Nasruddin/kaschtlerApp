package com.pkuerzer.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Spieler {

	@Id
	@SequenceGenerator(name="spieler_seq_gen", sequenceName="Spieler_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="spieler_seq_gen")
	@Column
	private Long id;
	
	@OneToMany(mappedBy="spieler", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	List<RundeSpieler> runden;
	
	@Column
	private String name;
	
	@Column
	private boolean active;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<RundeSpieler> getRunden() {
		return runden;
	}

	public void setRunden(List<RundeSpieler> runden) {
		this.runden = runden;
	}
	
}
