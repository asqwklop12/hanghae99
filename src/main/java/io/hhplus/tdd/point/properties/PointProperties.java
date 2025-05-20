package io.hhplus.tdd.point.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("point")
@Getter
@Setter
public class PointProperties {
  private long availableCharge;
  private long max;
  private long min;
}
