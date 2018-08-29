package com.droptable.tipsservice.repositories;

import com.droptable.tipsservice.dao.db.Restaurant;
import com.droptable.tipsservice.dao.db.Waiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WaitersRepository extends PagingAndSortingRepository<Waiter, String> {
    public Page<Waiter> findAllByRestaurant(Restaurant restaurant, Pageable pageable);

}
