package kr.hhplus.be.server.dining.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.command.UpdateRestaurantUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class UpdateRestaurantController {
  private final UpdateRestaurantUseCase updateRestaurantUseCase;

  public UpdateRestaurantController(UpdateRestaurantUseCase updateRestaurantUseCase) {
    this.updateRestaurantUseCase = updateRestaurantUseCase;
  }

  public void execute(){
    updateRestaurantUseCase.execute();
  }
}
