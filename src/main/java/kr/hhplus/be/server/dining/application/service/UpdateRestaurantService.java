package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.UpdateRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.UpdateRestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateRestaurantService implements UpdateRestaurantUseCase {
  private final UpdateRestaurantRepository updateRestaurantPort;

  public UpdateRestaurantService(UpdateRestaurantRepository updateRestaurantPort) {
    this.updateRestaurantPort = updateRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
