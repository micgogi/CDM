package com.cts.election.model;

public class ConstituencyStatus {
  String associateId;
  String associateName;
  String finalStatus;
  String partyName;

  public ConstituencyStatus() {}

  public ConstituencyStatus(
      String associateId, String associateName, String finalStatus, String partyName) {
    this.associateId = associateId;
    this.associateName = associateName;
    this.finalStatus = finalStatus;
    this.partyName = partyName;
  }

  public String getAssociateId() {
    return associateId;
  }

  public void setAssociateId(String associateId) {
    this.associateId = associateId;
  }

  public String getFinalStatus() {
    return finalStatus;
  }

  public void setFinalStatus(String finalStatus) {
    this.finalStatus = finalStatus;
  }

  public String getPartyName() {
    return partyName;
  }

  public void setPartyName(String partyName) {
    this.partyName = partyName;
  }

  public String getAssociateName() {
    return associateName;
  }

  public void setAssociateName(String associateName) {
    this.associateName = associateName;
  }
}
