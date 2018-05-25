package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.AddItemResponseDTO;
import com.shopstreet.backend.cart.dto.CartDto;

public interface CartService {

    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO);

    public boolean deleteFromCart(CartDto cartDto);
}
