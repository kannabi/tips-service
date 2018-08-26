package com.droptable.tipsservice.repositories;

import com.droptable.tipsservice.dao.Tip;
import org.springframework.data.repository.CrudRepository;

public interface TipsRepository extends CrudRepository<Tip, Long> {
}
