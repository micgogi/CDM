package com.cts.loader.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class DailyData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String constituencyName;
	private int party1Percent;
	private int party2Percent;
	
	@CreationTimestamp
	LocalDateTime created;
	
	
	public DailyData() {}

	public DailyData(String constituencyName, int party1Percent, int party2Percent) {
		this.constituencyName = constituencyName;
		this.party1Percent = party1Percent;
		this.party2Percent = party2Percent;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public int getParty1Percent() {
		return party1Percent;
	}

	public void setParty1Percent(int party1Percent) {
		this.party1Percent = party1Percent;
	}

	public int getParty2Percent() {
		return party2Percent;
	}

	public void setParty2Percent(int party2Percent) {
		this.party2Percent = party2Percent;
	}
	
	
}
