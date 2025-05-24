package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.properties.PointProperties;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;

@Service
public class PointService {
  private final PointProperties properties;

  private final UserPointTable userPointTable;
  private final PointHistoryTable pointHistoryTable;

  private Map<String, ReentrantLock> locks = new ConcurrentHashMap<>();

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
    checkChargePoint(amount);
    UserPoint userPoint;
    locks.putIfAbsent("charge-" + id, new ReentrantLock());
    ReentrantLock lock = locks.get("charge-" + id);

    lock.lock();
    try {
      UserPoint currentUserPoint = userPointTable.selectById(id);
      long increaseAmount = currentUserPoint.point() + amount;
      currentUserPoint.checkMaxAvailableCharge(amount, properties.getAvailableCharge());
      userPoint = userPointTable.insertOrUpdate(id, increaseAmount);
      pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    } finally {
      lock.unlock();
    }
    return userPoint;
  }

  private void checkChargePoint(long amount) {
    if (amount <= properties.getMinSingleChargeAmount() || amount >= properties.getMaxSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }

  }

  public UserPoint use(long id, long amount) {
    checkNotUse(amount);
    UserPoint usePoint;
    locks.putIfAbsent("use-" + id, new ReentrantLock());
    ReentrantLock lock = locks.get("use-" + id);
    lock.lock();
    try {
      UserPoint userPoint = userPointTable.selectById(id);
      userPoint.checkPointUseMore(amount);
      usePoint = userPointTable.insertOrUpdate(id, userPoint.point() - amount);
      pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
    } finally {
      lock.unlock();
    }

    return usePoint;
  }

  private void checkNotUse(long amount) {
    if (amount <= properties.getMinSingleChargeAmount()) {
      throw new IllegalArgumentException("1회당 충전할 수 있는 포인트의 범위가 다릅니다. 다시 확인해주세요.");
    }
  }

}
