package io.hhplus.tdd.point.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class LockManager {
  private Map<String, ReentrantLock> locks = new ConcurrentHashMap<>();

  public <T> T withLock(String key, Supplier<T> action) {
    ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
    lock.lock();
    try {
      return action.get();
    } finally {
      lock.unlock();
    }
  }
}
