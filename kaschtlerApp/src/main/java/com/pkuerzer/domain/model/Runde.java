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
public class Runde {

	@Id
	@SequenceGenerator(name="runde_seq_gen", sequenceName="RUNDE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="runde_seq_gen")
	@Column
	private Long id;
	
	@OneToMany(mappedBy="runde", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<RundeSpieler> spieler;
	
	@Column
	private boolean herz;
	
	@Column
	private Integer multiplikation;
	
	@Column
	private boolean muli;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<RundeSpieler> getSpieler() {
		return spieler;
	}

	public void setSpieler(List<RundeSpieler> spieler) {
		this.spieler = spieler;
	}

	public boolean isHerz() {
		return herz;
	}

	public void setHerz(boolean herz) {
		this.herz = herz;
	}

	public boolean isMuli() {
		return muli;
	}

	public void setMuli(boolean muli) {
		this.muli = muli;
	}

	public Integer getMultiplikation() {
		return multiplikation;
	}

	public void setMultiplikation(Integer multiplikation) {
		this.multiplikation = multiplikation;
	}
	
}
