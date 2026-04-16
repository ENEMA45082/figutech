package com.figumatch.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.figumatch.dto.MatchResultResponse;
import com.figumatch.service.MatchService;

@RestController
@RequestMapping("/api/users/{userId}/matches")
public class MatchController {
  private final MatchService matchService;

  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @GetMapping
  public List<MatchResultResponse> list(@PathVariable Long userId,
    @RequestParam Optional<Integer> limit) {
    return matchService.findMatches(userId, limit);
  }
}
