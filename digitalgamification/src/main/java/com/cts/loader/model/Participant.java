package com.cts.loader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class Participant {
	@Column(name = "associate_id")
	@Id
	private String pid;

	@Column(name = "completion_status")
	private int completion_status;

	String associateName;

	public Participant() {
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public int getCompletion_status() {
		return completion_status;
	}

	public void setCompletion_status(int completion_status) {
		this.completion_status = completion_status;
	}

}
