package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.CatalogItemRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.CatalogItemResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogClient {
    private final String BASE_PATH = "http://10.177.1.131:8080";
    private final String PRODUCT_DETAILS_API = BASE_PATH + "/product/get-product-for-cart/%d";
    private RestTemplate restTemplate = new RestTemplate();

    public CatalogItemResponseDTO getProductDetails(CatalogItemRequestDTO requestDTO) {


        CatalogItemResponseDTO responseDTO = restTemplate.getForObject(PRODUCT_DETAILS_API, CatalogItemResponseDTO.class);
        return responseDTO;
    }

//    public CatalogItemResponseDTO getProductDetails(CatalogItemRequestDTO requestDTO) {
//        return new CatalogItemResponseDTO(534L, "http://image", "product1");
//    }
}
