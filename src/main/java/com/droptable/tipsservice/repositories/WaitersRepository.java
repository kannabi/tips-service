package com.droptable.tipsservice.repositories;

import com.droptable.tipsservice.dao.db.Waiter;
import org.springframework.data.repository.CrudRepository;

public interface WaitersRepository extends CrudRepository<Waiter, String> {
}
