package com.figumatch.dto;

import java.util.List;

public class MatchResultResponse {
  private UserResponse user;
  private int receiveCount;
  private int giveCount;
  private List<Integer> receiveStickers;
  private List<Integer> giveStickers;

  public MatchResultResponse() {}

  public MatchResultResponse(UserResponse user, int receiveCount, int giveCount,
    List<Integer> receiveStickers, List<Integer> giveStickers) {
    this.user = user;
    this.receiveCount = receiveCount;
    this.giveCount = giveCount;
    this.receiveStickers = receiveStickers;
    this.giveStickers = giveStickers;
  }

  public UserResponse getUser() {
    return user;
  }

  public void setUser(UserResponse user) {
    this.user = user;
  }

  public int getReceiveCount() {
    return receiveCount;
  }

  public void setReceiveCount(int receiveCount) {
    this.receiveCount = receiveCount;
  }

  public int getGiveCount() {
    return giveCount;
  }

  public void setGiveCount(int giveCount) {
    this.giveCount = giveCount;
  }

  public List<Integer> getReceiveStickers() {
    return receiveStickers;
  }

  public void setReceiveStickers(List<Integer> receiveStickers) {
    this.receiveStickers = receiveStickers;
  }

  public List<Integer> getGiveStickers() {
    return giveStickers;
  }

  public void setGiveStickers(List<Integer> giveStickers) {
    this.giveStickers = giveStickers;
  }
}
