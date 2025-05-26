package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.application.port.out.repository.GetRestaurantPort;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class GetJpaRestaurantAdapter implements GetRestaurantPort {
  private final GetJpaRestaurantRepository repository;

  public GetJpaRestaurantAdapter(GetJpaRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return new Restaurant();
  }
}
