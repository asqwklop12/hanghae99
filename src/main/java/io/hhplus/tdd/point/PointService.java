package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.manager.LockManager;
import io.hhplus.tdd.point.properties.PointProperties;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;

@Service
public class PointService {
  private final PointProperties properties;

  private final UserPointTable userPointTable;
  private final PointHistoryTable pointHistoryTable;

  private final LockManager lockManager;

  public PointService(UserPointTable userPointTable,
                      PointHistoryTable pointHistoryTable,
                      PointProperties properties,
                      LockManager lockManager) {
    this.userPointTable = userPointTable;
    this.pointHistoryTable = pointHistoryTable;
    this.properties = properties;
    this.lockManager = lockManager;
  }

  public UserPoint point(long id) {
    return userPointTable.selectById(id);
  }

  public List<PointHistory> history(long id) {
    return pointHistoryTable.selectAllByUserId(id);
  }

  public UserPoint charge(long id, long amount) {
    //기존 포인트 가져온다.
    checkChargePoint(amount);
    String key = "charge-" + id;

    return lockManager.withLock(key, () -> {
      UserPoint currentUserPoint = userPointTable.selectById(id);
      long increaseAmount = currentUserPoint.point() + amount;
      currentUserPoint.checkMaxAvailableCharge(amount, properties.getAvailableCharge());
      UserPoint updated = userPointTable.insertOrUpdate(id, increaseAmount);
      pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
      return updated;
    });
  }

  private void checkChargePoint(long amount) {
    if (amount <= properties.getMinSingleChargeAmount() || amount >= properties.getMaxSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }

  }

  public UserPoint use(long id, long amount) {
    checkNotUse(amount);
    String key = "use-" + id;
    return lockManager.withLock(key, () -> {
      UserPoint userPoint = userPointTable.selectById(id);
      userPoint.checkPointUseMore(amount);
      UserPoint usePoint = userPointTable.insertOrUpdate(id, userPoint.point() - amount);
      pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
      return usePoint;
    });
  }

  private void checkNotUse(long amount) {
    if (amount <= properties.getMinSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }
  }

}
