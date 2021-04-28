/*
 * Copyright (c) / / Author Gadiler 5/4/2021.
 *  All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */

package com.example.demo.beans;

public enum Category {
    FOOD(0),
    ELECTRICITY(1),
    RESTAURANT(2),
    VACATION(3);

    private final int category;

    Category(int i) {
        this.category = i;
    }

    public int getCategory() {
        return category;
    }
}
