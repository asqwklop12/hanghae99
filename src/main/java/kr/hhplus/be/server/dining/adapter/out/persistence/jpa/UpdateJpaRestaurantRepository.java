package kr.hhplus.be.server.dining.adapter.out.persistence.jpa;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateJpaRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
