package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dto.*;

import java.util.List;

public interface CartService {

    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO);

    public List<GetItemResponseDTO> getFromCart(Long cartid);

    public DeleteItemResponseDTO deleteFromCart(DeleteItemRequestDTO deleteItemRequestDTO);

    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO);
}
