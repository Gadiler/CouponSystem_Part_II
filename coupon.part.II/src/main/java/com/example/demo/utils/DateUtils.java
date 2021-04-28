/*
 * Copyright (c) / / Author Gadiler 4/4/2021.
 * All rights reserved to Gadi Engelsman.
 * https://github.com/Gadiler
 */

package com.example.demo.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtils {
    public static Date convert(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static java.util.Date getCurrentDate() {
        return new java.util.Date();
    }

    public static java.util.Date getDatePlusMonth() {
        return Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
