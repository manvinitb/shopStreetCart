package com.shopstreet.backend.cart.repository;

import com.shopstreet.backend.cart.dao.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    public Cart findByCartidAndPidAndMid(Long cartid,Long pid,Long mid);

    @Modifying
    @Query(value = "update cart set qty=?1 where cartid=?2 and pid=?3 and mid=?4",nativeQuery = true)
    public int saveQtyByCartidAndPidAndMid(Long qty,Long cartid,Long pid,Long mid);

}
