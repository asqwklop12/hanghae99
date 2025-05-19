package io.hhplus.tdd.point;

public record UserPoint(
    long id,
    long point,
    long updateMillis
) {

  public static UserPoint empty(long id) {
    return new UserPoint(id, 0, System.currentTimeMillis());
  }

  public void validate() {
    if (point > 10000) {
      throw new IllegalArgumentException("포인트는 최대 10000포인트까지 충전이 가능합니다.");
    }
  }
}
