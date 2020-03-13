package com.cts.election.model;

public class PartyStatus {
	String partyName;
	String constituencyName;
	int votes;
	int maleVotes = 0;
	int femaleVotes = 0;
	
	public PartyStatus() {}

	public PartyStatus(String partyName, String constituencyName, int votes, int maleVotes,
			int femaleVotes) {
		this.partyName = partyName;
		this.constituencyName = constituencyName;
		this.votes = votes;
		this.maleVotes = maleVotes;
		this.femaleVotes = femaleVotes;
	}
	
	

	public PartyStatus(String partyName, String constituencyName, int votes) {
		this.partyName = partyName;
		this.constituencyName = constituencyName;
		this.votes = votes;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getMaleVotes() {
		return maleVotes;
	}

	public void setMaleVotes(int maleVotes) {
		this.maleVotes = maleVotes;
	}

	public int getFemaleVotes() {
		return femaleVotes;
	}

	public void setFemaleVotes(int femaleVotes) {
		this.femaleVotes = femaleVotes;
	}

	@Override
	public String toString() {
		return "PartyStatus [partyName=" + partyName + ", constituencyName=" + constituencyName + ", votes=" + votes
				+ ", maleVotes=" + maleVotes + ", femaleVotes=" + femaleVotes + "]";
	}

	
	
	
	
}
