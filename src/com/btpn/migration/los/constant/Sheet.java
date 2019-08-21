package com.btpn.migration.los.constant;

public class Sheet {
	
	// General
	public static final String Home = "Home";
	public static final String InformasiDebitur = "Informasi Debitur";
	public static final String OrderAppraisal = "Order appraisal";	
	public static final String OderBIchecking = "Oder BI checking";	
	public static final String SupplierChecking = "Supplier Checking";	
	public static final String BuyerChecking = "Buyer Checking";
	public static final String AnalisaRekKoran = "Analisa Rek Koran";
	public static final String RAC = "RAC";
	public static final String MKK  = "MKK";
	public static final String PelaporanBI = "Pelaporan BI";
	public static final String SandiBIExistingDebitur = "Sandi BI Existing Debitur";
	public static final String SuratPenawaran = "Surat Penawaran";
	public static final String OrderNotaris = "Order Notaris";
	
	// SMEL (Large) Only Exist on Excel Large
	public static final String Spreading = "Spreading";
	public static final String SummarySpreading = "Summary Spreading";
	public static final String FinNeedsAnalysis = "Fin. Needs Analysis";
	
	// SMES (Small) Only Exist on Exccel Small
	public static final String AnalisaLapKue = "Analisa Lap Keu";
	public static final String MemoReview = "Memo Review";
	
	public static final String[] sheets = new String [] {Home, InformasiDebitur, OrderAppraisal, OderBIchecking, SupplierChecking, BuyerChecking, AnalisaRekKoran, RAC, MKK, PelaporanBI, SandiBIExistingDebitur, SuratPenawaran, OrderNotaris, Spreading, SummarySpreading, FinNeedsAnalysis, AnalisaLapKue, MemoReview};

	public static boolean inSheet(String sheet) {
		for(String s : sheets) {
			if (s.equalsIgnoreCase(sheet)) { return true; }
		}
		
		return false;
	}
}
