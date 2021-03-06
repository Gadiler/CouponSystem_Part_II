/*
 * Copyright (c) // Author Gadiler 25.5.2021, 10:26.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.security;


import com.example.demo.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class Information {

    private long currentTimeMillis;
    private ClientService clientService;


}
