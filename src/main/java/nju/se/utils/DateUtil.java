package nju.se.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jh
 */
public class DateUtil {
    public static final String PARSE_ERROR = "格式错误";
    public static final String FORMAT_ERROR = "格式转换失败";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CHRON_FORMAT = "MM-dd May yyyy";

    /**
     * 将yyyy格式的字符串转换为时间
     *
     * @param str yyyy格式
     * @return LocalDateTime格式的时间对象
     * @throws DateException 格式错误异常
     */
    public static LocalDateTime parseYear(String str) throws DateException {
        SimpleDateFormat f = new SimpleDateFormat(YEAR_FORMAT);
        Date date;
        try {
            date = f.parse(str);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (ParseException e) {
            throw new DateException(FORMAT_ERROR + " : " + str + "不符合 " + YEAR_FORMAT + " 格式");
        }
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为时间
     *
     * @param str yyyy-MM-dd格式
     * @return LocalDateTime格式的时间对象
     * @throws DateException 格式错误异常
     */
    public static LocalDateTime parseDate(String str) throws DateException {
        SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = f.parse(str);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (ParseException e) {
            throw new DateException(FORMAT_ERROR + " : " + str + "不符合 " + DATE_FORMAT + " 格式");
        }
    }


    /**
     * 将HH:mm:ss格式的字符串转换为时间
     *
     * @param str HH:mm:ss格式
     * @return LocalDateTime格式的时间对象
     * @throws DateException 格式错误异常
     */
    public static LocalDateTime parseTime(String str) throws DateException {
        SimpleDateFormat f = new SimpleDateFormat(TIME_FORMAT);
        Date date;
        try {
            date = f.parse(str);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (ParseException e) {
            throw new DateException(FORMAT_ERROR + " : " + str + "不符合 " + DATE_FORMAT + " 格式");
        }
    }

    /**
     * todo: 将Chron格式的字符串转换为时间，暂时只记录年份
     *
     * @param chronDate chron格式，有"16-24 May 2015"和"May 27 2018-June 3 2018"两种
     * @return LocalDateTime格式的时间对象
     * @throws DateException 格式错误异常
     */
    public static LocalDateTime parseChron(String chronDate) {
        String year = chronDate.substring(chronDate.length() - 5);
        return parseYear(year);
    }

    /**
     * 将LocalDateTime对象转换为"yyyy"格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @return "yyyy"格式字符串
     */
    public static String toStringYear(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YEAR_FORMAT);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    /**
     * 将LocalDateTime对象转换为"yyyy-MM"格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @return "yyyy-MM"格式字符串
     */
    public static String toStringMonth(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MONTH_FORMAT);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    /**
     * 将LocalDateTime对象转换为"yyyy-MM-dd"格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @return "yyyy-MM-dd"格式字符串
     */
    public static String toStringDate(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    /**
     * 将LocalDateTime对象转换为"HH:mm:ss"格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @return "HH:mm:ss"格式字符串
     */
    public static String toStringTime(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    /**
     * 将LocalDateTime对象转换为"yyyy-MM-dd HH:mm:ss"格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @return "yyyy-MM-dd HH:mm:ss"格式字符串
     */
    public static String toStringDateTime(LocalDateTime dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    /**
     * 将LocalDateT对象转换为指定格式字符串
     *
     * @param date   LocalDate时间对象
     * @return 指定格式字符串
     */
    public static String toStringDate(LocalDate date) {
        return toString(LocalDateTime.of(date, LocalTime.MIN), DATE_FORMAT);
    }

    /**
     * 将LocalDateTime对象转换为指定格式字符串
     *
     * @param dateTime LocalDateTime时间对象
     * @param format   格式字符串，如"yyyy-MM-dd HH:mm:ss"
     * @return 指定格式字符串
     */
    public static String toString(LocalDateTime dateTime, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return formatter.format(dateTime);
        } catch (Exception e) {
            throw new DateException(PARSE_ERROR);
        }
    }

    public static class DateException extends RuntimeException {
        public DateException(String msg) {
            super(msg);
        }
    }

}
