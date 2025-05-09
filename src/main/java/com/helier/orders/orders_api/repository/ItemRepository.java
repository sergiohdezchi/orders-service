package com.helier.orders.orders_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helier.orders.orders_api.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

}
