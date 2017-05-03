package com.csjbot.robot.base.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
    public static final String FULL_DATE_FORMAT_TMPL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_TMPL = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_TMPL = "yyyyMMdd";

    public static String hmsToDate(java.util.Date d) {
        if (d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        }
        return null;
    }

    public static java.util.Date StringToDate(String str) {
        if (("".equals(str)) || (str == null)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return date;
    }

    public static java.util.Date String2Date(String str) {
        if (StringUtil.isEmpty(str))
            return null;
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss".substring(0, str.length()));
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static java.util.Date StringToDateSendType(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
        }
        return date;
    }

    public static String DateToStringForBatch(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToStringYMDH(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToString(java.util.Date date) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToStringHMSForYD(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = "";
        str = format.format(date);
        String result = str.substring(0, 8) + "T"
                + str.substring(8, str.length());
        return result;
    }

    public static String DateToFullString(java.util.Date date) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToFullStringForBatchNo(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static java.util.Date DateToDate(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = null;
        str = format.format(date);
        return StringToDate(str);
    }

    public static String DateToStringForTB(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToStringForYDTM(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmm");
        String str = null;
        str = format.format(date);
        return str;
    }

    public static String DateToStringForTransitPC(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = null;
        str = format.format(date);
        str = str.replace("-", "");
        str = str.substring(2, str.length());
        return str;
    }

    public static java.util.Date StringToFullDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Double TwoTimeDifference(String t1, String t2) {
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d1 = sdf.parse(t1);
            d2 = sdf.parse(t2);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        long dd1 = d1.getTime();
        long dd2 = d2.getTime();
        double hms = dd2 - dd1;
        return Double.valueOf(hms);
    }

    public static String format(long ms) {
        long date = ms / 86400000L;
        long hour = (ms - (date * 60L * 60L * 1000L * 24L)) / 3600000L;
        long minute = (ms - (hour * 60L * 60L * 1000L)) / 60000L;
        long second = (ms - (hour * 60L * 60L * 1000L) - (minute * 60L * 1000L)) / 1000L;
        if (second >= 60L) {
            second %= 60L;
            minute += second / 60L;
        }
        if (minute >= 60L) {
            minute %= 60L;
            hour += minute / 60L;
        }
        if (hour >= 24L) {
            hour %= 24L;
            date += hour / 24L;
        }
        return date + "天" + hour + "小时" + minute + "分" + second + "秒";
    }

    public static int temp(String YYMM) {
        String strDate = YYMM;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = new GregorianCalendar();
        java.util.Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day = calendar.getActualMaximum(5);
        return day;
    }

    public static Long toDayLast(java.util.Date now, String endTime) {
        if (("".equals(endTime)) || (endTime == null)) {
            return Long.valueOf(-1L);
        }
        java.util.Date et = StringToDate(endTime);
        Long aa = Long.valueOf((et.getTime() - now.getTime()) / 86400000L);
        return aa;
    }

    public static java.util.Date convertDateTime(String str) {
        if (("".equals(str)) || (str == null)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static java.util.Date convertDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
