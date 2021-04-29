/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.accessingdatajpa;


import com.example.demo.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
//    List<Coupon> findByIdAndPrice(int id, double price);
//
//    List<Coupon> findAllByOrderById(); //Not sure if needed
//
//    List<Coupon> findAllByOrderByIdDesc();
//
//    List<Coupon> findAllByOrderByIdAsc();
//
//    List<Coupon> findAllByAmount(int amount);
//
//    List<Coupon> findAllByCategory(Category category);
}
