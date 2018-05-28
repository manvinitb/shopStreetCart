package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.CreateOrderRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.CreateOrderResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final String BASE_PATH = "http://localhost:3000";
    private final String CREATE_ORDER_API = BASE_PATH + "/v1/oms/order/create";
    private RestTemplate restTemplate = new RestTemplate();

    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO requestDTO) {


        CreateOrderResponseDTO responseDTO = null;
        try {
            responseDTO = restTemplate.postForObject(CREATE_ORDER_API, requestDTO, CreateOrderResponseDTO.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw e;
        }
        return responseDTO;
    }
}
