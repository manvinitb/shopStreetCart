package com.shopstreet.backend.cart.repository;

import com.shopstreet.backend.cart.dao.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    public Cart findByCartidAndPid(Long cartid, Long pid);

    @Modifying
    @Query(value = "update cart set qty=?1 where cartid=?2 and pid=?3 and mid=?4", nativeQuery = true)
    public int saveQtyByCartidAndPidAndMid(Long qty, Long cartid, Long pid, Long mid);

    public List<Cart> findByCartid(Long cartid);

    public int deleteByCartidAndPid(Long cartid, Long pid);

    public int deleteAllByCartid(Long cartid);

    public List<Cart> findAllByCartid(Long cartid);


}
