package com.droptable.tipsservice.repositories;

import com.droptable.tipsservice.dao.db.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface RestaurantsRepository extends CrudRepository<Restaurant, String> {
public interface RestaurantsRepository extends JpaRepository<Restaurant, String> {
    public Restaurant findByLogin(String login);
}
