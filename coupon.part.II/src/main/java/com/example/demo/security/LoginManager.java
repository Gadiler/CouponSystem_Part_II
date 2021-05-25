/*
 * Copyright (c) // Author Gadiler 25.5.2021, 11:12.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.security;

import com.example.demo.exceptions.DeniedAccessException;
import com.example.demo.login.ClientType;
import com.example.demo.services.interfaces.AdminService;
import com.example.demo.services.interfaces.ClientService;
import com.example.demo.services.interfaces.CompanyService;
import com.example.demo.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

//TODO: Figure if need to replace the origin LoginManager (login.Login),
//      The only difference is return value: String-Token or relevant ClientService.

@Service
@RequiredArgsConstructor
public class LoginManager {
    private final TokenManager tokenManager;
    private final ApplicationContext ctx;
    private ClientService clientService = null;

    public String register(String email, String password, ClientType clientType) throws DeniedAccessException {
        switch (clientType) {
            case ADMIN:
                clientService = (ClientService) ctx.getBean(AdminService.class);
                break;
            case COMPANY:
                clientService = (ClientService) ctx.getBean(CompanyService.class);
                break;
            case CUSTOMER:
                clientService = (ClientService) ctx.getBean(CustomerService.class);
                break;
        }
        if (clientService.login(email, password)) {
            return tokenManager.createToken(clientService);
        }
        throw new DeniedAccessException("Unauthorized member!");
    }
}
