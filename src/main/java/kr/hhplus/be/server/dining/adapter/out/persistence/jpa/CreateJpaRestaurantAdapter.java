package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.dining.application.port.out.repository.CreateRestaurantPort;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class CreateJpaRestaurantAdapter implements CreateRestaurantPort {
  private final CreateJpaRestaurantRepository repository;

  public CreateJpaRestaurantAdapter(CreateJpaRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute(Restaurant restaurant) {
    return repository.save(RestaurantEntity.fromDomain(restaurant)).toDomain();
  }
}
