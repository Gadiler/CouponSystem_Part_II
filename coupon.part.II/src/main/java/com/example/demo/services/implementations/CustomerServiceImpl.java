package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.*;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    private final String TAG = this.getClass().getSimpleName();
    private int lastCustomerId;

    public CustomerServiceImpl(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
        syncCouponToCustomer();
    }


    @Override
    public boolean login(String email, String password) {
        for (Customer customer : this.customerRepository.findAll()) {
            if (customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password)) {
                lastCustomerId = customer.getId();
                return true;
            }
        }
        return false;
    }

    @Override
    public void purchaseCoupon(Coupon purchaseCoupon) throws ExistException, AmountException, ExpirationDate {
        Coupon coupon = null;
        Customer customer = null;
        try {
            coupon = couponRepository.findById(purchaseCoupon.getCompanyId()).orElseThrow(() -> new CouponException("The Coupon id: " + purchaseCoupon.getId() + " NOT exist!"));
            customer = customerRepository.findById(lastCustomerId).orElseThrow(() -> new CustomerException("The Customer doesn't exist!"));
        } catch (Exception e) {
            throw new ExistException("Not EXIST!");
        }

        if (customer.getCouponList().contains(coupon)) {
            throw new ExistException(TAG + ": The customer: " + customer.getFirstName() + " already have Coupon with id: " + purchaseCoupon.getId());
        } else if (coupon.getAmount() < 1) {
            throw new AmountException();
        } else if (coupon.getEndDate().compareTo(new Date()) < 0) {
            throw new ExpirationDate(coupon);
        } else {
            coupon.setAmount(coupon.getAmount() - 1);
            couponRepository.saveAndFlush(coupon);

            customer.getCouponList().add(coupon);
            customerRepository.saveAndFlush(customer);


            //TODO: delete!
//            Company company = this.companyRepository.findById(purchaseCoupon.getCompanyId()).orElseThrow(() -> new CompanyException("The company id: " + purchaseCoupon.getCompanyId() + "NOT exist!"));
//
//            couponDAO.addCouponPurchase(customerID, purchaseCoupon.getId());
//            customer.syncCouponToCustomer();
        }
    }

    /**
     * Remove coupon from Cart
     * @param couponId
     * @throws CouponException
     */
    @Override
    public void removeCoupon(int couponId) throws CouponException {
        if(couponRepository.findById(couponId).isPresent()){
            //TODO: Remove coupon from the cart
        }
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponException {
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponException("The id NOT found!"));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        if (customerRepository.findById(lastCustomerId).isPresent()) {
            Customer customer = customerRepository.findById(lastCustomerId).get();
            return customer.getCouponList();
        }
        return null;
    }

    @Override
    public List<Coupon> getAllCouponFromCategory(Category category) {
        List<Coupon> list = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getCategory().equals(category))
                list.add(coupon);
        }
        return list;
    }

    @Override
    public List<Coupon> getAllCouponMaxPrice(int maxPrice) {
        List<Coupon> couponList = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getPrice() < maxPrice)
                couponList.add(coupon);
        }
        return couponList;
    }

    @Override
    public List<Coupon> getAllCouponMinPrice(int minPrice) {
        List<Coupon> couponList = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getPrice() > minPrice)
                couponList.add(coupon);
        }
        return couponList;
    }

    @Override
    public Customer getLastLoggedCustomer() throws CustomerException {
        return customerRepository.findById(lastCustomerId).orElseThrow(() -> new CustomerException("There isn't Last Customer!"));
    }
}
