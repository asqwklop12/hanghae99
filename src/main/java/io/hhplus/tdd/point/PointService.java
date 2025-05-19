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
    UserPoint userPoint = userPointTable.insertOrUpdate(id, amount);
    pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    return userPoint;
  }

  private static void validate(long amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("0이하로 포인트를 충전할 수 없습니다.");
    }

    if(amount >= 2000) {
      throw new IllegalArgumentException("포인트는 한번에 2000포인트 이상으로 충전이 불가합니다.");
    }
  }

  public UserPoint use(long id, long amount) {
    UserPoint userPoint = userPointTable.selectById(id);
    pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
    return userPointTable.insertOrUpdate(id, userPoint.point() - amount);
  }
}
