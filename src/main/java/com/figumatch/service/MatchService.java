package com.figumatch.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.figumatch.dto.MatchResultResponse;
import com.figumatch.dto.UserResponse;
import com.figumatch.entity.StickerStatus;
import com.figumatch.entity.User;
import com.figumatch.repository.UserRepository;

@Service
public class MatchService {
  private final UserRepository userRepository;
  private final InventoryService inventoryService;

  public MatchService(UserRepository userRepository, InventoryService inventoryService) {
    this.userRepository = userRepository;
    this.inventoryService = inventoryService;
  }

  public List<MatchResultResponse> findMatches(Long userId, Optional<Integer> limitOpt) {
    User baseUser = userRepository.findById(userId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    Set<Integer> baseTengui = inventoryService.getStickerSet(userId, StickerStatus.TENGUI);
    Set<Integer> baseFalti = inventoryService.getStickerSet(userId, StickerStatus.FALTI);

    List<User> candidates = userRepository.findByZone(baseUser.getZone())
      .stream()
      .filter(user -> !user.getId().equals(userId))
      .collect(Collectors.toList());

    List<MatchResultResponse> results = new ArrayList<>();

    for (User other : candidates) {
      Set<Integer> otherTengui = inventoryService.getStickerSet(other.getId(), StickerStatus.TENGUI);
      Set<Integer> otherFalti = inventoryService.getStickerSet(other.getId(), StickerStatus.FALTI);

      List<Integer> receive = intersect(otherTengui, baseFalti);
      List<Integer> give = intersect(baseTengui, otherFalti);

      if (receive.isEmpty() && give.isEmpty()) continue;

      UserResponse otherResponse = UserService.toResponse(other);
      results.add(new MatchResultResponse(
        otherResponse,
        receive.size(),
        give.size(),
        receive,
        give
      ));
    }

    results.sort(Comparator
      .comparingInt((MatchResultResponse r) -> r.getReceiveCount() + r.getGiveCount()).reversed()
      .thenComparing(MatchResultResponse::getReceiveCount, Comparator.reverseOrder())
    );

    int limit = limitOpt.orElse(20);
    if (results.size() > limit) {
      return results.subList(0, limit);
    }
    return results;
  }

  private List<Integer> intersect(Set<Integer> left, Set<Integer> right) {
    return left.stream().filter(right::contains).sorted().collect(Collectors.toList());
  }
}
