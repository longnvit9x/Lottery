package neo.vn.lottery;

import java.util.Calendar;
import java.util.Date;

import neo.vn.lottery.model.Triple;

public class TimeUtils {
    public static Triple getYearMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Triple(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static Calendar dateNow() {
        Calendar today = Calendar.getInstance();
        return today;
    }

    public static Calendar today() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    public static Date getDateByTime(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.getTime();
    }
}
