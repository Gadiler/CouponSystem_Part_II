/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Singular
    private List<Coupon> couponList = new ArrayList<>();

}
