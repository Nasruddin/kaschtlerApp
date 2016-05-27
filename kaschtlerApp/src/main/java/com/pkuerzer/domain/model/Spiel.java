package com.pkuerzer.domain.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Spiel {
	
	public Spiel(){
		super();
	}
	
	public Spiel(Date created){
		this.created = created;
	}

	@Id
	@SequenceGenerator(name="spiel_seq_gen", sequenceName="SPIEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="spiel_seq_gen")
	@Column
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="spiel_id")
	@OrderBy("rundenNummer ASC")
	private Set<Runde> runden;
	
	@Column
	@DateTimeFormat(pattern="dd.MM.yyyy HH:mm")
	private Date created;
	
	@Column
	private boolean finished;
	
	@Column
	private boolean valid;
	
	public Runde getCurrentRound(Integer rundenNummer){
		return this.getRunden().stream().filter(runde -> runde.getRundenNummer() == rundenNummer).findFirst().get();
	}
	
	public Runde getPreviousRound(Integer rundenNummer){
		return this.getRunden().stream().filter(runde -> runde.getRundenNummer() == rundenNummer -1).findFirst().get();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Runde> getRunden() {
		return runden;
	}

	public void setRunden(Set<Runde> runden) {
		this.runden = runden;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
