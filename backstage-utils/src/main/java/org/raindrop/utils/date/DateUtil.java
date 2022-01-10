package org.raindrop.utils.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public DateUtil() {
    }

    public static String today(){
        return format(LocalDate.now(), DATE_NUMBER_FORMATTER);
    }

    public static String yesterday(){
        return format(LocalDate.now().minusDays(1l), DATE_NUMBER_FORMATTER);
    }

    public static LocalDateTime parse(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE);
    }

    public static long toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return dateTime.toInstant(ZONE).toEpochMilli();
        }
    }

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return dateTime.format(DATE_TIME_FORMATTER);
        }
    }

    public static String format(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
        if (dateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("The date formatter must not be null");
        } else {
            return dateTime.format(dateTimeFormatter);
        }
    }

    public static LocalDateTime parse(String dateTimeStr) {
        if (dateTimeStr != null && dateTimeStr.trim().length() != 0) {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static LocalDateTime parse(String dateTimeStr, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeStr != null && dateTimeStr.trim().length() != 0) {
            if (dateTimeFormatter == null) {
                throw new IllegalArgumentException("The date formatter must not be null");
            } else {
                return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            }
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static LocalDateTime parse(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return LocalDateTime.ofInstant(date.toInstant(), ZONE);
        }
    }

    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return Date.from(localDateTime.toInstant(ZONE));
        }
    }

    public static String format(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return date.format(DATE_FORMATTER);
        }
    }

    public static LocalDate parseDate(String dateStr) {
        if (dateStr != null && dateStr.trim().length() != 0) {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static String format(LocalDate date, DateTimeFormatter dateTimeFormatter) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("The date formatter must not be null");
        } else {
            return date.format(dateTimeFormatter);
        }
    }

    public static LocalDateTime getStartOrEndTimeOfDay(LocalDate date, Boolean first) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            LocalTime time = LocalTime.MAX;
            if (first) {
                time = LocalTime.MIN;
            }

            return LocalDateTime.of(date, time);
        }
    }

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

    public static LocalDate getStartOrEndDayOfWeek(LocalDate date, Boolean first) {
        LocalDate resDate = LocalDate.now();
        if (date == null) {
            date = resDate;
        }

        DayOfWeek week = date.getDayOfWeek();
        int value = week.getValue();
        if (first) {
            resDate = date.minusDays((long)(value - 1));
        } else {
            resDate = date.plusDays((long)(7 - value));
        }

        return resDate;
    }

    public static Set<String> getBetweenDate(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long between = ChronoUnit.DAYS.between(startDate, endDate);
        return between < 1L ? (Set) Stream.of(start, end).collect(Collectors.toSet()) : (Set)Stream.iterate(startDate, (e) -> {
            return e.plusDays(1L);
        }).limit(between + 1L).map(LocalDate::toString).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
