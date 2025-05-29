package kr.hhplus.be.server.dining.adapter.in.web.controller.query;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.query.SearchRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dining/restaurant")
@RequiredArgsConstructor
public class SearchRestaurantController {
  private final SearchRestaurantUseCase searchRestaurantUseCase;


  public void execute() {
    searchRestaurantUseCase.execute();
  }
}
