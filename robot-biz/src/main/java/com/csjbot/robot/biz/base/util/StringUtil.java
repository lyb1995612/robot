package com.csjbot.robot.biz.base.util;

import java.text.DecimalFormat;
import java.util.Random;

public class StringUtil {
    static int DEFAULT_UUID_LENGTH = 32;

    private static Random randomer = new Random();

    private static char[] QUID_AlphaNumerArray = { '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z' };

    private static char[] QUID_AlphaArray = { 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static String replace(String str, String replaced,
            String replaceString) {
        if (str == null)
            return null;
        if (str.equals(""))
            return "";
        StringBuilder str_buf = new StringBuilder();
        int start = 0;
        int i = 0;
        i = str.indexOf(replaced, start);
        while (i >= 0) {
            str_buf.append(str.substring(start, i));
            str_buf.append(replaceString);
            start = i + replaced.length();
            i = str.indexOf(replaced, start);
        }
        if (start < str.length())
            str_buf.append(str.substring(start));

        return str_buf.toString();
    }

    public static String space(int value, int count) {
        return space(new StringBuilder().append(value).append("").toString(),
                '0', count);
    }

    public static String space(String str, char spacer, int count) {
        if (count <= 0)
            return "";
        if (str.length() >= count)
            return str;
        StringBuffer str_buf = new StringBuffer();
        count -= str.length();
        for (int i = 0; i < count; ++i) {
            str_buf.append(spacer);
        }
        str_buf.append(str);
        return str_buf.toString();
    }

    public static String createQUID(int length) {
        StringBuilder str_builer = new StringBuilder();
        char c = '\0';
        c = QUID_AlphaArray[randomer.nextInt(QUID_AlphaArray.length)];
        str_builer.append(c);
        for (int i = 0; i < length - 1; ++i) {
            c = QUID_AlphaNumerArray[randomer
                    .nextInt(QUID_AlphaNumerArray.length)];
            str_builer.append(c);
        }
        return str_builer.toString();
    }

    public static String createUUID() {
        return createQUID(DEFAULT_UUID_LENGTH);
    }

    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        return (str.equals(""));
    }

    public static boolean notEmpty(String str) {
        return (!(isEmpty(str)));
    }

    public static boolean bool(String str) {
        if (str == null)
            return false;
        if (str.equalsIgnoreCase("Y"))
            return true;
        if (str.equalsIgnoreCase("TRUE"))
            return true;
        if (str.equalsIgnoreCase("YES"))
            return true;
        try {
            if (Integer.parseInt(str) > 0)
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static String assemblySqlInQueryString(String listStr,
            String separator) {
        String[] strs = listStr.split(separator);
        String strsId = "";
        int count = strs.length;
        for (int i = 0; i < count; ++i) {
            String strId = strs[i];
            if (i == 0)
                strsId = new StringBuilder().append(strsId).append("'")
                        .append(strId).append("'").toString();
            else
                strsId = new StringBuilder().append(strsId).append(",'")
                        .append(strId).append("'").toString();
        }
        return strsId;
    }

    public static String getPdfHtmlTmpl() {
        StringBuffer html = new StringBuffer();
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        html.append("<head>");
        html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        html.append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;text-align：center;} .avanta {width: 180px;height: 180px;} @page{size:210mm 297mm;} </style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<p align=\"center\">&nbsp;</p>");
        html.append("<center>");
        html.append("@HTML_PDF@");
        html.append("</center>");
        html.append("</body></html>");
        return html.toString();
    }

    public static String formatNumber(String pattern, Double value) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String formatNumber(String pattern, Float value) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String escape(String str) {
        return escape(str, "%");
    }

    public static String escape(String str, String prefix) {
        StringBuffer str_buf = new StringBuffer();
        String prefix_ext = new StringBuilder().append(prefix).append("u")
                .toString();

        str_buf.ensureCapacity(str.length() * 6);
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if ((Character.isDigit(c)) || (Character.isLowerCase(c))
                    || (Character.isUpperCase(c))) {
                str_buf.append(c);
            } else if (c < 256) {
                str_buf.append(prefix);
                if (c < '\16')
                    str_buf.append("0");
                str_buf.append(Integer.toString(c, 16));
            } else {
                str_buf.append(prefix_ext);
                str_buf.append(Integer.toString(c, 16));
            }
        }
        return str_buf.toString();
    }

    public static String unescape(String str) {
        return unescape(str, "%");
    }

    public static String unescape(String str, String prefix) {
        if (str.length() <= 0)
            return str;
        StringBuffer str_buf = new StringBuffer();
        int pos = 0;
        int lastPos = 0;
        char c = '\0';

        str_buf.ensureCapacity(str.length());
        while (lastPos < str.length()) {
            pos = str.indexOf(prefix, lastPos);

            if (pos == lastPos) {
                if (str.charAt(pos + 1) == 'u') {
                    c = (char) Integer.parseInt(
                            str.substring(pos + 2, pos + 6), 16);
                    str_buf.append(c);
                    lastPos = pos + 6;
                }
                c = (char) Integer
                        .parseInt(str.substring(pos + 1, pos + 3), 16);
                str_buf.append(c);
                lastPos = pos + 3;
            }

            if (pos == -1) {
                str_buf.append(str.substring(lastPos));
                lastPos = str.length();
            }
            str_buf.append(str.substring(lastPos, pos));
            lastPos = pos;
        }

        return str_buf.toString();
    }

    public static boolean isContainModel(long modelId, String modelListStr) {
        if (isEmpty(modelListStr))
            return false;
        String[] modelArray = modelListStr.split(",");
        for (int i = 0; i < modelArray.length; ++i) {
            String modelStr = modelArray[i];
            if (isEmpty(modelStr))
                return false;
            if (modelId == Long.parseLong(modelStr))
                return true;
        }
        return false;
    }

}