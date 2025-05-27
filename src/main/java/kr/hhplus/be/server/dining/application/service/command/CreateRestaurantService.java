package kr.hhplus.be.server.dining.application.service.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.command.CreateRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.command.CreateRestaurantRepository;
import kr.hhplus.be.server.dining.domain.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantService implements CreateRestaurantUseCase {
  private final CreateRestaurantRepository createRestaurantPort;

  public CreateRestaurantService(CreateRestaurantRepository createRestaurantPort) {
    this.createRestaurantPort = createRestaurantPort;
  }

  @Override
  public void execute() {
    createRestaurantPort.execute(new Restaurant());
  }
}
