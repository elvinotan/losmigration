package com.btpn.migration.los.constant;

public class Sheet {
	public static final String Home = "Home";
	public static final String Informasi_Debitur = "Informasi Debitur";
	public static final String Order_Appraisal = "Order appraisal";
	public static final String Oder_BI_checking = "Oder BI checking";
	public static final String Supplier_Checking = "Supplier Checking";
	public static final String Buyer_Checking = "Buyer Checking";
	public static final String Analisa_Rek_Koran = "Analisa Rek Koran";
	public static final String Spreading = "Spreading";
	public static final String Summary_Spreading = "Summary Spreading";
	public static final String Fin_Needs_Analysis = "Fin. Needs Analysis";
	public static final String RAC = "RAC";
	public static final String MKK  = "MKK";
	public static final String Pelaporan_BI = "Pelaporan BI";
	public static final String Sandi_BI_Existing_Debitur = "Sandi BI Existing Debitur";
	public static final String Surat_Penawaran = "Surat Penawaran";
	public static final String Order_Notaris = "Order Notaris";
	
	public static final String[] sheets = new String [] {Home, Informasi_Debitur, Order_Appraisal, Oder_BI_checking, Supplier_Checking, Buyer_Checking, Analisa_Rek_Koran, Spreading, Summary_Spreading, Fin_Needs_Analysis, RAC, MKK, Pelaporan_BI, Sandi_BI_Existing_Debitur, Surat_Penawaran, Order_Notaris};

	public static boolean inSheet(String sheet) {
		for(String s : sheets) {
			if (s.equalsIgnoreCase(sheet)) { return true; }
		}
		
		return false;
	}
}
