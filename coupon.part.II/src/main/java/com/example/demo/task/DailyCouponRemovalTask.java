/*
 * Copyright (c) // Author Gadiler 29.4.2021, 15:48.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.task;

import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.beans.Coupon;
import com.example.demo.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyCouponRemovalTask {
    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = (1000 * 10))
    public void removeExpired() {
        System.out.println("##### Expired Coupon Collector ####");
        for (Coupon coupon : couponRepository.findAll()) {
            if (coupon.getEndDate().compareTo(DateUtils.getCurrentDate()) < 0) {
                couponRepository.delete(coupon);
            }
        }
    }
}
