package com.shopstreet.backend.cart.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "cart", uniqueConstraints = {@UniqueConstraint(columnNames = {"cartid","pid","mid"})})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long cartid;
    private Long pid;
    private Long mid;
    private Long qty;
    private String price;
}
