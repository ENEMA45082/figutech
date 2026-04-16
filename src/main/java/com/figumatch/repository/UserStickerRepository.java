package com.figumatch.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.figumatch.entity.StickerStatus;
import com.figumatch.entity.UserSticker;
import com.figumatch.entity.UserStickerId;

public interface UserStickerRepository extends JpaRepository<UserSticker, UserStickerId> {
  List<UserSticker> findByIdUserId(Long userId);
  List<UserSticker> findByIdUserIdAndStatus(Long userId, StickerStatus status);
}
