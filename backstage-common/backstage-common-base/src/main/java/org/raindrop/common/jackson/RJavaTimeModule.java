package org.raindrop.common.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.deser.key.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.key.ZonedDateTimeKeySerializer;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * java 8 时间默认序列化
 *
 * @author L.cm
 * @author lishanbu
 */
public class RJavaTimeModule extends SimpleModule {

	public RJavaTimeModule() {
		super(PackageVersion.VERSION);

		// 普通时间类型，默认序列化方式
		this.addDeserializer(Instant.class, InstantDeserializer.INSTANT);
		this.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
		this.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);
		this.addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
		this.addDeserializer(MonthDay.class, MonthDayDeserializer.INSTANCE);
		this.addDeserializer(OffsetTime.class, OffsetTimeDeserializer.INSTANCE);
		this.addDeserializer(Period.class, JSR310StringParsableDeserializer.PERIOD);
		this.addDeserializer(Year.class, YearDeserializer.INSTANCE);
		this.addDeserializer(YearMonth.class, YearMonthDeserializer.INSTANCE);
		this.addDeserializer(ZoneId.class, JSR310StringParsableDeserializer.ZONE_ID);
		this.addDeserializer(ZoneOffset.class, JSR310StringParsableDeserializer.ZONE_OFFSET);
		this.addSerializer(Duration.class, DurationSerializer.INSTANCE);
		this.addSerializer(Instant.class, InstantSerializer.INSTANCE);
		this.addSerializer(MonthDay.class, MonthDaySerializer.INSTANCE);
		this.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
		this.addSerializer(OffsetTime.class, OffsetTimeSerializer.INSTANCE);
		this.addSerializer(Period.class, new ToStringSerializer(Period.class));
		this.addSerializer(Year.class, YearSerializer.INSTANCE);
		this.addSerializer(YearMonth.class, YearMonthSerializer.INSTANCE);
		this.addSerializer(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE);
		this.addSerializer(ZoneId.class, new ZoneIdSerializer());
		this.addSerializer(ZoneOffset.class, new ToStringSerializer(ZoneOffset.class));
		this.addKeySerializer(ZonedDateTime.class, ZonedDateTimeKeySerializer.INSTANCE);
		this.addKeyDeserializer(Duration.class, DurationKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(MonthDay.class, MonthDayKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(OffsetDateTime.class, OffsetDateTimeKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(OffsetTime.class, OffsetTimeKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(Period.class, PeriodKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(Year.class, YearKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(YearMonth.class, YearMonthKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(ZonedDateTime.class, ZonedDateTimeKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(ZoneId.class, ZoneIdKeyDeserializer.INSTANCE);
		this.addKeyDeserializer(ZoneOffset.class, ZoneOffsetKeyDeserializer.INSTANCE);
		// ======================= 时间序列化规则 ===============================
		// yyyy-MM-dd HH:mm:ss
		this.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
		// yyyy-MM-dd
		this.addSerializer(LocalDate.class,
				new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
		// HH:mm:ss
		this.addSerializer(LocalTime.class,
				new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));

		// ======================= 时间反序列化规则 ==============================
		// yyyy-MM-dd HH:mm:ss
		this.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
		// yyyy-MM-dd
		this.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
		// HH:mm:ss
		this.addDeserializer(LocalTime.class,
				new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
	}

}
