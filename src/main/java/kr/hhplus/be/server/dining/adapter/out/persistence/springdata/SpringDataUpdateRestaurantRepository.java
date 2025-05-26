package kr.hhplus.be.server.dining.adapter.out.persistence.springdata;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.JpaUpdateRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.UpdateRestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataUpdateRestaurantRepository implements UpdateRestaurantRepository {
  private final JpaUpdateRestaurantRepository repository;

  public SpringDataUpdateRestaurantRepository(JpaUpdateRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {

  }
}
