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
    UserPoint userPoint = userPointTable.insertOrUpdate(id, amount);
    pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
    return userPoint;
  }

  public UserPoint use(long id, long amount) {
    UserPoint userPoint = userPointTable.selectById(id);
    return userPointTable.insertOrUpdate(id, userPoint.point() - amount);
  }
}
