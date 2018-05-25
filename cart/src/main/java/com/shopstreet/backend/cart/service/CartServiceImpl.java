package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dao.Cart;
import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.CartDto;
import com.shopstreet.backend.cart.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean addToCart(AddItemRequestDTO addItemRequestDTO) {

        Cart cart = createModelInInitialState(addItemRequestDTO);

        if(cartRepository.findByCartidAndPidAndMid()!=null){

        }
         else{
            cartRepository.save(cart);
        }



        return true;
    }

    private Cart createModelInInitialState(AddItemRequestDTO addItemRequestDTO) {
        Cart.CartBuilder builder = Cart.builder();
        builder.cartid(addItemRequestDTO.getCartid());
        builder.mid(addItemRequestDTO.getMid());
        builder.pid(addItemRequestDTO.getPid());
        builder.qty(addItemRequestDTO.getQty());
        builder.price(String.valueOf(addItemRequestDTO.getPrice()));
        return builder.build();
    }

    @Override
    public boolean updateCart(CartDto cartDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto, cart);
        cartRepository.save(cart);
        return false;
    }

    @Override
    public boolean deleteFromCart(CartDto cartDto) {
        return false;
    }
}
