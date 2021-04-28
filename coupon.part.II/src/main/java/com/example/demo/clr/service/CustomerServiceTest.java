package com.example.demo.clr.service;

import com.example.demo.services.interfaces.CustomerService;
import com.example.demo.utils.ArtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Order(3)
public class CustomerServiceTest implements CommandLineRunner {
    private final CustomerService customerService;
    private final ArtUtils artUtils;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.CUSTOMER_SERVICE);
        artUtils.printCompaniesTable();
    }
}
