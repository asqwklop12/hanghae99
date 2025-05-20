package io.hhplus.tdd.point;

import io.hhplus.tdd.point.properties.PointProperties;

public class PointPropertiesStub extends PointProperties {
  @Override
  public long getAvailableCharge() {
    return 10000L;
  }

  @Override
  public long getMaxSingleChargeAmount() {
    return 2000L;
  }


  @Override
  public long getMinSingleChargeAmount() {
    return 0L;
  }
}
