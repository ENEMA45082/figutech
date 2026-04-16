package com.figumatch.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class UserUpdateRequest {
  @Size(max = 100)
  private String name;

  @Size(max = 60)
  private String alias;

  @Email
  @Size(max = 160)
  private String email;

  @Size(max = 80)
  private String zone;

  @Size(max = 30)
  private String whatsapp;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
  }
}
