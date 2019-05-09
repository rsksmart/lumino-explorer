package org.rif.lumino.explorer.models.enums;

public enum FeedType {
  CHANNEL(1),
  NODE(2),
  TOKEN(3);

  private int id;

  FeedType(int id) {
    this.id = id;
  }
}
