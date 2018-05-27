package com.shopstreet.backend.cart.restclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryRequestItemDTO {
    private Long productID;
    private Long merchantID;
    private Long qty;
}
