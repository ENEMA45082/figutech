package com.figumatch.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.figumatch.dto.SwapCreateRequest;
import com.figumatch.dto.SwapResponse;
import com.figumatch.entity.SwapRequest;
import com.figumatch.entity.SwapStatus;
import com.figumatch.entity.User;
import com.figumatch.repository.SwapRequestRepository;
import com.figumatch.repository.UserRepository;

@Service
public class SwapService {
  private final SwapRequestRepository swapRequestRepository;
  private final UserRepository userRepository;

  public SwapService(SwapRequestRepository swapRequestRepository, UserRepository userRepository) {
    this.swapRequestRepository = swapRequestRepository;
    this.userRepository = userRepository;
  }

  public SwapResponse create(SwapCreateRequest request) {
    User requester = userRepository.findById(request.getFromUserId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found"));
    User receiver = userRepository.findById(request.getToUserId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver not found"));

    SwapRequest swap = new SwapRequest();
    swap.setRequester(requester);
    swap.setReceiver(receiver);
    swap.setOfferedStickers(request.getOffered());
    swap.setRequestedStickers(request.getRequested());
    swap.setStatus(SwapStatus.PENDING);

    return toResponse(swapRequestRepository.save(swap));
  }

  public SwapResponse get(Long id) {
    SwapRequest swap = swapRequestRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Swap not found"));
    return toResponse(swap);
  }

  public List<SwapResponse> listByUser(Long userId) {
    return swapRequestRepository.findByRequesterIdOrReceiverId(userId, userId)
      .stream().map(SwapService::toResponse).collect(Collectors.toList());
  }

  public SwapResponse updateStatus(Long id, SwapStatus status) {
    SwapRequest swap = swapRequestRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Swap not found"));
    swap.setStatus(status);
    return toResponse(swapRequestRepository.save(swap));
  }

  private static SwapResponse toResponse(SwapRequest swap) {
    return new SwapResponse(
      swap.getId(),
      swap.getRequester().getId(),
      swap.getReceiver().getId(),
      swap.getOfferedStickers(),
      swap.getRequestedStickers(),
      swap.getStatus(),
      swap.getCreatedAt()
    );
  }
}
