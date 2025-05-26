package kr.hhplus.be.server.dining.adapter.out.persistence.springdata;

import kr.hhplus.be.server.dining.adapter.out.persistence.jpa.JpaDeleteRestaurantRepository;
import kr.hhplus.be.server.dining.application.port.out.repository.DeleteRestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataDeleteRestaurantRepository implements DeleteRestaurantRepository {
  private final JpaDeleteRestaurantRepository repository;

  public SpringDataDeleteRestaurantRepository(JpaDeleteRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {

  }
}
