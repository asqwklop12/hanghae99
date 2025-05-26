package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.DeleteRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.DeleteRestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteRestaurantService implements DeleteRestaurantUseCase {
  private final DeleteRestaurantRepository deleteRestaurantPort;

  public DeleteRestaurantService(DeleteRestaurantRepository deleteRestaurantPort) {
    this.deleteRestaurantPort = deleteRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
