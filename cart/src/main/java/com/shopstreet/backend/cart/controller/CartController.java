package com.shopstreet.backend.cart.controller;

import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.AddItemResponseDTO;
import com.shopstreet.backend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/cart")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AddItemResponseDTO addToCart(@RequestBody AddItemRequestDTO addItemRequestDTO) {
        cartService.addToCart(addItemRequestDTO);
        return null;
    }
}
