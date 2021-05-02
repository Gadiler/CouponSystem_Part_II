/*
 * Copyright (c) // Author Gadiler 2.5.2021, 13:08.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.*;
import com.example.demo.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PutMapping
    public ResponseEntity<?> purchaseCoupon(Coupon couponToAdd){
        //TODO: 1. What service use to add Customer.
        // 2. Surround with try\catch or shall i add exception to signature?
        try {
            customerService.purchaseCoupon(couponToAdd);
        } catch (ExistException | AmountException | ExpirationDate | CustomerException | CouponException | CompanyException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
