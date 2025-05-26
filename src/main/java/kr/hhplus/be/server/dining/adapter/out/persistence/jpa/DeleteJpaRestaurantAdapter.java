package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.application.port.out.repository.DeleteRestaurantPort;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteJpaRestaurantAdapter implements DeleteRestaurantPort {
  private final DeleteJpaRestaurantRepository repository;

  public DeleteJpaRestaurantAdapter(DeleteJpaRestaurantRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {

  }
}
