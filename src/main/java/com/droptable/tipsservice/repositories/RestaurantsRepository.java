package com.droptable.tipsservice.repositories;

import com.droptable.tipsservice.dao.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantsRepository extends CrudRepository<Restaurant, String> {
}
