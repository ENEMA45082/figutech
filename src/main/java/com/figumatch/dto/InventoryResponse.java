package com.figumatch.dto;

import java.util.List;

public class InventoryResponse {
  private List<Integer> tengui;
  private List<Integer> falti;

  public InventoryResponse() {}

  public InventoryResponse(List<Integer> tengui, List<Integer> falti) {
    this.tengui = tengui;
    this.falti = falti;
  }

  public List<Integer> getTengui() {
    return tengui;
  }

  public void setTengui(List<Integer> tengui) {
    this.tengui = tengui;
  }

  public List<Integer> getFalti() {
    return falti;
  }

  public void setFalti(List<Integer> falti) {
    this.falti = falti;
  }
}
