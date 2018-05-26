package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dao.Cart;
import com.shopstreet.backend.cart.dto.*;

import java.util.List;

public interface CartService {

    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO);

    public List<GetItemResponseDTO> getFromCart(Long cartid);

    public List<DeleteItemResponseDTO> deleteFromCart(DeleteItemRequestDTO deleteItemRequestDTO);
}
