package kr.hhplus.be.server.dining.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.hhplus.be.server.dining.domain.model.Restaurant;

@Entity
public class RestaurantEntity {

  @Id
  @GeneratedValue
  private Long id;

  public static RestaurantEntity fromDomain(Restaurant restaurant) {
    return new RestaurantEntity();
  }

  public Restaurant toDomain() {
    return new Restaurant();
  }
}
