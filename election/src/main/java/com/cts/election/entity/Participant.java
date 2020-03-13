package com.cts.election.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Participant {
	
	
	@Column(name="completion_status")
	private int completion_status;
	
	@Id
	String associateId;
	
	String associateName;
	
	
}
