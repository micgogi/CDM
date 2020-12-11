package com.cts.election.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MasterData {
  @Id String associateId;
  String associateName;
  String partyName;
  String constituencyName;
  String gender;

  public String getAssociateId() {
    return associateId;
  }

  public void setAssociateId(String associateId) {
    this.associateId = associateId;
  }

  public String getAssociateName() {
    return associateName;
  }

  public void setAssociateName(String associateName) {
    this.associateName = associateName;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
