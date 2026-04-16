package com.figumatch.dto;

import java.time.Instant;

public class UserResponse {
  private Long id;
  private String name;
  private String alias;
  private String email;
  private String zone;
  private String whatsapp;
  private Instant createdAt;

  public UserResponse() {}

  public UserResponse(Long id, String name, String alias, String email, String zone, String whatsapp, Instant createdAt) {
    this.id = id;
    this.name = name;
    this.alias = alias;
    this.email = email;
    this.zone = zone;
    this.whatsapp = whatsapp;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }
}
