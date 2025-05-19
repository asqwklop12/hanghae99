package io.hhplus.tdd.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointServiceTest {

  private UserPointTable userPoint;
  private PointHistoryTable pointHistory;
  private PointService pointService;

  @BeforeEach
  void init() {
    //given
    userPoint = spy(UserPointTable.class);
    pointHistory = spy(PointHistoryTable.class);
    pointService = new PointService(userPoint, pointHistory);
  }

  @Test
  public void _1번_포인트를_조회하는경우() {
    //given
    when(userPoint.selectById(1L)).thenReturn(new UserPoint(1L, 1000L, 200L));
    //when
    UserPoint point = pointService.point(1L);
    //then
    assertThat(point.id()).isEqualTo(1L);
  }

  @Test
  public void _포인트_충전() {
    //given
    UserPoint point = pointService.charge(1L, 1000L);
    //then
    assertThat(point.id()).isEqualTo(1L);
    assertThat(point.point()).isEqualTo(1000L);
  }

}
