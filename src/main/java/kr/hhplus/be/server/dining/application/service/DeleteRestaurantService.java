package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.DeleteRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.DeleteRestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteRestaurantService implements DeleteRestaurantUseCase {
  private final DeleteRestaurantPort deleteRestaurantPort;

  public DeleteRestaurantService(DeleteRestaurantPort deleteRestaurantPort) {
    this.deleteRestaurantPort = deleteRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
