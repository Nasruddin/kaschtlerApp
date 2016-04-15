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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
	private List<Runde> runden;
	
	@Column
	private Date created;
	
	@Column
	private boolean finished;
	
	@Column
	private boolean valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Runde> getRunden() {
		return runden;
	}

	public void setRunden(List<Runde> runden) {
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
