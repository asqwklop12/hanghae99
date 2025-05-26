package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.application.port.out.repository.SearchRestaurantPort;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class SearchJpaRestaurantAdapter implements SearchRestaurantPort {
  private final SearchJpaRestaurantRepository repository;

  public SearchJpaRestaurantAdapter(SearchJpaRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute() {
    repository.findById(1L);
    return new Restaurant();
  }
}
