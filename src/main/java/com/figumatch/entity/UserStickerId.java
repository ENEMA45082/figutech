package com.figumatch.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserStickerId implements Serializable {
  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Integer stickerNumber;

  public UserStickerId() {}

  public UserStickerId(Long userId, Integer stickerNumber) {
    this.userId = userId;
    this.stickerNumber = stickerNumber;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getStickerNumber() {
    return stickerNumber;
  }

  public void setStickerNumber(Integer stickerNumber) {
    this.stickerNumber = stickerNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserStickerId that = (UserStickerId) o;
    return Objects.equals(userId, that.userId)
      && Objects.equals(stickerNumber, that.stickerNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, stickerNumber);
  }
}
