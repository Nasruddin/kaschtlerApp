package com.pkuerzer.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(RundeSpielerId.class)
public class RundeSpieler {

	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="runde_id")
	private Runde runde;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="spieler_id")
	private Spieler spieler;
	
	@Column
	private Integer punkte;
	
	@Column
	private Integer position;
	
	@Column
	private boolean gegangen;

	public Runde getRunde() {
		return runde;
	}

	public void setRunde(Runde runde) {
		this.runde = runde;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public Integer getPunkte() {
		return punkte;
	}

	public void setPunkte(Integer punkte) {
		this.punkte = punkte;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public boolean isGegangen() {
		return gegangen;
	}

	public void setGegangen(boolean gegangen) {
		this.gegangen = gegangen;
	}
	
}
