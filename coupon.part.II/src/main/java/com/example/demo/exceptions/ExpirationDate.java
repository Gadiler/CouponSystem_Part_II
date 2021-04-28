/*
 * Copyright (c) // Author Gadiler 7/4/2021.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.exceptions;


import com.example.demo.beans.Coupon;

public class ExpirationDate extends Exception {
    public ExpirationDate(Coupon coupon) {
        super("The coupons: " + coupon.getId() + " has expired at " + coupon.getEndDate());
    }
}
