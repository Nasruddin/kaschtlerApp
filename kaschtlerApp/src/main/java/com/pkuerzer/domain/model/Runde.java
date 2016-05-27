package com.pkuerzer.domain.model;

import java.util.Date;
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
	
	public Runde(){
		super();
	}
	public Runde(Date created, Integer multiplikation){
		this.created = created;
		this.multiplikation = multiplikation;
	}

	@Id
	@SequenceGenerator(name="runde_seq_gen", sequenceName="RUNDE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="runde_seq_gen")
	@Column
	private Long id;

	@Column(name="spiel_id")
	private Long spielId;
	
	@OneToMany(mappedBy="runde", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<RundeSpieler> spieler;
	
	@Column
	private boolean herz;
	
	@Column
	private Integer multiplikation;
	
	@Column
	private Integer rundenNummer;
	
	@Column
	private boolean muli;
	
	@Column
	private Date created;
	
	public boolean isLastRound(){
		if(this.rundenNummer == 15){
			return true;
		} else{
			return false;
		}
	}
	
	public boolean isOnePlayerWinner(){
		return this.getSpieler().stream().anyMatch(s -> s.getPunkte() <= 0);
	}
	
	public boolean isOnePlayerOver100(){
		return this.getSpieler().stream().anyMatch(s -> s.getPunkte() >= 100);
	}

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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Integer getRundenNummer() {
		return rundenNummer;
	}
	public void setRundenNummer(Integer rundenNummer) {
		this.rundenNummer = rundenNummer;
	}
	public Long getSpielId() {
		return spielId;
	}
	public void setSpielId(Long spielId) {
		this.spielId = spielId;
	}
	
}
