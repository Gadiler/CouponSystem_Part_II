/*
 * Copyright (c) // Author Gadiler 25.5.2021, 10:25.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.security;

import com.example.demo.services.interfaces.ClientService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager {

    private final Map<String, Information> map = new HashMap<>();

    public String createToken(ClientService clientService) {
        Information information = new Information(System.currentTimeMillis(), clientService);
        String token = UUID.randomUUID().toString();
        map.put(token, information);
        return token;
    }

    //TODO: Need to replace to other method, check if CurrentTimeMillis is before X time.
    public boolean isExist(String token) {
        if(map.containsKey(token)) {
            map.get(token).setCurrentTimeMillis(System.currentTimeMillis());
            return true;
        }return false;
    }
}
