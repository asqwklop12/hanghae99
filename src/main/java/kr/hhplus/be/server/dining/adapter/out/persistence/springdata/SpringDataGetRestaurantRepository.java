package kr.hhplus.be.server.dining.adapter.out.persistence.springdata;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.JpaGetRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.GetRestaurantRepository;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataGetRestaurantRepository implements GetRestaurantRepository {
  private final JpaGetRestaurantRepository repository;

  public SpringDataGetRestaurantRepository(JpaGetRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return new Restaurant();
  }
}
