package com.figumatch.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.figumatch.dto.InventoryResponse;
import com.figumatch.dto.InventoryUpdateRequest;
import com.figumatch.service.InventoryService;

@RestController
@RequestMapping("/api/users/{userId}/inventory")
public class InventoryController {
  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping
  public InventoryResponse get(@PathVariable Long userId) {
    return inventoryService.getInventory(userId);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Long userId, @Valid @RequestBody InventoryUpdateRequest request) {
    inventoryService.applyUpdates(userId, request.getUpdates());
  }
}
