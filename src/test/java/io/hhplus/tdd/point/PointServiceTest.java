package io.hhplus.tdd.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import java.util.List;
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
    //when
    UserPoint point = pointService.charge(1L, 1000L);
    //then
    assertThat(point.id()).isEqualTo(1L);
    assertThat(point.point()).isEqualTo(1000L);
  }

  @Test
  public void _포인트_충전을_안했을_경우_히스토리_조회() {
    //when
    List<PointHistory> history = pointService.history(1L);
    //then
    assertThat(history).isEmpty();
  }

  @Test
  public void _포인트_충전을_했을_경우_히스토리_조회() {
    //given
    UserPoint point = pointService.charge(1L, 1000L);
    //when
    List<PointHistory> history = pointService.history(point.id());
    //then
    assertThat(history).isNotEmpty();
  }

  @Test
  public void _포인트_사용() {
    //given
    pointService.charge(1L, 1000L);
    //when
    UserPoint point = pointService.use(1L, 200L);
    //then
    assertThat(point.point()).isEqualTo(800L);
  }

  @Test
  public void _포인트_사용_히스토리확인() {
    //given
    pointService.charge(1L, 1000L);
    UserPoint point = pointService.use(1L, 200L);
    //when
    List<PointHistory> history = pointService.history(point.id());
    //then
    assertThat(history.get(1).type()).isEqualTo(TransactionType.USE);
  }

  @Test
  public void _포인트는_0이하로_충전할수없다() {
    assertThatThrownBy(() -> pointService.charge(1L, -20L)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void _한번에_2000포인트이상_충전할수없다() {
    assertThatThrownBy(() -> pointService.charge(1L, 2000L)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void _포인트_2회충전() {
    //given
    pointService.charge(1L, 500L);
    pointService.charge(1L, 500L);
    //when
    UserPoint point = pointService.point(1L);
    //then
    assertThat(point.point()).isEqualTo(1000L);
  }

  @Test
  public void _포인트는_최대_10000포인트까지충전이_가능합니다() {
    //given
    userPoint.insertOrUpdate(1L, 10000L);
    //when & then
    assertThatThrownBy(() -> pointService.charge(1L, 1L)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void _0포인트_이하를_사용할_수_없다() {
    //given
    pointService.charge(1L, 1000L);
    //when&then
    assertThatThrownBy(() -> pointService.use(1L, -5L)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void _연속_사용_테스트() {
    //given
    pointService.charge(1L, 1000L);
    pointService.use(1L, 100L);
    pointService.use(1L, 100L);
    //when

    UserPoint point = pointService.point(1L);

    // then
    assertThat(point.point()).isEqualTo(800L);
  }

  @Test
  void _현재_충전된_금액보다_더_큰_포인트를_사용할_수_없다() {
    //given
    pointService.charge(1L, 200L);
    //when&then
    assertThatThrownBy(() -> pointService.use(1L, 500L)).isInstanceOf(IllegalArgumentException.class);
  }
}
