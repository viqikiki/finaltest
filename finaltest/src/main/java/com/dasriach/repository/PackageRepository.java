package com.dasriach.repository;


import com.dasriach.model.Package;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PackageRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public PackageRepository(RedisTemplate redisTemplate) {
        this.hashOperations=redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void savePackage(Package pack){
        hashOperations.put("PACKAGE",pack.getMsisdn(),pack);
    }

    public List<Package> findAll(){
        return hashOperations.values("PACKAGE");
    }

    public Package findById(Integer id){
        return (Package) hashOperations.get("PACKAGE",id);

    }
    public void update(Package pack){
        savePackage(pack);
    }
    public void delete(Integer id){
        hashOperations.delete("PACKAGE",id);
    }

}