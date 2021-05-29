/*
 * Copyright (c) // Author Gadiler 2.5.2021, 18:27.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.*;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.CustomerException;
import com.example.demo.exceptions.DeniedAccessException;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.security.TokenManager;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends ClientController {

    private final AdminService adminService;
    private final LoginManager loginManager;
    private final TokenManager tokenManager;


    @Override
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody User user) throws DeniedAccessException {
        String token = loginManager.register(user.getEmail(), user.getPassword(), ClientType.ADMIN);
        System.out.println(token);
        if(((ClientService) adminService).login(user.getEmail(), user.getPassword())){
            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

//    @Override
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) throws DeniedAccessException {
//        String body = loginManager.register(user.getEmail(), user.getPassword(), ClientType.ADMIN);
//        System.out.println(body);
//        if (((ClientService) adminService).login(user.getEmail(), user.getPassword()))
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        else
//            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
//
//    }

    @PostMapping("/add/company")
    public ResponseEntity<?> addCompany(@RequestBody Company companyToAdd) throws CompanyException {
        adminService.addCompany(companyToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add/coupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon couponToAdd) throws CouponException {
        adminService.addCoupon(couponToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customerToAdd) throws CustomerException {
        adminService.addCustomer(customerToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) throws CompanyException {
        //TODO: figure if that working or change from String to int.
        adminService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        //TODO: figure if that working or change from String to int.
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCompany")
    public ResponseEntity<?> updateCompany(@RequestBody Company companyToUpdate) throws CompanyException {
        adminService.updateCompany(companyToUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customerToUpdate) throws CustomerException {
        adminService.updateCustomer(customerToUpdate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAll/companies", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws DeniedAccessException {
        if (tokenManager.isExist(token))
            return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/getAll/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(path = "getAll/Coupons", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(adminService.getAllCoupons(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCompany(@PathVariable int id) throws CompanyException {
        return new ResponseEntity<>(adminService.getSingleCompany(id), HttpStatus.OK);
    }
}
