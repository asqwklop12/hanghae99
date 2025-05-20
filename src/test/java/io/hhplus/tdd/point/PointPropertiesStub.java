package io.hhplus.tdd.point;

import io.hhplus.tdd.point.properties.PointPropertiesComponent;

public class PointPropertiesStub extends PointPropertiesComponent {
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
