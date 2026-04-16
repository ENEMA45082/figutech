package com.figumatch.service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.figumatch.dto.InventoryResponse;
import com.figumatch.dto.InventoryUpdateItem;
import com.figumatch.entity.StickerStatus;
import com.figumatch.entity.User;
import com.figumatch.entity.UserSticker;
import com.figumatch.entity.UserStickerId;
import com.figumatch.repository.UserRepository;
import com.figumatch.repository.UserStickerRepository;

@Service
public class InventoryService {
  private final UserRepository userRepository;
  private final UserStickerRepository userStickerRepository;

  public InventoryService(UserRepository userRepository, UserStickerRepository userStickerRepository) {
    this.userRepository = userRepository;
    this.userStickerRepository = userStickerRepository;
  }

  public InventoryResponse getInventory(Long userId) {
    ensureUserExists(userId);
    List<UserSticker> tengui = userStickerRepository.findByIdUserIdAndStatus(userId, StickerStatus.TENGUI);
    List<UserSticker> falti = userStickerRepository.findByIdUserIdAndStatus(userId, StickerStatus.FALTI);

    return new InventoryResponse(
      tengui.stream().map(item -> item.getId().getStickerNumber()).sorted().collect(Collectors.toList()),
      falti.stream().map(item -> item.getId().getStickerNumber()).sorted().collect(Collectors.toList())
    );
  }

  public void applyUpdates(Long userId, List<InventoryUpdateItem> updates) {
    ensureUserExists(userId);

    for (InventoryUpdateItem update : updates) {
      int number = update.getNumero();
      if (number < 1 || number > 980) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sticker number out of range");
      }

      String estado = update.getEstado().trim().toUpperCase(Locale.ROOT);
      if ("NADA".equals(estado)) {
        userStickerRepository.deleteById(new UserStickerId(userId, number));
        continue;
      }

      StickerStatus status;
      try {
        status = StickerStatus.valueOf(estado);
      }
      catch (IllegalArgumentException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid estado: " + update.getEstado());
      }

      UserStickerId id = new UserStickerId(userId, number);
      UserSticker item = userStickerRepository.findById(id)
        .orElseGet(() -> new UserSticker(id, status));
      item.setStatus(status);
      item.setUpdatedAt(Instant.now());
      userStickerRepository.save(item);
    }
  }

  public Set<Integer> getStickerSet(Long userId, StickerStatus status) {
    return userStickerRepository.findByIdUserIdAndStatus(userId, status)
      .stream()
      .map(item -> item.getId().getStickerNumber())
      .collect(Collectors.toSet());
  }

  private User ensureUserExists(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }
}
