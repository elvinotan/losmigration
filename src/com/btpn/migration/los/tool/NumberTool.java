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
		if (data == null) return null;
		String digit = "";
		
		char[] chars = data.toCharArray();
		for (Character c : chars) {
			if (Character.isDigit(c) || c == '.') {
				digit += c;
			}
		}
		
		return StringTool.isEmpty(digit.trim()) ? null : digit;
	}
	
	public static String handlePercentage(String percentage) {
		if (StringTool.isEmptyTag(percentage)) {
			return null;
		}else {
			percentage = percentage.replaceAll("%", "").replaceAll(",", ".").replaceAll("`", "");
			double pct = Double.valueOf(percentage);
			if (pct <= 1D) {
				percentage = NumberTool.format(pct * 100);
			}else {
				percentage = NumberTool.format(pct);
			}
			return percentage;
		}
	}
}
