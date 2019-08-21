package com.btpn.migration.los.constant;

public class LobType {
	public static final String MUR ="MUR";
	public static final String SMES ="SMES";	
	public static final String SMEL ="SMEL";
	
	public static boolean isMur(String data) {
		return LobType.MUR.equals(data);
	}
	
	public static boolean isSmes(String data) {
		return LobType.SMES.equals(data);
	}
	
	public static boolean isSmel(String data) {
		return LobType.SMEL.equals(data);
	}
}