package com.shopstreet.backend.cart.restclient;

import com.shopstreet.backend.cart.restclient.dto.InventoryRequestItemDTO;
import com.shopstreet.backend.cart.restclient.dto.InventoryResponseItemDTO;
import com.shopstreet.backend.cart.restclient.dto.InventoryUpdateRequestDTO;
import com.shopstreet.backend.cart.restclient.dto.InventoryUpdateResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {
    private final String BASE_PATH = "http://10.177.1.131:8080";
    private final String UPDATE_AVAILABILITY = "/product/update-availability";
    private final String CHECK_AVAILABILITY = "/product/check-availability";

    public InventoryUpdateResponseDTO updateAvailability(InventoryUpdateRequestDTO requestDTO) {
        String uri = BASE_PATH + UPDATE_AVAILABILITY;
        RestTemplate restTemplate = new RestTemplate();
        InventoryUpdateResponseDTO responseDTO = restTemplate.postForObject(uri, requestDTO, InventoryUpdateResponseDTO.class);
        return responseDTO;
    }


    public InventoryResponseItemDTO checkAvailability(InventoryRequestItemDTO requestDTO) {
        String uri = BASE_PATH + CHECK_AVAILABILITY;
        RestTemplate restTemplate = new RestTemplate();
        InventoryResponseItemDTO responseDTO = restTemplate.postForObject(uri, requestDTO, InventoryResponseItemDTO.class);
        return responseDTO;
    }

//    public InventoryUpdateResponseDTO updateAvailability(InventoryUpdateRequestDTO requestDTO) {
//        List<InventoryResponseItemDTO> list = new ArrayList<>();
//        list.add(new InventoryResponseItemDTO(534L, true));
//        list.add(new InventoryResponseItemDTO(535L, false));
//        //list.add(new InventoryResponseItemDTO(25L, true));
//        return new InventoryUpdateResponseDTO(list);
//    }
//
//
//    public InventoryResponseItemDTO checkAvailability(InventoryRequestItemDTO requestDTO) {
////        String uri = BASE_PATH + CHECK_AVAILABILITY;
////        RestTemplate restTemplate = new RestTemplate();
////        InventoryResponseItemDTO responseDTO = restTemplate.postForObject(uri, requestDTO, InventoryResponseItemDTO.class);
//        return new InventoryResponseItemDTO(534L, true);
//    }
}
