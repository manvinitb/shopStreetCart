package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dto.*;

public interface CartService {

    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO);

    public GetCartResponseDTO getFromCart(Long cartid);

    public DeleteItemResponseDTO deleteFromCart(DeleteItemRequestDTO deleteItemRequestDTO);

    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO);
}
