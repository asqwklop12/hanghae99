package kr.hhplus.be.server.dining.application.port.out.repository.command;

import kr.hhplus.be.server.dining.domain.model.Restaurant;

public interface CreateRestaurantRepository {

  Restaurant execute(Restaurant restaurant);
}
