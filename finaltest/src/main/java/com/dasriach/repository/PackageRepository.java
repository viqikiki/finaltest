package com.dasriach.repository;


import com.dasriach.model.Package;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PackageRepository extends CrudRepository<Package, String> {
    Package findByToken(String id);
}
