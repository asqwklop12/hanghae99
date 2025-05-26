package kr.hhplus.be.server.dining.adapter.out.persistence.springdata;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.JpaSearchRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.SearchRestaurantRepository;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataSearchJpaRestaurantRepository implements SearchRestaurantRepository {
  private final JpaSearchRestaurantRepository repository;

  public SpringDataSearchJpaRestaurantRepository(JpaSearchRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return new Restaurant();
  }
}
