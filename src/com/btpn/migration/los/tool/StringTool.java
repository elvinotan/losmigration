package com.btpn.migration.los.tool;

public class StringTool {
	
	public static String combine(String ...datas) {
		String combine = ""; 
		
		for (String data : datas) {
			if (data != null) combine += (" "+data);
		}
		
		return combine.trim();
	}
	
	public static boolean isEmpty(String data) {
		return (data == null || "".equals(data));
	}
	
	public static boolean isEmptyTag(String data) {
		if (isEmpty(data)) {
			return true;
		}else {
			return "\\".equals(data.trim()) || "--".equals(data.trim()) || "-".equals(data.trim()) || "NA".toLowerCase().equals(data.trim().toLowerCase())  || "N/A".toLowerCase() .equals(data.trim().toLowerCase())  || "N / A".toLowerCase() .equals(data.trim().toLowerCase());   
		}
	}
	
	public static String cleanPhone(String phone) {
		if (phone == null) return null;
		
		int idx = phone.indexOf("E");
		int idxDot = phone.indexOf(".");
		if (idx >= 0 && idxDot == 1) {
			return phone.substring(0, idx).replaceAll("\\.", "");
		}else {
			return phone;
		}
	}
	
	public static boolean inArray(String data, String ...strings) {
		for (String s : strings) {
			if (s.equals(data)) return true;
		}
		return false;
	}
}
