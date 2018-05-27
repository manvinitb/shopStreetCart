package com.shopstreet.backend.cart.restclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequestDTO {
    private Long userId;
    private String cartid;
    private List<CreateOrderRequestItemDTO> items;
}