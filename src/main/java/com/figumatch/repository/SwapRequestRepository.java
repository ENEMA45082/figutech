package com.figumatch.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.figumatch.entity.SwapRequest;

public interface SwapRequestRepository extends JpaRepository<SwapRequest, Long> {
  List<SwapRequest> findByRequesterIdOrReceiverId(Long requesterId, Long receiverId);
}
