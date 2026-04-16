package com.figumatch.entity;

import java.time.Instant;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.figumatch.util.JsonListConverter;

@Entity
@Table(name = "swap_requests")
public class SwapRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "requester_id")
  private User requester;

  @ManyToOne(optional = false)
  @JoinColumn(name = "receiver_id")
  private User receiver;

  @Convert(converter = JsonListConverter.class)
  @Column(nullable = false, columnDefinition = "TEXT")
  private List<Integer> offeredStickers;

  @Convert(converter = JsonListConverter.class)
  @Column(nullable = false, columnDefinition = "TEXT")
  private List<Integer> requestedStickers;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 12)
  private SwapStatus status = SwapStatus.PENDING;

  @Column(nullable = false)
  private Instant createdAt = Instant.now();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getRequester() {
    return requester;
  }

  public void setRequester(User requester) {
    this.requester = requester;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public List<Integer> getOfferedStickers() {
    return offeredStickers;
  }

  public void setOfferedStickers(List<Integer> offeredStickers) {
    this.offeredStickers = offeredStickers;
  }

  public List<Integer> getRequestedStickers() {
    return requestedStickers;
  }

  public void setRequestedStickers(List<Integer> requestedStickers) {
    this.requestedStickers = requestedStickers;
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
