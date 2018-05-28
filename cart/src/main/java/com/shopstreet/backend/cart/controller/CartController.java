package com.shopstreet.backend.cart.controller;

import com.shopstreet.backend.cart.dto.*;
import com.shopstreet.backend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/cart")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AddItemResponseDTO addToCart(@RequestBody AddItemRequestDTO addItemRequestDTO) {
        System.out.println(addItemRequestDTO.toString());
        try {
            return cartService.addToCart(addItemRequestDTO);
        } catch (Exception e) {
            return new AddItemResponseDTO(false, "Something went wrong.");
        }

    }

    @RequestMapping(value = "/get/{cartid}", method = RequestMethod.GET)
    public GetCartResponseDTO getCart(@PathVariable Long cartid) {
        try {
            System.out.println(cartid);
            return cartService.getFromCart(cartid);

        } catch (Exception e) {
            return new GetCartResponseDTO(false, "Something went wrong.", null);
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public DeleteItemResponseDTO deleteFromCart(@RequestBody DeleteItemRequestDTO requestDTO) {
        try {
            return cartService.deleteFromCart(requestDTO);
        } catch (Exception e) {
            return new DeleteItemResponseDTO(false, "Something went wrong.");
        }
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public CheckoutResponseDTO checkout(@RequestBody CheckoutRequestDTO checkoutRequestDTO) {
        try {
            return cartService.checkout(checkoutRequestDTO);
        } catch (Exception e) {
            return new CheckoutResponseDTO(false, null, "Something went wrong.");
        }
    }
}
