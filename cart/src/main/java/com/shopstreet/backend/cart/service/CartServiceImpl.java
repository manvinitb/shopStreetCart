package com.shopstreet.backend.cart.service;

import com.shopstreet.backend.cart.dao.Cart;
import com.shopstreet.backend.cart.dto.*;
import com.shopstreet.backend.cart.repository.CartRepository;
import com.shopstreet.backend.cart.restclient.InventoryClient;
import com.shopstreet.backend.cart.restclient.MailClient;
import com.shopstreet.backend.cart.restclient.OrderClient;
import com.shopstreet.backend.cart.restclient.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private MailClient mailClient;


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public AddItemResponseDTO addToCart(AddItemRequestDTO addItemRequestDTO) {
        AddItemResponseDTO addItemResponseDTO;
        Cart cart = createModelInInitialState(addItemRequestDTO);

        InventoryRequestItemDTO.InventoryRequestItemDTOBuilder builder = InventoryRequestItemDTO.builder();
        builder.productID(cart.getPid());
        builder.merchantID(cart.getMid());
        builder.qty(cart.getQty());
        InventoryRequestItemDTO inventoryRequestItemDTO = builder.build();


        InventoryResponseItemDTO inventoryResponseItemDTO = inventoryClient.checkAvailability(inventoryRequestItemDTO);

        if (inventoryResponseItemDTO.getAvailable() == false) {
            return new AddItemResponseDTO(false, "This item is out of stock");
        }


        Cart findBycart = cartRepository.findByCartidAndPidAndMid(cart.getCartid(), cart.getPid(), cart.getMid());
        Cart responseCart;

        if (findBycart != null) {
            findBycart.setQty(cart.getQty());
            cartRepository.save(findBycart);
            return new AddItemResponseDTO(true, "Added to cart successfully");
        } else {
            cartRepository.save(cart);
            return new AddItemResponseDTO(true, "Added to cart successfully");

        }

    }


    @Transactional
    private void updateToCart(Long qty, Long cartid, Long pid, Long mid) {
        cartRepository.saveQtyByCartidAndPidAndMid(qty, cartid, pid, mid);
    }


    @Override
    public List<GetItemResponseDTO> getFromCart(Long cartid) {
        ArrayList<Cart> cartArrayList = (ArrayList<Cart>) cartRepository.findByCartid(cartid);
        ArrayList<GetItemResponseDTO> getItemResponseDTOArrayList = new ArrayList<>();
        for (Cart cart : cartArrayList) {
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


    private GetItemResponseDTO createGetItemResponseDTO(Cart cart) {
        GetItemResponseDTO.GetItemResponseDTOBuilder builder = GetItemResponseDTO.builder();
        builder.cartid(cart.getCartid());
        builder.mid(cart.getMid());
        builder.pid(cart.getPid());
        builder.qty(cart.getQty());
        builder.price(Double.parseDouble(cart.getPrice()));
        return builder.build();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public DeleteItemResponseDTO deleteFromCart(DeleteItemRequestDTO deleteItemRequestDTO) {

        DeleteItemResponseDTO deleteItemResponseDTO;
        int rowsDeleted = cartRepository.deleteByCartidAndPid(deleteItemRequestDTO.getCartid(), deleteItemRequestDTO.getPid());

        if (rowsDeleted > 0) {
            return new DeleteItemResponseDTO(true, "Deleted Successfully");
        } else {
            return new DeleteItemResponseDTO(false, "Some Error Occured");
        }


    }

    @Override
    public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequestDTO) {
        // 1. cart item details - List<Items>
        List<Cart> cartList = cartRepository.findAllByCartid(checkoutRequestDTO.getCartid());
        // 2. stock check - inventoryClient
        // 3. filter items available
        List<Cart> availableList = updateInventory(cartList);
        if (availableList.size() == 0) {
            return new CheckoutResponseDTO(false, null, "No Items are Available!");
        }
        // 4. orderClient - createOrder
        Long orderId = createOrder(checkoutRequestDTO.getUserid(), checkoutRequestDTO.getCartid(), availableList);
        // 5. empty cart
        if (orderId == null) {
            return new CheckoutResponseDTO(false, null, "Something went wrong.");
        }
        cartRepository.deleteAllByCartid(checkoutRequestDTO.getCartid());

        // 5. email trigger- orderId, emailId  (config put emailId and password of real email google)
        try {
            mailClient.sendEmail(orderId, checkoutRequestDTO.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new CheckoutResponseDTO(true, orderId, "Order placed Successfully!");
    }

    private Long createOrder(Long userId, Long cartId, List<Cart> availableList) {
        List<CreateOrderRequestItemDTO> items = new ArrayList<>();
        for (Cart cart : availableList) {
            CreateOrderRequestItemDTO.CreateOrderRequestItemDTOBuilder builder = CreateOrderRequestItemDTO.builder();
            builder.pid(cart.getPid());
            builder.mid(cart.getMid());
            builder.qty(cart.getQty());
            builder.price(Double.parseDouble(cart.getPrice()));
            items.add(builder.build());
        }
        CreateOrderRequestDTO requestDTO = new CreateOrderRequestDTO(userId, String.valueOf(cartId), items);
        CreateOrderResponseDTO responseDTO = orderClient.createOrder(requestDTO);
        return responseDTO.getOrderid();
    }

    private List<Cart> updateInventory(List<Cart> cartList) {
        List<InventoryRequestItemDTO> itemDTOList = new ArrayList<>();
        for (Cart cart : cartList) {
            InventoryRequestItemDTO itemDTO = new InventoryRequestItemDTO(cart.getPid(), cart.getMid(), cart.getQty());
            itemDTOList.add(itemDTO);
        }

        InventoryUpdateRequestDTO requestDTO = new InventoryUpdateRequestDTO(itemDTOList);
        InventoryUpdateResponseDTO responseDTO = inventoryClient.updateAvailability(requestDTO);

        List<InventoryResponseItemDTO> itemAvailabilityList = responseDTO.getInventoryResponseItemDTOList();
        Map<Long, Boolean> availabilityMap = new HashMap<>();
        for (InventoryResponseItemDTO itemDTO : itemAvailabilityList) {
            availabilityMap.put(itemDTO.getProductID(), itemDTO.getAvailable());
        }

        List<Cart> resultList = new ArrayList<>();
        for (Cart cart : cartList) {
            if (availabilityMap.containsKey(cart.getPid()) && availabilityMap.get(cart.getPid())) {
                resultList.add(cart);
            }
        }
        return resultList;
    }


}
