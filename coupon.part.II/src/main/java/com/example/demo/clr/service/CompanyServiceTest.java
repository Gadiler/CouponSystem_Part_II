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
    private final String TAG = this.getClass().getSimpleName();
    private final CompanyService companyService;
    private final TestUtils testUtils;
    private final ArtUtils artUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.COMPANY_SERVICE + "\n");
        artUtils.printCompaniesTable();
        System.out.println("\n");

        /*Company*/
        ArtUtils.testTitle("Get Last logged company");
        testUtils.printTestHeader("Get Last logged company - No one logged already");
        try {
            System.out.println(companyService.getLastLoggedCompany());
        } catch (Exception e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Login");
        testUtils.printTestHeader("Login - Wrong Email & Password");
        System.out.println(((ClientService) companyService).login("McDonaldsssss@Gmail.com", "123456"));
        ArtUtils.printSeparator();
        testUtils.printTestHeader("Login - Correct");
        System.out.println(((ClientService) companyService).login("McDonalds@Gmail.com", "1234"));
        ArtUtils.printSeparator();
        System.out.println(companyService.getAllCoupons());

        ArtUtils.testTitle("Add Coupon");
        testUtils.printTestHeader("addCoupon - Wrong title & companyId");
        try {
            companyService.addCoupon(Coupon.builder().id(11).title("Food").companyId(0).description("Bla-Bla").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        this.companyService.getAllCoupons().forEach(System.out::println);
        ArtUtils.printSeparator();
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Coupon");
        testUtils.printTestHeader("addCoupon - Correct");
        companyService.addCoupon(Coupon.builder().id(11).title("Food & Drinks").companyId(9).description("Bla-Bla").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        this.companyService.getAllCoupons().forEach(System.out::println);
        ArtUtils.printSeparator();
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Coupon");
        testUtils.printTestHeader("Update Coupon - Wrong id");
        try {
            companyService.updateCoupon(Coupon.builder().id(21).title("Food & Drinks").companyId(9).description("Go go magog").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Coupon");
        testUtils.printTestHeader("Update Coupon - Correct");
        try {
            companyService.updateCoupon(Coupon.builder().id(8).title("Food & Drinks & Chill").companyId(9).description("Go go magog").category(Category.FOOD).amount(10).price(640).startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).image("No have").build());
        } catch (CouponException e) {
            System.err.println(e.getMessage());
        }
//        this.companyService.getAllCoupons().forEach(System.out::println);
        artUtils.printCouponsTable();
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Get Single Coupon");
        testUtils.printTestHeader("Get Single Coupons id = 13 -> WRONG ID");
        try {
            companyService.getSingleCoupon(13);
        } catch (CouponException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get Single Coupon");
        testUtils.printTestHeader("Get Single Coupon id = 9");
        System.out.println(companyService.getSingleCoupon(9));
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Delete Coupon");
        testUtils.printTestHeader("Delete Coupon id = 13 -> WRONG ID");
        try {
            companyService.deleteCoupon(13);
        } catch (CouponException e) {
            Thread.sleep(300);
            System.out.println("\n" + TAG + " " + e.getMessage() + "\n");
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
        artUtils.printCompaniesTable();
//        customerService.getAllCoupons().forEach(System.out::println);
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with max Price = 500");
        companyService.getAllCouponMaxPrice(500).forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get All Coupons");
        testUtils.printTestHeader("Get All Coupons with min Price = 500");
        companyService.getAllCouponMinPrice(500).forEach(System.out::println);
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Get All Coupons with Category");
        testUtils.printTestHeader("Get All Coupons with Category = FOOD");
        companyService.getAllCouponFromCategory(Category.FOOD).forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get Last logged company");
        testUtils.printTestHeader("Get Last logged company - Correct");
        System.out.println(companyService.getLastLoggedCompany());
        ArtUtils.printSeparator();
        /*Company*/
    }
}
