package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PointService {

  @Value("${point.available_charge}")
  private static long MAX_AVAILABLE_CHARGE;
  @Value("${point.max}")
  private static long MAX_SINGLE_CHARGE_AMOUNT;
  @Value("${point.min}")
  private static long MIN_SINGLE_CHARGE_AMOUNT;


  private final UserPointTable userPointTable;
  private final PointHistoryTable pointHistoryTable;

  public PointService(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
    this.userPointTable = userPointTable;
    this.pointHistoryTable = pointHistoryTable;
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

    UserPoint currentUserPoint = userPointTable.selectById(id);
    long increaseAmount = currentUserPoint.point() + amount;

    currentUserPoint.isMaxAvailableCharge(amount, MAX_AVAILABLE_CHARGE);
    UserPoint userPoint = userPointTable.insertOrUpdate(id, increaseAmount);
    pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    return userPoint;
  }

  private static void isNotCharge(long amount) {
    if (amount <= MIN_SINGLE_CHARGE_AMOUNT || amount >= MAX_SINGLE_CHARGE_AMOUNT) {
      throw new IllegalArgumentException("포인트는 0보다 크거나 2000미만으로 충전할 수 있습니다.");
    }

  }

  public UserPoint use(long id, long amount) {
    isNotUse(amount);
    UserPoint userPoint = userPointTable.selectById(id);
    userPoint.checkPointUseMore(amount);
    pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
    return userPointTable.insertOrUpdate(id, userPoint.point() - amount);
  }

  private static void isNotUse(long amount) {
    if (amount <= MIN_SINGLE_CHARGE_AMOUNT) {
      throw new IllegalArgumentException("0포인트 이하로 사용이 불가합니다.");
    }
  }
}
