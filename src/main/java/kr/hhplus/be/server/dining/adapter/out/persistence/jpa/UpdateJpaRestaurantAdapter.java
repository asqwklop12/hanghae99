package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.application.port.out.repository.UpdateRestaurantPort;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateJpaRestaurantAdapter implements UpdateRestaurantPort {
  private final UpdateJpaRestaurantRepository repository;

  public UpdateJpaRestaurantAdapter(UpdateJpaRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {

  }
}
