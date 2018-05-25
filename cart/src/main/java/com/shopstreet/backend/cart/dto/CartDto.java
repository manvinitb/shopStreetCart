package com.shopstreet.backend.cart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public class CartDto
{


    CartCompositeKey cartCompositeKey;
    private Long qty;
    private String price;


    public CartDto() {
    }

    public CartDto(CartCompositeKey cartCompositeKey, Long qty, String price) {
        this.cartCompositeKey = cartCompositeKey;
        this.qty = qty;
        this.price = price;
    }

    public CartCompositeKey getCartCompositeKey() {
        return cartCompositeKey;
    }

    public void setCartCompositeKey(CartCompositeKey cartCompositeKey) {
        this.cartCompositeKey = cartCompositeKey;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static class CartCompositeKey implements Serializable{
        protected Long cart;
        protected Long pid;
        protected Long mid;

        public CartCompositeKey(Long cart, Long pid, Long mid) {
            this.cart = cart;
            this.pid = pid;
            this.mid = mid;
        }

        public CartCompositeKey() {
        }

        public Long getCart() {
            return cart;
        }

        public Long getPid() {
            return pid;
        }

        public Long getMid() {
            return mid;
        }

    }

}
