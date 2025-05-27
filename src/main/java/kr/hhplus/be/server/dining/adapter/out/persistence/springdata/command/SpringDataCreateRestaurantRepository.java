package kr.hhplus.be.server.dining.adapter.out.persistence.springdata.command;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.command.JpaCreateRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.command.CreateRestaurantRepository;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataCreateRestaurantRepository implements CreateRestaurantRepository {
  private final JpaCreateRestaurantRepository repository;

  public SpringDataCreateRestaurantRepository(JpaCreateRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public Restaurant execute(Restaurant restaurant) {
    return repository.save(RestaurantEntity.fromDomain(restaurant)).toDomain();
  }
}
