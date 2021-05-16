/*
 * Copyright (c) // Author Gadiler 2.5.2021, 15:09.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CouponException;
import com.example.demo.services.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> addCoupon(@RequestBody Coupon couponToAdd) throws CouponException, CompanyException {
        companyService.addCoupon(couponToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable int id) throws CouponException {
        //TODO: figure if that working or change from String to int.
        companyService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon couponToUpdate) throws CouponException {
        companyService.updateCoupon(couponToUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(companyService.getAllCoupons(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCoupon(@PathVariable int id) throws CouponException {
        return new ResponseEntity<>(companyService.getSingleCoupon(id), HttpStatus.OK);
    }

    @GetMapping("/lastLogged")
    public ResponseEntity<?> getLastLoggedCompany() throws CompanyException {
        return new ResponseEntity<>(companyService.getLastLoggedCompany(), HttpStatus.OK);
    }

    @GetMapping("/{maxPrice}")
    public ResponseEntity<?> getAllCouponMaxPrice(@PathVariable int maxPrice) {
        return new ResponseEntity<>(companyService.getAllCouponMaxPrice(maxPrice), HttpStatus.OK);
    }

    @GetMapping("/{minPrice}")
    public ResponseEntity<?> getAllCouponMinPrice(@PathVariable int minPrice) {
        return new ResponseEntity<>(companyService.getAllCouponMinPrice(minPrice), HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getAllCouponFromCategory(@PathParam("category") Category category) {
        return new ResponseEntity<>(companyService.getAllCouponFromCategory(category), HttpStatus.OK);
    }
}
