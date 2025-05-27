package kr.hhplus.be.server.dining.application.port.out.repository.query;

import kr.hhplus.be.server.dining.domain.model.Restaurant;

public interface SearchRestaurantRepository {

  Restaurant execute();
}
