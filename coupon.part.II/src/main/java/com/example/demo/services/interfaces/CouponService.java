/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.services.interfaces;

import com.example.demo.beans.Coupon;

import java.util.List;


public interface CouponService {
    void addCoupon(Coupon coupon);

    void deleteCoupon(int id);

    void updateCoupon(Coupon coupon);

    Coupon getSingleCoupon(int id);

    List<Coupon> getAllCoupons();
//    List<Coupon> findBy

}
