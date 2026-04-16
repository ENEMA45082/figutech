package com.figumatch.controller;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.figumatch.dto.SwapCreateRequest;
import com.figumatch.dto.SwapResponse;
import com.figumatch.entity.SwapStatus;
import com.figumatch.service.SwapService;

@RestController
@RequestMapping("/api/swaps")
public class SwapController {
  private final SwapService swapService;

  public SwapController(SwapService swapService) {
    this.swapService = swapService;
  }

  @PostMapping
  public SwapResponse create(@Valid @RequestBody SwapCreateRequest request) {
    return swapService.create(request);
  }

  @GetMapping("/{id}")
  public SwapResponse get(@PathVariable Long id) {
    return swapService.get(id);
  }

  @GetMapping
  public List<SwapResponse> list(@RequestParam Optional<Long> userId) {
    if (userId.isEmpty()) {
      return List.of();
    }
    return swapService.listByUser(userId.get());
  }

  @PatchMapping("/{id}/status")
  public SwapResponse updateStatus(@PathVariable Long id, @RequestParam SwapStatus value) {
    return swapService.updateStatus(id, value);
  }
}
