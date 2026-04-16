package com.figumatch.entity;

import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_stickers")
public class UserSticker {
  @EmbeddedId
  private UserStickerId id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private StickerStatus status;

  @Column(nullable = false)
  private Instant updatedAt = Instant.now();

  public UserSticker() {}

  public UserSticker(UserStickerId id, StickerStatus status) {
    this.id = id;
    this.status = status;
    this.updatedAt = Instant.now();
  }

  public UserStickerId getId() {
    return id;
  }

  public void setId(UserStickerId id) {
    this.id = id;
  }

  public StickerStatus getStatus() {
    return status;
  }

  public void setStatus(StickerStatus status) {
    this.status = status;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
