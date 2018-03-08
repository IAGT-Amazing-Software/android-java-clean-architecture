package com.innopro.android.sample.presentation.utils;


import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getDateFromTimestampWithFormat(long time, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time*1000);
        String date = DateFormat.format(format, cal).toString();
        return date;
    }

    public static String getCurrentDateWithFormat(String format) {
        long timestamp = new Date().getTime()/1000;
        return getDateFromTimestampWithFormat(timestamp, format);
    }

    public static String getEnchantedDate(long time) {
        String date = getDateFromTimestampWithFormat(time, "d MMM yyyy");
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        int currentHours = cal.get(Calendar.HOUR);
        int currentMinutes = cal.get(Calendar.MINUTE);
        cal.setTimeInMillis(time*1000);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hoursOfDay = cal.get(Calendar.HOUR_OF_DAY);
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);

        if(currentYear == year){
            if(currentMonth == month){
                if(currentDay == day){
                    if(currentHours == hours){
                        int min = currentMinutes - minutes;
                        if(min == 0){
                            date = "Hace un momento";
                        }else{
                            String minS = String.valueOf(min);
                            date = "Hace " + minS + " minutos";
                        }

                    }else{
                        String hou = String.valueOf(currentHours - hours);
                        date = "Hace " + hou + " horas";
                    }
                }else{
                    if((currentDay - day) == 1){
                        String tim = String.valueOf(hoursOfDay) + ":" + String.valueOf(minutes);
                        date = "Ayer a las " + tim;
                    }
                }
            }
        }
        return date;
    }


}
