package com.shopstreet.backend.cart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddItemResponseDTO {
    private Long cartid;
    private Long pid;
    private Long qty;
    private Long mid;
    private Double price;
}
