package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dao.Cart;
import com.shopstreet.backend.cart.dto.*;
import com.shopstreet.backend.cart.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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



    @Override
    public List<GetItemResponseDTO> getFromCart(Long cartid) {

        ArrayList<Cart> cartArrayList = (ArrayList<Cart>) cartRepository.findByCartid(cartid);

        ArrayList<GetItemResponseDTO> getItemResponseDTOArrayList = new ArrayList<>();

        for(Cart cart: cartArrayList){
            getItemResponseDTOArrayList.add(createGetItemResponseDTO(cart));

        }

        return getItemResponseDTOArrayList;



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
    private GetItemResponseDTO createGetItemResponseDTO(Cart cart){
        GetItemResponseDTO.GetItemResponseDTOBuilder builder = GetItemResponseDTO.builder();
        builder.cartid(cart.getCartid());
        builder.mid(cart.getMid());
        builder.pid(cart.getPid());
        builder.qty(cart.getQty());
        builder.price(Double.parseDouble(cart.getPrice()));
        return builder.build();
    }






    @Override
    public List<DeleteItemResponseDTO> deleteFromCart(DeleteItemRequestDTO deleteItemRequestDTO ) {


        return null;
    }
}
