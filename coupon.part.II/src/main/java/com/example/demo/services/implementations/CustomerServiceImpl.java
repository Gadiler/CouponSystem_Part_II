package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CustomerException;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements AdminService {


    public CustomerServiceImpl(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
    }

    @Override
    public void addCompany(Company companyToAdd) throws CompanyException {

    }

    @Override
    public void updateCompany(Company companyToUpdate) throws CompanyException {

    }

    @Override
    public void deleteCompany(int companyId) throws CompanyException {

    }

    @Override
    public Company getSingleCompany(int companyId) throws CompanyException {
        return null;
    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public void addCustomer(Customer customerToAdd) throws CustomerException {

    }

    @Override
    public void updateCustomer(Customer customerToUpdate) throws CustomerException {

    }

    @Override
    public void deleteCustomer(int CustomerId) {

    }

    @Override
    public Customer getSingleCustomer(int CustomerId) throws CustomerException {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
