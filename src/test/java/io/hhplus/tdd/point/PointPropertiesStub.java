package io.hhplus.tdd.point;

import io.hhplus.tdd.point.properties.PointPropertiesComponent;

public class PointPropertiesStub extends PointPropertiesComponent {
  private long availableCharge = 10000L;
  private long max = 2000L;
  private long min = 0L;

  @Override
  public long getAvailableCharge() {
    return availableCharge;
  }

  @Override
  public long getMax() {
    return max;
  }

  @Override
  public long getMin() {
    return min;
  }
}
