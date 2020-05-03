package org.home.ledger.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
	public static BigDecimal round(String number) {
		Double doubleVal = Double.parseDouble(number);
		DecimalFormat format = new DecimalFormat("##.00");
		String value = format.format(doubleVal);
		return new BigDecimal(value);
	}
}
