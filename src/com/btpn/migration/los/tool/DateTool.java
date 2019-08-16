package com.btpn.migration.los.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String getYear(String data) {
		if (data == null) return null;
		String[] array = data.split(" ");
		String[] parts = array[0].split("-");
		
		return parts[0];
	}
}
