package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.CreateOrderRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.CreateOrderResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final String BASE_PATH = "http://localhost:3000";
    private final String CREATE_ORDER_API = "/v1/oms/order/create";

    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO requestDTO) {
        String uri = BASE_PATH + CREATE_ORDER_API;
        RestTemplate restTemplate = new RestTemplate();
        CreateOrderResponseDTO responseDTO = restTemplate.postForObject(uri, requestDTO, CreateOrderResponseDTO.class);
        return responseDTO;
    }
}
