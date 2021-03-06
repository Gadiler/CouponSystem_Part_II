package com.example.demo.services.implementations;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.exceptions.CouponException;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    private int lastCompanyId;

    public CompanyServiceImpl(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
        syncCouponToCustomer();
    }

    @Override
    public boolean login(String email, String password) {
        for (Company company : this.companyRepository.findAll()) {
            if (company.getEmail().equalsIgnoreCase(email) && company.getPassword().equals(password)) {
                lastCompanyId = company.getId();
                return true;
            }
        }
        return false;
    }

    //TODO: Complete!
    @Override
    public boolean register(String email, String password) {
        return false;
    }

    /**
     * Add coupon to the last logged company.
     * That why when user from the web added Coupon, the coupon added without companyId
     *
     * @param couponToAdd
     * @throws CouponException
     */
    @Override
    public void addCoupon(Coupon couponToAdd) throws CouponException, CompanyException {
        boolean flag = false;
        try {
            couponToAdd.setCompanyId(getLastLoggedCompany().getId());
        } catch (CompanyException e) {
            e.printStackTrace();
        }

        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getTitle().equalsIgnoreCase(couponToAdd.getTitle())) {
                throw new CouponException("The title: " + couponToAdd.getTitle() + " is already in use!");
            }
            if (couponToAdd.getCompanyId() == coupon.getCompanyId() && !flag) {
                flag = true;
            }
        }
        if (flag) {
            getLastLoggedCompany().getCoupons().add(couponToAdd);
            this.couponRepository.save(couponToAdd);
            syncCouponToCustomer();
            return;
        }
        throw new CouponException("The id: " + couponToAdd.getCompanyId() + " isn't found!");
    }

    @Override
    public void updateCoupon(Coupon couponToUpdate) throws CouponException {
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getId() == couponToUpdate.getId() && coupon.getCompanyId() == couponToUpdate.getCompanyId()) {
                this.couponRepository.saveAndFlush(couponToUpdate);
                syncCouponToCustomer();
                return;
            }
        }
        throw new CouponException("The id: " + couponToUpdate.getId() + " isn't found!");
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponException {
        if (couponRepository.findById(couponId).isPresent()) {
            couponRepository.deleteById(couponId);
            syncCouponToCustomer();
        } else {
            throw new CouponException("Can't find the coupon!");
        }
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
    public List<Coupon> getAllCouponFromCategory(Category category) {
        List<Coupon> couponList = new ArrayList<>();
        for (Coupon coupon : getAllCoupons()) {
            if (coupon.getCategory().equals(category))
                couponList.add(coupon);
        }
        return couponList;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        if (companyRepository.findById(lastCompanyId).isPresent()) {
            Company company = companyRepository.findById(lastCompanyId).get();
            return company.getCoupons();
        }
        return null;
    }

    @Override
    public Company getLastLoggedCompany() throws CompanyException {
        return companyRepository.findById(lastCompanyId).orElseThrow(() -> new CompanyException("There isn't Last Company!"));
    }
}
