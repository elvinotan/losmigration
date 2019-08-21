package com.btpn.migration.los.tool;

import java.text.NumberFormat;

public class NumberTool {

	public static String format(Double number) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(2);
		return nf.format(number);
	}
	
	public static String extractNumberOnly(String data) {
		String digit = "";
		
		char[] chars = data.toCharArray();
		for (Character c : chars) {
			if (Character.isDigit(c)) {
				digit += c;
			}
		}
		
		return StringTool.isEmpty(digit.trim()) ? null : digit;
	}
}
