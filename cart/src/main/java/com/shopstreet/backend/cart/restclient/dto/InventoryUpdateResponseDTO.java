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
public class InventoryUpdateResponseDTO {

    private List<InventoryResponseItemDTO> stockResponseDtoList;
}
