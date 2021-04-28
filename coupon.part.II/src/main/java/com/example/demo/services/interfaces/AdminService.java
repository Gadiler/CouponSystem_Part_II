package com.example.demo.services.interfaces;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CustomerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void addCompany(Company companyToAdd) throws CompanyException;

    void updateCompany(Company companyToUpdate) throws CompanyException;

    void deleteCompany(int companyId) throws CompanyException;

    Company getSingleCompany(int companyId) throws CompanyException;

    List<Company> getAllCompanies();

    void addCustomer(Customer customerToAdd) throws CustomerException;

    void updateCustomer(Customer customerToUpdate) throws CustomerException;

    void deleteCustomer(int CustomerId);

    Customer getSingleCustomer(int CustomerId) throws CustomerException;

    List<Customer> getAllCustomers();

}
