package com.figumatch.controller;

import java.util.Map;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.figumatch.repository.SwapRequestRepository;
import com.figumatch.repository.UserRepository;
import com.figumatch.repository.UserStickerRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  private final UserRepository userRepository;
  private final UserStickerRepository userStickerRepository;
  private final SwapRequestRepository swapRequestRepository;
  private final String adminToken;

  public AdminController(
    UserRepository userRepository,
    UserStickerRepository userStickerRepository,
    SwapRequestRepository swapRequestRepository,
    @Value("${FIGUMATCH_ADMIN_TOKEN:}") String adminToken
  ) {
    this.userRepository = userRepository;
    this.userStickerRepository = userStickerRepository;
    this.swapRequestRepository = swapRequestRepository;
    this.adminToken = adminToken;
  }

  @PostMapping("/reset")
  @Transactional
  public Map<String, Object> reset(@RequestHeader("X-Admin-Token") String token) {
    if (adminToken == null || adminToken.isBlank()) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN,
        "Admin token is not configured"
      );
    }
    if (!adminToken.equals(token)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid admin token");
    }

    long swaps = swapRequestRepository.count();
    long stickers = userStickerRepository.count();
    long users = userRepository.count();

    swapRequestRepository.deleteAll();
    userStickerRepository.deleteAll();
    userRepository.deleteAll();

    return Map.of(
      "deletedSwaps", swaps,
      "deletedStickers", stickers,
      "deletedUsers", users
    );
  }
}
