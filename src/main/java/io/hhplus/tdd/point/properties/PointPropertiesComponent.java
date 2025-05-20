package io.hhplus.tdd.point.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("point")
@Getter
public class PointPropertiesComponent {
  protected long availableCharge;
  protected long max;
  protected long min;
}
