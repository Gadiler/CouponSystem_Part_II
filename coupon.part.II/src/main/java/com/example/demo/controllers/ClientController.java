/*
 * Copyright (c) // Author Gadiler 23.5.2021, 14:41.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.controllers;

import com.example.demo.beans.User;
import com.example.demo.exceptions.DeniedAccessException;
import org.springframework.http.ResponseEntity;

public abstract class ClientController {

    public abstract ResponseEntity<?> login(User user) throws DeniedAccessException;
}
