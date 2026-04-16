package com.figumatch.dto;

import java.time.Instant;
import java.util.List;
import com.figumatch.entity.SwapStatus;

public class SwapResponse {
  private Long id;
  private Long requesterId;
  private Long receiverId;
  private List<Integer> offered;
  private List<Integer> requested;
  private SwapStatus status;
  private Instant createdAt;

  public SwapResponse() {}

  public SwapResponse(Long id, Long requesterId, Long receiverId, List<Integer> offered,
    List<Integer> requested, SwapStatus status, Instant createdAt) {
    this.id = id;
    this.requesterId = requesterId;
    this.receiverId = receiverId;
    this.offered = offered;
    this.requested = requested;
    this.status = status;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRequesterId() {
    return requesterId;
  }

  public void setRequesterId(Long requesterId) {
    this.requesterId = requesterId;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public List<Integer> getOffered() {
    return offered;
  }

  public void setOffered(List<Integer> offered) {
    this.offered = offered;
  }

  public List<Integer> getRequested() {
    return requested;
  }

  public void setRequested(List<Integer> requested) {
    this.requested = requested;
  }

  public SwapStatus getStatus() {
    return status;
  }

  public void setStatus(SwapStatus status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }
}
