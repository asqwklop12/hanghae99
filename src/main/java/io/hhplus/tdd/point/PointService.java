package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PointService {

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
    validate(amount);
    //기존 포인트 가져온다.
    UserPoint currentUserPoint = userPointTable.selectById(id);
    UserPoint userPoint = userPointTable.insertOrUpdate(id, currentUserPoint.point() + amount);
    userPoint.validate();
    pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    return userPoint;
  }

  private static void validate(long amount) {
    if (amount <= 0 || amount >= 2000) {
      throw new IllegalArgumentException("포인트는 0보다 크거나 2000미만으로 충전할 수 있습니다.");
    }

  }

  public UserPoint use(long id, long amount) {
    UserPoint userPoint = userPointTable.selectById(id);
    pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
    return userPointTable.insertOrUpdate(id, userPoint.point() - amount);
  }
}
