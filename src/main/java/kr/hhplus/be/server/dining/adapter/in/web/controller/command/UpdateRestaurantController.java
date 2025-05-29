package kr.hhplus.be.server.dining.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.resturant.command.UpdateRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dining/restaurant")
@RequiredArgsConstructor
public class UpdateRestaurantController {
  private final UpdateRestaurantUseCase updateRestaurantUseCase;

  public void execute() {
    updateRestaurantUseCase.execute();
  }
}
