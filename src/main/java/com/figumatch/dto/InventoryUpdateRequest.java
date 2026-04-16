package com.figumatch.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class InventoryUpdateRequest {
  @NotNull
  @NotEmpty
  private List<InventoryUpdateItem> updates;

  public List<InventoryUpdateItem> getUpdates() {
    return updates;
  }

  public void setUpdates(List<InventoryUpdateItem> updates) {
    this.updates = updates;
  }
}
