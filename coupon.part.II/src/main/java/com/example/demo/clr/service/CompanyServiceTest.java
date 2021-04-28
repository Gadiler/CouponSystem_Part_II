package com.example.demo.clr.service;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.exceptions.CouponException;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CompanyService;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(3)
public class CompanyServiceTest implements CommandLineRunner {

    private final CompanyService companyService;
    private final TestUtils testUtils;
    private final ArtUtils artUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.COMPANY_SERVICE);

        /*Company*/
        ArtUtils.testTitle("Login");
        testUtils.printTestHeader("Login - Wrong Email & Password");
        System.out.println(((ClientService) companyService).login("McDonaldsssss@Gmail.com", "123456"));
        ArtUtils.printSeparator();
        testUtils.printTestHeader("Login - Correct");
        System.out.println(((ClientService) companyService).login("McDonalds@Gmail.com", "1234"));
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Coupon");
        testUtils.printTestHeader("addCoupon - Wrong title & companyId");
        try {
            companyService.addCoupon(Coupon.builder().id(11).title("Food").companyId(0).description("Bla-Bla").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Coupon");
        testUtils.printTestHeader("addCoupon - Correct");
        try {
            companyService.addCoupon(Coupon.builder().id(11).title("Food & Drinks").companyId(9).description("Bla-Bla").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Coupon");
        testUtils.printTestHeader("Update Coupon - Wrong id");
        try {
            companyService.updateCoupon(Coupon.builder().id(12).title("Food & Drinks").companyId(9).description("Go go magog").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Coupon");
        testUtils.printTestHeader("Update Coupon - Correct");
        try {
            companyService.updateCoupon(Coupon.builder().id(11).title("Food & Drinks & Chill").companyId(9).description("Go go magog").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get Single Coupons id = 13 -> WRONG ID");
        try {
            System.out.println(companyService.getSingleCoupon(13));
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        //To delay the main thread
        System.out.println();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get Single Coupons id = 11");
        try {
            System.out.println(companyService.getSingleCoupon(11));
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Delete Coupon");
        testUtils.printTestHeader("Delete Coupon id = 13 -> WRONG ID");
        try {
            companyService.deleteCoupon(13);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Delete Coupon");
        testUtils.printTestHeader("Delete Coupon id = 11 -> Correct");
        try {
            companyService.deleteCoupon(11);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with max amount = 500");
        companyService.getAllCouponMaxPrice(500).forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with min amount = 500");
        companyService.getAllCouponMinPrice(500).forEach(System.out::println);
        ArtUtils.printSeparator();
        /*Company*/
    }
}
