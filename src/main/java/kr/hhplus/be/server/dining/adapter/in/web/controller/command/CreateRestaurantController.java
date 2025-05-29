package kr.hhplus.be.server.dining.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.command.CreateRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dining/restaurant")
@RequiredArgsConstructor
public class CreateRestaurantController{
  private final CreateRestaurantUseCase createRestaurantUseCase;

  public void execute(){
    createRestaurantUseCase.execute();
  }
}
