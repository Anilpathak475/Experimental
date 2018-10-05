package com.bitnudge.ime.moneygram.libs;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by anilpathak on 03/11/17.
 */

public class CalenderUtil {

    public static Date getDateFromString(String date) {
        String dateFormat = "yyyy-MM-dd'T'HH:mmZ";
        DateFormat df = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            return df.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFormatedDateFromDate(String date) {
        Date convertedDate = getDateFromString(date);
        String formatDate = "dd MMM yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatDate, Locale.ENGLISH);
        return dateFormatter.format(convertedDate);
    }

    public static long getDifferenceBetweenDates(Date startDate, Date endDate) {
        long diff = startDate.getTime() - endDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}