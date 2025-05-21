package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.properties.PointProperties;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

@Service
public class PointService {
  private final PointProperties properties;

  private final UserPointTable userPointTable;
  private final PointHistoryTable pointHistoryTable;

  private Lock lock = new ReentrantLock();

  public PointService(UserPointTable userPointTable,
                      PointHistoryTable pointHistoryTable,
                      PointProperties properties) {
    this.userPointTable = userPointTable;
    this.pointHistoryTable = pointHistoryTable;
    this.properties = properties;
  }

  public UserPoint point(long id) {
    return userPointTable.selectById(id);
  }

  public List<PointHistory> history(long id) {
    return pointHistoryTable.selectAllByUserId(id);
  }

  public UserPoint charge(long id, long amount) {
    //기존 포인트 가져온다.
    isNotCharge(amount);

    lock.lock();
    UserPoint currentUserPoint = userPointTable.selectById(id);
    long increaseAmount = currentUserPoint.point() + amount;

    currentUserPoint.isMaxAvailableCharge(amount, properties.getAvailableCharge());
    UserPoint userPoint = userPointTable.insertOrUpdate(id, increaseAmount);
    pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    lock.unlock();
    return userPoint;
  }

  private void isNotCharge(long amount) {
    if (amount <= properties.getMinSingleChargeAmount() || amount >= properties.getMaxSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }

  }

  @Synchronized
  public UserPoint use(long id, long amount) {
    isNotUse(amount);
    UserPoint userPoint = userPointTable.selectById(id);
    userPoint.checkPointUseMore(amount);
    pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
    return userPointTable.insertOrUpdate(id, userPoint.point() - amount);
  }

  private void isNotUse(long amount) {
    if (amount <= properties.getMinSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }
  }

}
