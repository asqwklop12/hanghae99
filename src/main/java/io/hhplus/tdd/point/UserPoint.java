package io.hhplus.tdd.point;

public record UserPoint(
    long id,
    long point,
    long updateMillis
) {

  public static UserPoint empty(long id) {
    return new UserPoint(id, 0, System.currentTimeMillis());
  }

  public void isMaxAvailableCharge(long amount, long maxAvailableCharge) {
    if (point + amount > maxAvailableCharge) {
      throw new IllegalArgumentException("최대로 지정할수 있는 포인트를 넘어섰습니다. 관리자에게 문의부탁드립니다.");
    }
  }

  public void checkPointUseMore(long amount) {
    if (point < amount) {
      throw new IllegalArgumentException("현재 충전된 금액보다 더 큰 포인트를 사용할 수 없다.");
    }
  }
}
