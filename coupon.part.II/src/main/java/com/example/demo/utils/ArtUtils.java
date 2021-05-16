/*
 * Copyright (c) / / @author Gadiler 4/4/2021 .
 * https://github.com/Gadiler
 */

package com.example.demo.utils;


import com.example.demo.accessingdatajpa.CompanyRepository;
import com.example.demo.accessingdatajpa.CouponRepository;
import com.example.demo.accessingdatajpa.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ArtUtils {

    public static final String BOOTSTRAP = "\n██████╗  ██████╗  ██████╗ ████████╗███████╗████████╗██████╗  █████╗ ██████╗ \n" +
            "██╔══██╗██╔═══██╗██╔═══██╗╚══██╔══╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╔╝██║   ██║██║   ██║   ██║   ███████╗   ██║   ██████╔╝███████║██████╔╝\n" +
            "██╔══██╗██║   ██║██║   ██║   ██║   ╚════██║   ██║   ██╔══██╗██╔══██║██╔═══╝ \n" +
            "██████╔╝╚██████╔╝╚██████╔╝   ██║   ███████║   ██║   ██║  ██║██║  ██║██║     \n" +
            "╚═════╝  ╚═════╝  ╚═════╝    ╚═╝   ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     \n" +
            "                                                                            ";
    public static final String COMPANY_SERVICE = "\n ██████╗ ██████╗ ███╗   ███╗██████╗  █████╗ ███╗   ██╗██╗   ██╗    ███████╗███████╗██████╗ ██╗   ██╗██╗ ██████╗███████╗\n" +
            "██╔════╝██╔═══██╗████╗ ████║██╔══██╗██╔══██╗████╗  ██║╚██╗ ██╔╝    ██╔════╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██╔════╝\n" +
            "██║     ██║   ██║██╔████╔██║██████╔╝███████║██╔██╗ ██║ ╚████╔╝     ███████╗█████╗  ██████╔╝██║   ██║██║██║     █████╗  \n" +
            "██║     ██║   ██║██║╚██╔╝██║██╔═══╝ ██╔══██║██║╚██╗██║  ╚██╔╝      ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██║     ██╔══╝  \n" +
            "╚██████╗╚██████╔╝██║ ╚═╝ ██║██║     ██║  ██║██║ ╚████║   ██║       ███████║███████╗██║  ██║ ╚████╔╝ ██║╚██████╗███████╗\n" +
            " ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝       ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝\n" +
            "                                                                                                                       ";
    public static final String CUSTOMER_SERVICE = "\n ██████╗██╗   ██╗███████╗████████╗ ██████╗ ███╗   ███╗███████╗██████╗     ███████╗███████╗██████╗ ██╗   ██╗██╗ ██████╗███████╗\n" +
            "██╔════╝██║   ██║██╔════╝╚══██╔══╝██╔═══██╗████╗ ████║██╔════╝██╔══██╗    ██╔════╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██╔════╝\n" +
            "██║     ██║   ██║███████╗   ██║   ██║   ██║██╔████╔██║█████╗  ██████╔╝    ███████╗█████╗  ██████╔╝██║   ██║██║██║     █████╗  \n" +
            "██║     ██║   ██║╚════██║   ██║   ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗    ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██║     ██╔══╝  \n" +
            "╚██████╗╚██████╔╝███████║   ██║   ╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║    ███████║███████╗██║  ██║ ╚████╔╝ ██║╚██████╗███████╗\n" +
            " ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝    ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝    ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝\n" +
            "                                                                                                                              ";
    public static final String LOGIN_MANAGER = "\n██╗      ██████╗  ██████╗ ██╗███╗   ██╗    ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗██████╗     ████████╗███████╗███████╗████████╗\n" +
            "██║     ██╔═══██╗██╔════╝ ██║████╗  ██║    ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗    ╚══██╔══╝██╔════╝██╔════╝╚══██╔══╝\n" +
            "██║     ██║   ██║██║  ███╗██║██╔██╗ ██║    ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██████╔╝       ██║   █████╗  ███████╗   ██║   \n" +
            "██║     ██║   ██║██║   ██║██║██║╚██╗██║    ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██╔══██╗       ██║   ██╔══╝  ╚════██║   ██║   \n" +
            "███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║  ██║       ██║   ███████╗███████║   ██║   \n" +
            "╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝       ╚═╝   ╚══════╝╚══════╝   ╚═╝   \n" +
            "                                                                                                                                               ";

    public static final String ADMIN_SERVICE = "\n █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗    ███████╗███████╗██████╗ ██╗   ██╗██╗ ██████╗███████╗\n" +
            "██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║    ██╔════╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██╔════╝\n" +
            "███████║██║  ██║██╔████╔██║██║██╔██╗ ██║    ███████╗█████╗  ██████╔╝██║   ██║██║██║     █████╗  \n" +
            "██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║    ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██║     ██╔══╝  \n" +
            "██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║    ███████║███████╗██║  ██║ ╚████╔╝ ██║╚██████╗███████╗\n" +
            "╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝    ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝\n" +
            "                                                                                                ";

    public static int TEST_COUNT = 1;

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    public static void testTitle(String methodName) {
        StringBuilder sb = new StringBuilder("# Test unit NO. " + TEST_COUNT++ + " Method: " + methodName + "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(sb);
    }

    public static void printSeparator() {
        StringBuilder sb = new StringBuilder("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(sb);
    }

    public void printCouponsTable() {
        TableList t1 = new TableList("Id", "CompanyId", "Category", "Start Date", "End Date", "Amount", "Price", "Image", "Description", "Title").withUnicode(false);
        couponRepository.findAll().forEach(coupon -> t1.addRow(String.valueOf(coupon.getId()), String.valueOf(coupon.getCompanyId()), String.valueOf(coupon.getCategory()), String.valueOf(coupon.getStartDate()), String.valueOf(coupon.getEndDate()), String.valueOf(coupon.getAmount()), String.valueOf(coupon.getPrice()), coupon.getImage(), coupon.getDescription(), coupon.getTitle()));
        t1.print();
    }

    public void printCompaniesTable() {
        TableList t1 = new TableList("Id", "Name", "Email", "Password").withUnicode(false);
        companyRepository.findAll().forEach(company -> t1.addRow(String.valueOf(company.getId()), company.getName(), company.getEmail(), company.getPassword()));
        t1.print();
    }

    public void printCustomersTable() {
        TableList t1 = new TableList("Id", "First Name", "Last Name", "Email", "Password");
        customerRepository.findAll().forEach(customer -> t1.addRow(String.valueOf(customer.getId()), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword()));
        t1.print();
    }

}
