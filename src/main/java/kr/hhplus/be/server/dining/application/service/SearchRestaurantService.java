package kr.hhplus.be.server.dining.application.service;

import kr.hhplus.be.server.dining.application.port.in.usecase.SearchRestaurantUseCase;
import kr.hhplus.be.server.dining.application.port.out.repository.SearchRestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchRestaurantService implements SearchRestaurantUseCase {
  private final SearchRestaurantRepository searchRestaurantPort;

  public SearchRestaurantService(SearchRestaurantRepository searchRestaurantPort) {
    this.searchRestaurantPort = searchRestaurantPort;
  }

  @Override
  public void execute() {

  }
}
