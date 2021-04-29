package com.example.demo.clr.service;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exceptions.CompanyException;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(2)
public class AdminServiceTest implements CommandLineRunner {

    private final AdminService adminService;
    private final TestUtils testUtils;
    private final ArtUtils artUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.ADMIN_SERVICE);

        /*Admin Company*/
        ArtUtils.testTitle("Login");
        testUtils.printTestHeader("Login - Wrong Email & Password");
        System.out.println(((ClientService) adminService).login("Administrator@Admin.com", "123456"));
        testUtils.printTestHeader("Login - Correct");
        System.out.println(((ClientService) adminService).login("Admin@Admin.com", "admin"));
        ArtUtils.printSeparator();


        ArtUtils.testTitle("Add Company");
        testUtils.printTestHeader("Add Company - Existing Email");
        try {
            adminService.addCompany(Company.builder().id(11).name("Samsung").email("Cola@Gmail.com").password("1234").build());
        } catch (CompanyException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Company");
        testUtils.printTestHeader("Add Company - Existing Name");
        try {
            adminService.addCompany(Company.builder().id(11).name("Coca Cola").email("Samsung@Gmail.com").password("1234").build());
        } catch (CompanyException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Company");
        testUtils.printTestHeader("Add Company - Correct");
        adminService.addCompany(Company.builder().id(11).name("Samsung").email("Samsung@Gmail.com").password("1234").build());
        adminService.getAllCompanies().forEach(System.out::println);
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Company");
        testUtils.printTestHeader("Update Company - NOT existing ID");
        try {
            adminService.updateCompany(Company.builder().id(12).name("Samsung").email("Samsung@Gmail.com").password("123456789").build());
        } catch (CompanyException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Company");
        testUtils.printTestHeader("Update Company - NOT existing Name");
        try {
            adminService.updateCompany(Company.builder().id(11).name("ShamShung").email("Samsung@Gmail.com").password("123456789").build());
        } catch (CompanyException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Company");
        testUtils.printTestHeader("Update Company - NOT existing ID");
        try {
            adminService.updateCompany(Company.builder().id(12).name("Samsung").email("Samsung@Gmail.com").password("123456789").build());
        } catch (CompanyException e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Company");
        testUtils.printTestHeader("Update Company - Correct");
        adminService.updateCompany(Company.builder().id(11).name("Samsung").email("Samsung@Gmail.com").password("123456789").build());
//        adminService.getAllCompanies().forEach(System.out::println);
        artUtils.printCompaniesTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get Single Company");
        testUtils.printTestHeader("Get Single Company with id = 11");
        System.out.println(adminService.getSingleCompany(11));
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Delete Company");
        testUtils.printTestHeader("Delete Company");
        adminService.deleteCompany(10);
        adminService.getAllCompanies().forEach(System.out::println);
        ArtUtils.printSeparator();
        artUtils.printCompaniesTable();
        ArtUtils.printSeparator();
        /*Admin Company*/

        /*Admin Customer*/
        ArtUtils.testTitle("Add Customer");
        testUtils.printTestHeader("Add Customer - Email already in use ");
        try {
            adminService.addCustomer(Customer.builder().id(11).firstName("Gadi").lastName("Engelsman").email("Barney@Gmail.com").password("12345").build());
        } catch (Exception e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Add Customer");
        testUtils.printTestHeader("Add Customer - Correct");
        adminService.addCustomer(Customer.builder().id(11).firstName("Gadi").lastName("Engelsman").email("Gadi@Gmail.com").password("12345").build());
//        adminService.getAllCustomers().forEach(System.out::println);
        artUtils.printCustomersTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Customer");
        testUtils.printTestHeader("Update Customer - ID NOT exist");
        try {
            adminService.updateCustomer(Customer.builder().id(12).firstName("Gadi").lastName("Engelsman").email("Gadi.Engelsman@Gmail.com").password("1234567").build());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Update Customer");
        testUtils.printTestHeader("Update Customer - Correct -> Update Email address");
        adminService.updateCustomer(Customer.builder().id(11).firstName("Gadi").lastName("Engelsman").email("Gadi.Engelsman@Gmail.com").password("1234567").build());
//        adminService.getAllCustomers().forEach(System.out::println);
        artUtils.printCustomersTable();
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Get Single Customer");
        testUtils.printTestHeader("Get Single Customer");
        System.out.println(adminService.getSingleCustomer(11));
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Delete Customer");
        testUtils.printTestHeader("Delete Customer");
        adminService.deleteCustomer(11);
//        adminService.getAllCustomers().forEach(System.out::println);
        artUtils.printCustomersTable();
        ArtUtils.printSeparator();
        /*Admin Customer*/

    }

}
