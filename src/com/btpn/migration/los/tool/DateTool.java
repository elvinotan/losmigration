package com.btpn.migration.los.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {
	private static String pattern = "yyyy-MM-dd HH:mm:ss:SSSS";

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String getYear(String data) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(data);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.valueOf(cal.get(Calendar.YEAR));
	}
	
	public static String getYMD(String data) throws ParseException {
		return (StringTool.isEmpty(data)) ? null : data.trim().split(" ")[0];
	}
	
	public static String getYMDInformasiDebiturAppManagement(String data) throws ParseException {
		if ("26 Juni 2018".equals(data)) return "2018-06-26";
		return (StringTool.isEmpty(data)) ? null : data.trim().split(" ")[0];
	}
	
	public static String getCurrentYMDHS() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.format(new Date());
	}
}
