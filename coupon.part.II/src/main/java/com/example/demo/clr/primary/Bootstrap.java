package com.example.demo.clr.primary;

import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Order(1)
public class Bootstrap implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final TestUtils testUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.BOOTSTRAP);

        Company com1 = Company.builder().name("Coca Cola").email("Cola@Gmail.com").password("1234").build();
        Company com2 = Company.builder().name("Ivory").email("Ivory@Gmail.com").password("1234").build();
        Company com3 = Company.builder().name("Bug").email("Bug@Gmail.com").password("1234").build();
        Company com4 = Company.builder().name("Nestle").email("Nestle@Gmail.com").password("1234").build();
        Company com5 = Company.builder().name("Pepis").email("Pepis@Gmail.com").password("1234").build();
        Company com6 = Company.builder().name("Apple").email("Apple@Gmail.com").password("1234").build();
        Company com7 = Company.builder().name("Isrotel").email("Isrotel@Gmail.com").password("1234").build();
        Company com8 = Company.builder().name("Hotels").email("Hotels@Gmail.com").password("1234").build();
        Company com9 = Company.builder().name("McDonald's").email("McDonalds@Gmail.com").password("1234").build();
        Company com10 = Company.builder().name("Burgeranch").email("Burgeranch@Gmail.com").password("1234").build();

        Customer cs0 = Customer.builder().id(0).firstName("Ted").lastName("Mosby").email("TedM@Gmail.com").password("TedMosby").build();
        Customer cs1 = Customer.builder().id(1).firstName("Barney").lastName("Stinson").email("Barney@Gmail.com").password("BarneyStinson").build();
        Customer cs2 = Customer.builder().id(2).firstName("Marshall").lastName("Eriksen").email("Marshall@Gmail.com").password("MarshallEriksen").build();
        Customer cs3 = Customer.builder().id(3).firstName("Robin").lastName("Scherbatsky").email("Robin@Gmail.com").password("RobinScherbatsky").build();
        Customer cs4 = Customer.builder().id(4).firstName("Lily").lastName("Aldrin").email("Lily@Gmail.com").password("LilyAldrin").build();
        Customer cs5 = Customer.builder().id(5).firstName("Rachel").lastName("Green").email("TedM@Gmail.com").password("TedMosby").build();
        Customer cs6 = Customer.builder().id(6).firstName("Monica").lastName("Geller").email("Barney@Gmail.com").password("BarneyStinson").build();
        Customer cs7 = Customer.builder().id(7).firstName("Phoebe").lastName("Buffay").email("Marshall@Gmail.com").password("MarshallEriksen").build();
        Customer cs8 = Customer.builder().id(8).firstName("Ross").lastName("Geller").email("Robin@Gmail.com").password("RobinScherbatsky").build();
        Customer cs9 = Customer.builder().id(9).firstName("Joey").lastName("Tribbiani").email("Tribbiani@Gmail.com").password("Tribbiani").build();
        Customer cs10 = Customer.builder().id(10).firstName("Chandler").lastName("Bing").email("Bing@Gmail.com").password("Bing").build();

        Coupon c1 = Coupon.builder().id(1).companyId(2).category(Category.ELECTRICITY).title("Electricity").description("Electricity products discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(500.0).image("c://programfile").build();
        Coupon c2 = Coupon.builder().id(2).companyId(1).category(Category.FOOD).title("Drinks").description("Drinks discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(5000.0).image("c://programfile").build();
        Coupon c3 = Coupon.builder().id(3).companyId(4).category(Category.FOOD).title("Drinks & Chill").description("Discount on any product of Nestle").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(50.0).image("c://programfile").build();
        Coupon c4 = Coupon.builder().id(4).companyId(7).category(Category.VACATION).title("Vacation").description("Vacation discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(180.0).image("c://programfile").build();
        Coupon c5 = Coupon.builder().id(5).companyId(5).category(Category.RESTAURANT).title("Food").description("Food discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(150.0).image("c://programfile").build();
        Coupon c6 = Coupon.builder().id(6).companyId(3).category(Category.ELECTRICITY).title("Electricity").description("Electricity products discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(2500.0).image("c://programfile").build();
        Coupon c7 = Coupon.builder().id(7).companyId(10).category(Category.FOOD).title("Drinks for free").description("Drinks discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(50.0).image("c://programfile").build();
        Coupon c8 = Coupon.builder().id(8).companyId(9).category(Category.FOOD).title("Drinks on the house").description("Discount on any product of Nestle").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(50.0).image("c://programfile").build();
        Coupon c9 = Coupon.builder().id(9).companyId(8).category(Category.VACATION).title("Vacation at the most beautiful place").description("Vacation discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(1850.0).image("c://programfile").build();
        Coupon c10 = Coupon.builder().id(10).companyId(10).category(Category.RESTAURANT).title("Food 1+1").description("Food discount").startDate(DateUtils.getCurrentDate()).endDate(DateUtils.getDatePlusMonth()).amount(10).price(1150.0).image("c://programfile").build();

        ArtUtils.testTitle("Add Companies");
        testUtils.printTestHeader("Add Companies");
        companyRepository.saveAll(Arrays.asList(com1, com2, com3, com4, com5, com6, com7, com8, com9, com10));
        companyRepository.findAll().forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Coupons");
        testUtils.printTestHeader("Add Coupons");
        couponRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
        couponRepository.findAll().forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Customers");
        testUtils.printTestHeader("Add Customers");
        customerRepository.saveAll(Arrays.asList(cs0, cs1, cs2, cs3, cs4, cs5, cs6, cs7, cs8, cs9, cs10));
        customerRepository.findAll().forEach(System.out::println);
        ArtUtils.printSeparator();


    }
}
