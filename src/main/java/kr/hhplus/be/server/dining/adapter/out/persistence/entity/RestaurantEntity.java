package kr.hhplus.be.server.dining.adapter.out.persistence.entity;

import kr.hhplus.be.server.dining.domain.model.Restaurant;

public class RestaurantEntity {
  public static RestaurantEntity fromDomain(Restaurant restaurant) {
    return new RestaurantEntity();
  }

  public Restaurant toDomain() {
    return new Restaurant();
  }
}
