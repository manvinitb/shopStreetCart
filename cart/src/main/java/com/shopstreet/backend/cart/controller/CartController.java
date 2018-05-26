package com.shopstreet.backend.cart.controller;

import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.AddItemResponseDTO;
import com.shopstreet.backend.cart.dto.GetItemRequestDTO;
import com.shopstreet.backend.cart.dto.GetItemResponseDTO;
import com.shopstreet.backend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/cart")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AddItemResponseDTO addToCart(@RequestBody AddItemRequestDTO addItemRequestDTO) {
        return cartService.addToCart(addItemRequestDTO);

    }


    @RequestMapping(value = "/get/{cartid}", method = RequestMethod.GET)
    public List<GetItemResponseDTO> getCart(@PathVariable Long cartid) {
        return cartService.getFromCart(cartid);

    }

}
