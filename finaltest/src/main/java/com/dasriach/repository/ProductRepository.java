package com.dasriach.repository;

import com.dasriach.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository {
    public static final String HASH_KEY = "Products";

    @Autowired
    private RedisTemplate template;

    public ProductRequest save(ProductRequest product){
        template.opsForHash().put(HASH_KEY,product.getProductId(),product);
        return product;
    }

    public List<ProductRequest> findAll(){
        System.out.println("Getting all data");
        return template.opsForHash().values(HASH_KEY);
    }

    public ProductRequest findProductById(int id){
        System.out.println("called findProductById() from DB");
        return (ProductRequest) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
