package com.figumatch.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SwapCreateRequest {
  @NotNull
  private Long fromUserId;

  @NotNull
  private Long toUserId;

  @NotEmpty
  private List<Integer> offered;

  @NotEmpty
  private List<Integer> requested;

  public Long getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Long fromUserId) {
    this.fromUserId = fromUserId;
  }

  public Long getToUserId() {
    return toUserId;
  }

  public void setToUserId(Long toUserId) {
    this.toUserId = toUserId;
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
}
