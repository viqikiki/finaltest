package com.dasriach.repository;

import com.dasriach.model.ProductRedis;
import com.dasriach.model.Trans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepository extends CrudRepository<Trans, String> {
}
