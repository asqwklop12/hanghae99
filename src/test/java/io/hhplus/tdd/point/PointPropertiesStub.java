package io.hhplus.tdd.point;

import io.hhplus.tdd.point.properties.PointProperties;

public class PointPropertiesStub extends PointProperties {
  @Override
  public long getAvailableCharge() {
    return 10000L;
  }

  @Override
  public long getMax() {
    return 2000L;
  }

  @Override
  public long getMin() {
    return 0L;
  }
}
