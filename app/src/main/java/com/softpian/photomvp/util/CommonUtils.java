package com.softpian.photomvp.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CommonUtils {

    public static String getNumberFormatted(long number) {

        return new DecimalFormat("#,###").format(number);
    }

    public static String getDateFromUnixTimestamp(String unixTimestamp) {

        Calendar calendar = Calendar.getInstance();
        Long timestamp = Long.parseLong(unixTimestamp);
        calendar.setTimeInMillis(timestamp * 1000);

        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
                .format(calendar.getTime());
    }
}
