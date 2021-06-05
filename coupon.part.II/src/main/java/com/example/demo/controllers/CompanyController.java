/*
 * Copyright (c) // Author Gadiler 2.5.2021, 15:09.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.*;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.DeniedAccessException;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.security.TokenManager;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController extends ClientController {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final LoginManager loginManager;
    private final TokenManager tokenManager;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws DeniedAccessException {
        String token = loginManager.register(user.getEmail(), user.getPassword(), ClientType.COMPANY);
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon couponToAdd) throws CouponException, CompanyException, DeniedAccessException {
        if (tokenManager.isExist(token)) {
            companyService.addCoupon(couponToAdd);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CouponException, DeniedAccessException {
        //TODO: figure if that working or change from String to int.
        if (tokenManager.isExist(token)) {
            companyService.deleteCoupon(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon couponToUpdate) throws CouponException, DeniedAccessException {
        if (tokenManager.isExist(token)) {
            companyService.updateCoupon(couponToUpdate);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws DeniedAccessException {
        if (tokenManager.isExist(token))
            return new ResponseEntity<>(companyService.getAllCoupons(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCoupon(@PathVariable int id) throws CouponException {
        return new ResponseEntity<>(companyService.getSingleCoupon(id), HttpStatus.OK);
    }

    @GetMapping("/lastLogged")
    public ResponseEntity<?> getLastLoggedCompany(@RequestHeader(name = "Authorization") String token) throws CompanyException, DeniedAccessException {
        if (tokenManager.isExist(token))
            return new ResponseEntity<>(companyService.getLastLoggedCompany(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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

    @GetMapping("getAll/customers")
    public ResponseEntity<?> getAllMyCustomers(@RequestHeader(name = "Authorization") String token) throws DeniedAccessException {
        List<Customer> customersList = new ArrayList<>();
        if (tokenManager.isExist(token)) {
            adminService.getAllCustomers().forEach(customer -> {
                customer.getCouponList().forEach(coupon -> {
                    try {
                        if (coupon.getCompanyId() == companyService.getLastLoggedCompany().getId()) {
                            customersList.add(customer);
                        }
                    } catch (CompanyException e) {
                        System.out.println(e.getMessage());
                    }
                });
            });
            return new ResponseEntity<>(customersList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
