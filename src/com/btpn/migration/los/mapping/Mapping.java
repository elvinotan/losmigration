package com.btpn.migration.los.mapping;

import java.util.List;

import com.btpn.migration.los.bean.SpecRow;

public interface Mapping {
	
	public static String MIGRATION = "MIGRATION";
	
	// Befungsi untuk melakukan mapping dari xls ke object, list of xls address untuk di ambil valuenya
	public void initMapping(String filename, String lobType);
	
	// Akan dijalankan pertama kali dgn tujuan clear data di db, krn akan melakukan migrasi ulang
	public String[] clearTable();
	
	// Ambil data dari mapping untuk fill valuenya
	public List<SpecRow> getSpecRows(String lobType);
}
