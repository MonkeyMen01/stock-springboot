package com.taiex.stock.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Functions {
    public static LocalDate formatDate(String dateStr) {
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));

        return LocalDate.of(year, month, day);
    }

    public static BigDecimal cleanDecimal(String value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value.replace(",", ""));
    }
}
