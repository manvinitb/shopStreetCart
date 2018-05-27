
package com.shopstreet.backend.cart.restclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequestItemDTO {
    private Long pid;
    private Long mid;
    private Long qty;
    private Double price;
}
