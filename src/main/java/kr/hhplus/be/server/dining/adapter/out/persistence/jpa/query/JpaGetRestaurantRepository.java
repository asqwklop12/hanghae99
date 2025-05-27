package kr.hhplus.be.server.dining.adapter.out.persistence.jpa.query;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGetRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
