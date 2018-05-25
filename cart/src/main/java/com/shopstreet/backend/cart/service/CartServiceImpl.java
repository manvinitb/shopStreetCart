package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dao.Cart;
import com.shopstreet.backend.cart.dto.AddItemRequestDTO;
import com.shopstreet.backend.cart.dto.AddItemResponseDTO;
import com.shopstreet.backend.cart.dto.CartDto;
import com.shopstreet.backend.cart.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO) {

        AddItemResponseDTO addItemResponseDTO;

        Cart cart = createModelInInitialState(addItemRequestDTO);
        Cart findBycart  = cartRepository.findByCartidAndPidAndMid(cart.getCartid(),cart.getPid(),cart.getMid());
        Cart responseCart;

        if(findBycart!=null){
            cartRepository.saveQtyByCartidAndPidAndMid(cart.getQty(),cart.getCartid(),cart.getPid(),cart.getMid());
            responseCart = cartRepository.findByCartidAndPidAndMid(cart.getCartid(),cart.getPid(),cart.getMid());

        }
         else{
            responseCart = cartRepository.save(cart);
        }


        addItemResponseDTO = createAddItemResponseDTO(responseCart);


        return addItemResponseDTO;
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

    private AddItemResponseDTO createAddItemResponseDTO(Cart cart){
        AddItemResponseDTO.AddItemResponseDTOBuilder builder = AddItemResponseDTO.builder();
        builder.cartid(cart.getCartid());
        builder.mid(cart.getMid());
        builder.pid(cart.getPid());
        builder.qty(cart.getQty());
        builder.price(Double.parseDouble(cart.getPrice()));
        return builder.build();
    }


    @Override
    public boolean deleteFromCart(CartDto cartDto) {
        return false;
    }
}
