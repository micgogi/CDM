package com.cts.election.model;

public class WinnerStatus {
  String constituency;
  String party;
  String imageUrl;

  public WinnerStatus() {}

  public WinnerStatus(String constituency, String party, String leading) {
    this.constituency = constituency;
    this.party = party;
    this.imageUrl = leading;
  }

  public String getConstituency() {
    return constituency;
  }

  public void setConstituency(String constituency) {
    this.constituency = constituency;
  }

  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
