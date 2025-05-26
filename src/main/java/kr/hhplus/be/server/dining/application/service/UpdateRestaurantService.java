package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.UpdateRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.UpdateRestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateRestaurantService implements UpdateRestaurantUseCase {
  private final UpdateRestaurantPort updateRestaurantPort;

  public UpdateRestaurantService(UpdateRestaurantPort updateRestaurantPort) {
    this.updateRestaurantPort = updateRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
