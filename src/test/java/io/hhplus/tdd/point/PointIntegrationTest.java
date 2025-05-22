package io.hhplus.tdd.point;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PointTestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PointIntegrationTest {

  @Autowired
  private PointService pointService;

  @Autowired
  private UserPointTable userPointTable;

  @Autowired
  private PointHistoryTable historyTable;


  @Test
  void 동시_충전_테스트() throws InterruptedException {
    int threadCount = 10;
    long userId = 1L;
    long amount = 100L;

    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      executor.execute(() -> {
        pointService.charge(userId, amount);
        latch.countDown();
      });
    }

    latch.await();
    UserPoint point = pointService.point(userId);
    UserPoint userPoint = userPointTable.selectById(1L);

    assertThat(point.point()).isEqualTo(userPoint.point());
  }

  @Test
  void 동시_사용_테스트() throws InterruptedException {
    //given
    int threadCount = 10;
    long userId = 1L;
    long amount = 100L;
    pointService.charge(userId, 1000L);

    //when
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      executor.execute(() -> {
        pointService.use(userId, amount);
        latch.countDown();
      });
    }

    latch.await();
    UserPoint point = pointService.point(userId);
    UserPoint userPoint = userPointTable.selectById(1L);

    //then
    assertThat(point.point()).isEqualTo(userPoint.point()); // 실패 가능성 높음
  }

}
