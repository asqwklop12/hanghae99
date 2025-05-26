package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.CreateRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.CreateRestaurantPort;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantService implements CreateRestaurantUseCase {
  private final CreateRestaurantPort createRestaurantPort;

  public CreateRestaurantService(CreateRestaurantPort createRestaurantPort) {
    this.createRestaurantPort = createRestaurantPort;
  }

  @Override
  public void execute() {
    createRestaurantPort.execute(new Restaurant());
  }
}
