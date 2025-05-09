package com.helier.orders.orders_api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helier.orders.orders_api.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findOneByEmail(String email);
    Boolean existsByEmail(String email);

}
