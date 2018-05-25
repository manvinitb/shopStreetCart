package com.shopstreet.backend.cart.repository;

import com.shopstreet.backend.cart.dao.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    public Cart findByCartidAndPidAndMid();

}
