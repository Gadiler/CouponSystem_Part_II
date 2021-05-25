package com.example.demo.services.interfaces;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class ClientService {

    protected final CompanyRepository companyRepository;
    protected final CouponRepository couponRepository;
    protected final CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);
    public abstract boolean register(String email, String password);

    /**
     * Run through each Coupon, if coupon.companyId == companyId then update. else, delete.
     */
    protected void syncCouponToCustomer() {
        couponRepository.findAll().forEach(coupon -> {
            companyRepository.findById(coupon.getCompanyId()).ifPresentOrElse(company -> {
                if (!company.getCoupons().contains(coupon)) {
                    company.getCoupons().add(coupon);
                    companyRepository.saveAndFlush(company);
                }
            }, () -> couponRepository.deleteById(coupon.getId()));
        });
    }

}
