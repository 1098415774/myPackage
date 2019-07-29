package sh.base.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getDateAfterDay(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,day);
        return calendar.getTime();
    }

    public static Date getWholeDayAfterDay(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.add(Calendar.DATE,day);
        return calendar.getTime();
    }
}
