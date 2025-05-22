package io.hhplus.tdd.point;

import io.hhplus.tdd.point.properties.PointProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PointTestConfig {
  @Bean
  public PointProperties pointProperties() {
    return new PointPropertiesStub();
  }
}
