package org.raindrop.codegen.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yhj
 * @since 2020-01-22
 */
public class DateUtil {
    private static final ZoneOffset ZONE = ZoneOffset.ofHours(8);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_TIME_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter DATE_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	public static final DateTimeFormatter HOUR_MINUTE_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	public static final DateTimeFormatter TIME_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
    public static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");
    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    /**
     * 毫秒时间戳转LocalDateTime
     *
     * @param timestamp 时间戳
     * @return 日期时间对象
     */
    public static LocalDateTime parse(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE);
    }

    /**
     * LocalDateTime转毫秒时间戳
     *
     * @param dateTime 日期时间对象
     * @return 时间戳
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return dateTime.toInstant(ZONE).toEpochMilli();
    }

    /**
     * LocalDateTime转yyy-MM-dd HH:mm:ss字符串
     *
     * @param dateTime 日期时间对象
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * LocalDateTime转指定格式字符串
     *
     * @param dateTime 日期时间对象
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("The date formatter must not be null");
        }
        return dateTime.format(dateTimeFormatter);
    }

    /**
     * yyy-MM-dd HH:mm:ss字符串转LocalDateTime
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime parse(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().length() == 0) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }

    /**
     * 按照指定格式将字符串转LocalDateTime
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime parse(String dateTimeStr, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeStr == null || dateTimeStr.trim().length() == 0) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("The date formatter must not be null");
        }
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
    }

    /**
     * Date 转换为 LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime parse(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZONE);
    }

    /**
     * LocalDateTime 转换为 Date
     *
     * @param localDateTime
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return Date.from(localDateTime.toInstant(ZONE));
    }

    /**
     * LocalDate转指定yyyy-MM-dd日期字符串
     *
     * @param date 日期对象
     */
    public static String format(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.format(DATE_FORMATTER);
    }

    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().length() == 0) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * LocalDate转指定格式字符串
     *
     * @param date              日期对象
     * @param dateTimeFormatter 格式
     */
    public static String format(LocalDate date, DateTimeFormatter dateTimeFormatter) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("The date formatter must not be null");
        }
        return date.format(dateTimeFormatter);
    }

    /**
     * 获得指定日期所在天的凌晨00:00:00或23:59:59
     *
     * @param date  指定日期
     * @param first true：00:00:00; false:23:59:59
     * @return 日期时间对象
     */
    public static LocalDateTime getStartOrEndTimeOfDay(LocalDate date, Boolean first) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        LocalTime time = LocalTime.MAX;
        if (first) {
            time = LocalTime.MIN;
        }
        return LocalDateTime.of(date, time);
    }

    /**
     * 获取指定日期所在月份的第一天或最后一天
     *
     * @param date  指定日期
     * @param first true：第一天；false: 最后一天
     * @return 日期对象
     */
    public static LocalDate getStartOrEndDayOfMonth(LocalDate date, Boolean first) {
        LocalDate resDate = LocalDate.now();
        if (date == null) {
            date = resDate;
        }
        Month month = date.getMonth();
        if (first) {
            resDate = LocalDate.of(date.getYear(), month, 1);
        } else {
            int length = month.length(date.isLeapYear());
            resDate = LocalDate.of(date.getYear(), month, length);
        }
        return resDate;
    }

    /**
     * 获取指定日期所在周的第一天或最后一天
     *
     * @param date  指定日期
     * @param first true：第一天；false: 最后一天
     * @return 日期对象
     */
    public static LocalDate getStartOrEndDayOfWeek(LocalDate date, Boolean first) {
        LocalDate resDate = LocalDate.now();
        if (date == null) {
            date = resDate;
        }
        DayOfWeek week = date.getDayOfWeek();
        int value = week.getValue();
        if (first) {
            resDate = date.minusDays(value - 1);
        } else {
            resDate = date.plusDays(7 - value);
        }
        return resDate;
    }

    /**
     * 获取连续日期集合（包含头尾）
     * example:
     *     getBetweenDate("2020-04-08", "2020-04-12");
     * result:
     *     [2020-04-08, 2020-04-09, 2020-04-10, 2020-04-11, 2020-04-12]
     * @param start 开始日期 yyyy-MM-dd 格式
     * @param end 结束日期
     * @return 日期集合
     */
    public static Set<String> getBetweenDate(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long between = ChronoUnit.DAYS.between(startDate, endDate);
        if (between < 1) {
            return Stream.of(start, end).collect(Collectors.toSet());
        }

        return Stream.iterate(startDate, e -> e.plusDays(1))
                .limit(between + 1)
                .map(LocalDate::toString)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
