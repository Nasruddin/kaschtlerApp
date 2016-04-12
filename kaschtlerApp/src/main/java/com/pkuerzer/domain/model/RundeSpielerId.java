package com.pkuerzer.domain.model;

import java.io.Serializable;

public class RundeSpielerId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long runde;
	private Long spieler;
	
	public Long getRunde() {
		return runde;
	}
	public void setRunde(Long runde) {
		this.runde = runde;
	}
	public Long getSpieler() {
		return spieler;
	}
	public void setSpieler(Long spieler) {
		this.spieler = spieler;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((runde == null) ? 0 : runde.hashCode());
		result = prime * result + ((spieler == null) ? 0 : spieler.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RundeSpielerId other = (RundeSpielerId) obj;
		if (runde == null) {
			if (other.runde != null)
				return false;
		} else if (!runde.equals(other.runde))
			return false;
		if (spieler == null) {
			if (other.spieler != null)
				return false;
		} else if (!spieler.equals(other.spieler))
			return false;
		return true;
	}
	
}
