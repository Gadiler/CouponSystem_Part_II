package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.CustomerException;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    public AdminServiceImpl(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
        syncCouponToCustomer();
    }

    @Override
    public boolean login(String email, String password) {
        return email.equalsIgnoreCase("admin@admin.com") && password.equals("admin");
    }

    //TODO: Complete!
    @Override
    public boolean register(String email, String password) {
        return false;
    }

    /**
     * Admin service: Add company to database.
     *
     * @param companyToAdd - company to add
     * @throws CompanyException "The name might be exist.
     */
    @Override
    public void addCompany(Company companyToAdd) throws CompanyException {
        List<Company> companyList = getAllCompanies();
        for (Company comp : companyList) {
            if (comp.getName().equalsIgnoreCase(companyToAdd.getName()))
                throw new CompanyException("The name already exist!");
            if (comp.getEmail().equalsIgnoreCase(companyToAdd.getEmail()))
                throw new CompanyException("The email already exist!");
        }
        this.companyRepository.save(companyToAdd);
        syncCouponToCustomer();
    }

    //TODO: Complete the function eith more exceptions
    @Override
    public void addCoupon(Coupon couponToAdd) throws CouponException {
        if (getAllCoupons().contains(couponToAdd)) {
            throw new CouponException("The coupon already exist!");
        }
        couponRepository.save(couponToAdd);
        syncCouponToCustomer();
    }

    @Override
    public void updateCompany(Company companyToUpdate) throws CompanyException {
        for (Company comp : getAllCompanies()) {
            if (comp.getName().equalsIgnoreCase(companyToUpdate.getName()) && comp.getId() == companyToUpdate.getId()) {
                this.companyRepository.saveAndFlush(companyToUpdate);
                syncCouponToCustomer();
                return;
            }
        }
        throw new CompanyException("The Company " + companyToUpdate.getName() + " with id: " + companyToUpdate.getId() + " NOT exist to make update!");
    }

    @Override
    public void deleteCompany(int companyId) throws CompanyException {
        if (this.companyRepository.existsById(companyId)) {
            this.companyRepository.deleteById(companyId);
            syncCouponToCustomer();
        } else
            throw new CompanyException("The company id: " + companyId + " NOT exist to delete!");
    }

    @Override
    public Company getSingleCompany(int companyId) throws CompanyException {
        return this.companyRepository.findById(companyId).orElseThrow(() -> new CompanyException("ID Not found!"));
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public void addCustomer(Customer customerToAdd) throws CustomerException {
        for (Customer customer : getAllCustomers()) {
            if (customer.getEmail().equalsIgnoreCase(customerToAdd.getEmail())) {
                throw new CustomerException("The Email already in use!");
            }
        }
        this.customerRepository.save(customerToAdd);
    }

    @Override
    public void updateCustomer(Customer customerToUpdate) throws CustomerException {
        for (Customer customer : getAllCustomers()) {
            if (customer.getId() == customerToUpdate.getId()) {
                this.customerRepository.saveAndFlush(customerToUpdate);
                return;
            }
        }
        throw new CustomerException("The ID NOT exist!");
    }

    @Override
    public void deleteCustomer(int CustomerId) {
        this.customerRepository.deleteById(CustomerId);
    }

    @Override
    public Customer getSingleCustomer(int CustomerId) throws CustomerException {
        return this.customerRepository.findById(CustomerId).orElseThrow(() -> new CustomerException("ID Not found!"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return this.couponRepository.findAll();
    }

}
