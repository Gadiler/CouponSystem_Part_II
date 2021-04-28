/*
 * Copyright (c) // Author Gadiler 7/4/2021.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.exceptions;

public class AmountException extends Exception {
    public AmountException() {
        super("The amount is 0!\nSorry, maybe next time you'll succeed");
    }
}
