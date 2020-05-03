package org.home.ledger.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	@SuppressWarnings("deprecation")
	public static String computeEndDate(Date fromDate) throws ParseException {
		int day = fromDate.getDate();
		int month = fromDate.getMonth();
		int year = fromDate.getYear()+1900;
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		int lastDay = c.getActualMaximum(Calendar.DATE);
		c.set(year, month, lastDay);
		Date date = c.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}
