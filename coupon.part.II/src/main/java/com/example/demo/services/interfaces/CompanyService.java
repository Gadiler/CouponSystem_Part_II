package com.example.demo.services.interfaces;

import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CouponException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    void addCoupon(Coupon couponToAdd) throws CouponException;
    void updateCoupon(Coupon couponToUpdate) throws CouponException;
    void deleteCoupon(int couponId);

    Coupon getSingleCoupon(int couponId) throws CouponException;

    List<Coupon> getAllCouponMaxPrice(int maxPrice);
    List<Coupon> getAllCouponMinPrice(int minPrice);

    List<Coupon> getAllCoupons();
}
