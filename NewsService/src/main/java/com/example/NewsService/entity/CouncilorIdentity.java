package com.example.NewsService.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class CouncilorIdentity implements Serializable{
	
	public CouncilorIdentity(String party, String constituency) {
		this.party = party;
		this.constituency = constituency;
	}
	
	public CouncilorIdentity(){}
	
	@NotNull
	@Size(max = 20)
	@Column(columnDefinition="varchar(45)")
	private String party;
	 
	@NotNull
	@Column(columnDefinition="varchar(45)")
	@Size(max = 20)
	private String constituency;
	
	
	
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CouncilorIdentity that = (CouncilorIdentity) o;

        if (!constituency.equals(that.constituency)) return false;
        return party.equals(that.party);
    }

    @Override
    public int hashCode() {
        int result = constituency.hashCode();
        result = 31 * result + party.hashCode();
        return result;
    }

	@Override
	public String toString() {
		return "CouncilorIdentity [party=" + party + ", constituency=" + constituency + "]";
	}
    
}
