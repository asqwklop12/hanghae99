package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.SearchRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.SearchRestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class SearchRestaurantService implements SearchRestaurantUseCase {
  private final SearchRestaurantPort searchRestaurantPort;

  public SearchRestaurantService(SearchRestaurantPort searchRestaurantPort) {
    this.searchRestaurantPort = searchRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
