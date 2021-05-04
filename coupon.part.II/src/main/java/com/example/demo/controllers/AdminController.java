/*
 * Copyright (c) // Author Gadiler 2.5.2021, 18:27.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CustomerException;
import com.example.demo.services.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add/copmany")
    public ResponseEntity<?> addCompany(@RequestBody Company companyToAdd) throws CompanyException {
        adminService.addCompany(companyToAdd);
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

    @GetMapping("/getAll/companies")
    public ResponseEntity<?> getAllCompanies() {
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/getAll/customers")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleCompany(@PathVariable int id) throws CompanyException {
        return new ResponseEntity<>(adminService.getSingleCompany(id), HttpStatus.OK);
    }

}
