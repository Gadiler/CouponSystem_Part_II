/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.beans.Coupon;
import com.example.demo.services.interfaces.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int id) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public Coupon getSingleCoupon(int id) {
        return null;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
