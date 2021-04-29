/*
 * Copyright (c) // Author Gadiler 29.4.2021, 15:35.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.login;

import com.example.demo.exceptions.DeniedAccessException;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CompanyService;
import com.example.demo.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws DeniedAccessException {
        switch (clientType) {
            case ADMIN:
                if (((ClientService) adminService).login(email, password)) {
                    return (ClientService) adminService;
                }
                break;
            case COMPANY:
                if (((ClientService) companyService).login(email, password)) {
                    return (ClientService) companyService;
                }
                break;

            case CUSTOMER:
                if (((ClientService) customerService).login(email, password)) {
                    return (ClientService) customerService;
                }
                break;
        }
        throw new DeniedAccessException("ACCESS DENIED!");
    }
}
