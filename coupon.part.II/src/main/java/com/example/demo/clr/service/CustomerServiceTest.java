package com.example.demo.clr.service;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.ExistException;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CustomerService;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(4)
public class CustomerServiceTest implements CommandLineRunner {
    private final CustomerService customerService;
    private final ArtUtils artUtils;
    private final TestUtils testUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.CUSTOMER_SERVICE);
        artUtils.printCompaniesTable();

        ArtUtils.testTitle("Login");
        testUtils.printTestHeader("Login - Wrong Email & Password");
        System.out.println(((ClientService) customerService).login("BarneyStinson@Gmail.com", "123456"));
        ArtUtils.printSeparator();
        testUtils.printTestHeader("Login - Correct");
        System.out.println(((ClientService) customerService).login("Barney@Gmail.com", "BarneyStinson"));
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Purchase Coupon");
        testUtils.printTestHeader("Purchase Coupon -> Wrong id");
        try {
            customerService.purchaseCoupon(Coupon.builder().category(Category.FOOD).title("Drinks").description("Drinks 50% discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(20).price(5000.0).image("c://programfile").build());
        } catch (ExistException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        System.out.println(customerService.getLastLoggedCustomer());
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Purchase Coupon");
        testUtils.printTestHeader("Purchase Coupon id = 5 into last customer");
        try {
            customerService.purchaseCoupon(customerService.getSingleCoupon(5));
        } catch (ExistException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(customerService.getLastLoggedCustomer());
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get Single Coupon");
        testUtils.printTestHeader("Get Single Coupon id = 2");
        try {
            System.out.println(customerService.getSingleCoupon(2));
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with max price = 500");
        customerService.getAllCoupons().forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with min price = 500");
        customerService.getAllCouponMaxPrice(500).forEach(System.out::println);
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Get All Coupons with Category");
        testUtils.printTestHeader("Get All Coupons with Category = FOOD");
        customerService.getAllCouponFromCategory(Category.RESTAURANT).forEach(System.out::println);
        ArtUtils.printSeparator();

    }
}
