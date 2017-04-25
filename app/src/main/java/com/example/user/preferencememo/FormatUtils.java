package com.example.user.preferencememo;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by user on 2017-04-22.
 */

public class FormatUtils {
    private static SimpleDateFormat dateFormat;

    public static SimpleDateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy/MM/dd/kk/mm/ss", Locale.KOREA);
        }
        return dateFormat;
    }

    public static String getDateFormatString(long dateTime) {
        SimpleDateFormat format = getDateFormat();
        return format.format(dateTime);
    }

}
