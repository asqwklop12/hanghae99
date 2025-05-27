package kr.hhplus.be.server.dining.adapter.out.persistence.jpa.command;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUpdateRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
