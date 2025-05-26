package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.GetRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.GetRestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class GetRestaurantService implements GetRestaurantUseCase {
  private final GetRestaurantRepository getRestaurantPort;

  public GetRestaurantService(GetRestaurantRepository getRestaurantPort) {
    this.getRestaurantPort = getRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
