package com.example.demo.services.interfaces;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void purchaseCoupon(Coupon purchaseCoupon) throws ExistException, AmountException, ExpirationDate, CompanyException, CustomerException, CouponException;
    void removeCoupon(int couponId) throws CouponException;
    Coupon getSingleCoupon(int couponId) throws CouponException;

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCouponFromCategory(Category category);

    List<Coupon> getAllCouponMaxPrice(int maxPrice);

    List<Coupon> getAllCouponMinPrice(int minPrice);

    Customer getLastLoggedCustomer() throws CustomerException;

}
