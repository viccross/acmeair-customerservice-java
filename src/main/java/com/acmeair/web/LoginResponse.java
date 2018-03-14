package com.acmeair.web;

public class LoginResponse {

  private Boolean validated;


  public LoginResponse() {}

  public LoginResponse(Boolean validated) {
    this.setValidated(validated);

  }

  public Boolean isValidated() {
    return validated;
  }

  public void setValidated(Boolean validated) {
    this.validated = validated;
  }
}
