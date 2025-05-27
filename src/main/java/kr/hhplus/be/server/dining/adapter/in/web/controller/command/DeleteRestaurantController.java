package kr.hhplus.be.server.dining.adapter.in.web.controller.command;

import kr.hhplus.be.server.dining.application.port.in.usecase.command.DeleteRestaurantUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class DeleteRestaurantController {
  private final DeleteRestaurantUseCase deleteRestaurantUseCase;

  public DeleteRestaurantController(DeleteRestaurantUseCase deleteRestaurantUseCase) {
    this.deleteRestaurantUseCase = deleteRestaurantUseCase;
  }

  public void execute(){
    deleteRestaurantUseCase.execute();
  }
}
