package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CouponException;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    public CompanyServiceImpl(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
    }

    @Override
    public boolean login(String email, String password) {
        for (Company company : this.companyRepository.findAll()) {
            if (company.getEmail().equalsIgnoreCase(email) && company.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon couponToAdd) throws CouponException {
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getTitle().equalsIgnoreCase(couponToAdd.getTitle()) && (coupon.getCompanyId() == couponToAdd.getCompanyId())) {
                throw new CouponException("The title: " + couponToAdd.getTitle() + " is already in use!");
            }
        }
        this.couponRepository.save(couponToAdd);
    }

    @Override
    public void updateCoupon(Coupon couponToUpdate) throws CouponException {
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getId() == couponToUpdate.getId() && coupon.getCompanyId() == couponToUpdate.getCompanyId()) {
                this.couponRepository.saveAndFlush(couponToUpdate);
                return;
            }
        }
        throw new CouponException("The id: " + couponToUpdate.getId() + " isn't found!");
    }

    @Override
    public void deleteCoupon(int couponId) {
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponException {
        return this.couponRepository.findById(couponId).orElseThrow(() -> new CouponException("ID Not found!"));
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
    public List<Coupon> getAllCoupons() {
        return this.couponRepository.findAll();
    }
}
