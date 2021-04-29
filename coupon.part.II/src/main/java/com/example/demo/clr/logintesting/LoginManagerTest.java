package com.example.demo.clr.logintesting;

import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class LoginManagerTest implements CommandLineRunner {
    private final LoginManager loginManager;
    private final TestUtils testUtils;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.LOGIN_MANAGER);

        ArtUtils.testTitle("Login - Admin");
        testUtils.printTestHeader("Login - Wrong Admin");
        try {
            loginManager.login("AdminTed@Gmail.com", "Ted", ClientType.ADMIN);
        } catch (Exception e) {
            Thread.sleep(100);
            System.err.println(e.getMessage());
        }
        ArtUtils.printSeparator();

        ArtUtils.testTitle("Login - Admin");
        testUtils.printTestHeader("Login - Correct");
        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
        adminService.getAllCustomers().forEach(System.out::println);
        ArtUtils.printSeparator();

    }
}
