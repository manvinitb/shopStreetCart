package com.shopstreet.backend.cart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddItemRequestDTO {
    private Long cartid;
    private Long pid;
    private Long mid;
    private Long qty;

    private Double price;

    @Override
    public String toString() {
        return "AddItemRequestDTO{" +
                "cartid=" + cartid +
                ", pid=" + pid +
                ", mid=" + mid +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
