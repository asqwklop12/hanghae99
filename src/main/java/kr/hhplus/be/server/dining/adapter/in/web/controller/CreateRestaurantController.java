package kr.hhplus.be.server.dining.adapter.in.web.controller;

import kr.hhplus.be.server.dining.application.port.in.usecase.CreateRestaurantUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class CreateRestaurantController{
  private final CreateRestaurantUseCase createRestaurantUseCase;

  public CreateRestaurantController(CreateRestaurantUseCase createRestaurantUseCase) {
    this.createRestaurantUseCase = createRestaurantUseCase;
  }

  public void execute(){
    createRestaurantUseCase.execute();
  }
}
