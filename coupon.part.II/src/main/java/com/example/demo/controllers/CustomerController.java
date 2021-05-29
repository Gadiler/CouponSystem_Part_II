/*
 * Copyright (c) // Author Gadiler 2.5.2021, 13:08.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.User;
import com.example.demo.exceptions.*;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController extends ClientController {

    private final CustomerService customerService;
    private final LoginManager loginManager;

//    @Override
//    @PostMapping(path = "/login")
//    public boolean login(@RequestBody User user) throws DeniedAccessException {
//        return ((ClientService) customerService).login(user.getEmail(), user.getPassword());
//
////        return loginManager.register(password, password, ClientType.CUSTOMER);
////        return new ResponseEntity<String>(loginManager.register(password, password, ClientType.CUSTOMER), HttpStatus.OK);
//
//    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws DeniedAccessException {
        return new ResponseEntity<>(loginManager.register(user.getEmail(), user.getPassword(), ClientType.CUSTOMER), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon couponToAdd) throws CustomerException, CouponException, AmountException, ExpirationDate, ExistException, CompanyException {
        //TODO: 1. What service use to add Customer.
        customerService.purchaseCoupon(couponToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(customerService.getAllCoupons(), HttpStatus.OK);
    }

    @GetMapping("/{minPrice}")
    public ResponseEntity<?> getAllCouponMinPrice(@PathVariable int minPrice) {
        return new ResponseEntity<>(customerService.getAllCouponMinPrice(minPrice), HttpStatus.OK);
    }

    @GetMapping("/{maxPrice}")
    public ResponseEntity<?> getAllCouponMaxPrice(@PathVariable int maxPrice) {
        return new ResponseEntity<>(customerService.getAllCouponMaxPrice(maxPrice), HttpStatus.OK);
    }

    //TODO: Need to replace annotation to @PathParam
    @GetMapping("/category")
    public ResponseEntity<?> getAllCouponFromCategory(@PathParam("category") Category category) {
        return new ResponseEntity<>(customerService.getAllCouponFromCategory(category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCoupon(@PathVariable int id) throws CouponException {
        return new ResponseEntity<>(customerService.getSingleCoupon(id), HttpStatus.OK);
    }

    @GetMapping("/lastLogged")
    public ResponseEntity<?> getLastLoggedCompany() throws CustomerException {
        return new ResponseEntity<>(customerService.getLastLoggedCustomer(), HttpStatus.OK);
    }
}
