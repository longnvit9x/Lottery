package neo.vn.lottery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import neo.vn.lottery.model.Triple;

public class TimeUtils {
    public static final String DAY_MONTH_YEAR = "dd/MM/yyyy";

    public static String formatDateToString(Date source, String format) {
        if (source == null) {
            return null;
        }
        if (format == null || format.isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = getDateFormat(format);
        return sdf.format(source);
    }

    public static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format;
    }

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
