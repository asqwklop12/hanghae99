package io.hhplus.tdd.point;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PointService {
  public UserPoint point(long id) {
    return new UserPoint(0, 0, 0);
  }

  public List<PointHistory> history(long id) {
    return List.of();
  }

  public UserPoint charge(long id, long amount) {
    return new UserPoint(0, 0, 0);
  }

  public UserPoint use(long id, long amount) {
    return new UserPoint(0, 0, 0);
  }
}
