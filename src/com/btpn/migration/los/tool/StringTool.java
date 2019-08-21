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
}
