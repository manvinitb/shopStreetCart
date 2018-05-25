package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.CartDto;

public interface CartService {

    public boolean addToCart(AddItemRequestDTO addItemRequestDTO);

    public boolean updateCart(CartDto cartDto);

    public boolean deleteFromCart(CartDto cartDto);
}
