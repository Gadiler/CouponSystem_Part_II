/*
 * Copyright (c) // Author Gadiler 25.5.2021, 10:25.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.security;

import com.example.demo.services.interfaces.ClientService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager {

    private Map<String, Information> map;

    public String createToken(ClientService clientService) {
        Information information = new Information(System.currentTimeMillis(), clientService);
        String token = UUID.randomUUID().toString();
        map.put(token, information);
        return token;
    }

    public boolean isExist(String token) {
        return map.containsKey(token);
    }
}
