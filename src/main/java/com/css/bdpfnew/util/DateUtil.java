/**
 * 
 */
package com.css.bdpfnew.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:25:47
 * @Description
 */
public class DateUtil {
	public static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}

	/**
	 * add lmn 获得某天最大时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		if (date == null) {
			return null;
		}
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
				ZoneId.systemDefault());
		;
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * add lmn 获得某天最小时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		if (date == null) {
			return null;
		}
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
				ZoneId.systemDefault());
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取下一年的今天日期
	 * @return
	 */
	public static Date getNextYear() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(calendar.YEAR, 1);
		Date newDate = calendar.getTime();
		return newDate;
	}

}