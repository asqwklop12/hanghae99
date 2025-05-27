package kr.hhplus.be.server.dining.adapter.out.persistence.jpa.query;

import kr.hhplus.be.server.dining.adapter.out.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//이거는 DSLQL 로변경예정
public interface JpaSearchRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
