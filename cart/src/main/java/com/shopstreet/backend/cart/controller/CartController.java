package com.shopstreet.backend.cart.controller;

import com.shopstreet.backend.cart.dto.*;
import com.shopstreet.backend.cart.restclient.OrderClient;
import com.shopstreet.backend.cart.restclient.dto.CreateOrderRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.CreateOrderRequestItemDTO;
import com.shopstreet.backend.cart.restclient.dto.CreateOrderResponseDTO;
import com.shopstreet.backend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/cart")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderClient orderClient;

    {

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AddItemResponseDTO addToCart(@RequestBody AddItemRequestDTO addItemRequestDTO) {
        System.out.println(addItemRequestDTO);
        try {
            return cartService.addToCart(addItemRequestDTO);
        } catch (Exception e) {
            return new AddItemResponseDTO(false, "Something went wrong.");
        }

    }

    @RequestMapping(value = "/get/{cartid}", method = RequestMethod.GET)
    public List<GetItemResponseDTO> getCart(@PathVariable Long cartid) {
        try {
            return cartService.getFromCart(cartid);
        } catch (Exception e) {
            return null;
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public DeleteItemResponseDTO deleteFromCart(@RequestBody DeleteItemRequestDTO deleteItemRequestDTO) {
        System.out.println("Came ....." + deleteItemRequestDTO.getCartid());
        return new DeleteItemResponseDTO(true, "Deleted Succesfully");
    }


    @RequestMapping(value = "/clientTest", method = RequestMethod.GET)
    public CreateOrderResponseDTO clientTest() {
        List<CreateOrderRequestItemDTO> items = new ArrayList<>();
        items.add(new CreateOrderRequestItemDTO(1L, 2L, 3L, 4.0));
        CreateOrderRequestDTO createOrderRequestDTO = new CreateOrderRequestDTO(1L, "hsdfoisdhfiodsjf", items);
        return orderClient.createOrder(createOrderRequestDTO);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public CheckoutResponseDTO checkout(@RequestBody CheckoutRequestDTO checkoutRequestDTO) {

        // 1. cart item details - List<Items>
        // 2. stock check - inventoryClient
        // 3. filter items available
        // 4. orderClient - createOrder
        // 5. email trigger- orderId, emailId  (config put emailId and password of real email google)
        return cartService.checkout(checkoutRequestDTO);
    }


    @RequestMapping(value = "/checkout1", method = RequestMethod.POST)
    public CheckoutResponseDTO deleteFromCart(@RequestBody CheckoutRequestDTO checkoutRequestDTO) {
        System.out.println("Came........" + checkoutRequestDTO.getCartid());
        return new CheckoutResponseDTO(true, 5L, "Order Placed Succesfully");
    }


}
