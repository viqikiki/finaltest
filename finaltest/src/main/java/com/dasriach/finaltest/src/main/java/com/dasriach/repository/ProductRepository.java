package com.dasriach.repository;


import com.dasriach.model.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductRedis, String> {
}
