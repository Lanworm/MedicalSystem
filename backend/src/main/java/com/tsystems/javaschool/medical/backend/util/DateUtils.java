package com.tsystems.javaschool.medical.backend.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

    private static Date getCurrentDate()
    {
        return new Date();
    }

    public static Timestamp getCurrentTimestamp()
    {
        return new Timestamp(getCurrentDate().getTime());
    }
}
