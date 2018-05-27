package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.CatalogItemRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.CatalogItemResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogClient {
    private final String BASE_PATH = "http://localhost:3000";
    private final String PRODUCT_DETAILS_API = "/product/%d/detail";

    public CatalogItemResponseDTO createOrder(CatalogItemRequestDTO requestDTO) {
        String uri = BASE_PATH + String.format(PRODUCT_DETAILS_API, requestDTO.getProductID());
        RestTemplate restTemplate = new RestTemplate();
        CatalogItemResponseDTO responseDTO = restTemplate.postForObject(uri, requestDTO, CatalogItemResponseDTO.class);
        return responseDTO;
    }
}
