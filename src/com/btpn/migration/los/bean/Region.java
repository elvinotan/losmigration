package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class Region {
	private String region;
	private String regionCode;
	private String branch;
	private String branchCode;
	private String pmsCode;
	
	public Region(String region, String regionCode, String branch, String branchCode, String pmsCode) {
		super();
		this.region = region;
		this.regionCode = regionCode;
		this.branch = branch;
		this.branchCode = branchCode;
		this.pmsCode = pmsCode;
	}
	
	public String getRegion() { return region; }
	public void setRegion(String region) { this.region = region; }
	
	public String getRegionCode() { return regionCode; }
	public void setRegionCode(String regionCode) { this.regionCode = regionCode; }
	
	public String getBranch() { return branch; }
	public void setBranch(String branch) { this.branch = branch; }
	
	public String getBranchCode() { return branchCode; }
	public void setBranchCode(String branchCode) { this.branchCode = branchCode; }

	public String getPmsCode() { return pmsCode; }
	public void setPmsCode(String pmsCode) { this.pmsCode = pmsCode; }
	
	public static List<Region> getRegions() {
		List<Region> regions = new ArrayList<Region>();
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "BEKASI MAS 1", "0554", "10554"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "BEKASI MAS 2", "0554", "20554"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA JATINEGARA 1", "0557", "10557"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA JATINEGARA 2", "0557", "20557"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "BOGOR PADJAJARAN", "0012", "10012"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA PANGLIMA POLIM", "0396", "10396"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA KELAPA GADING", "0449", "10449"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA SUNTER", "0457", "20457"));
		regions.add(new Region("REGION 1A - JAKARTA", "8111", "JAKARTA TANAH ABANG", "0490", "10490"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA PLUIT", "0451", "10451"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA MANGGA DUA", "0459", "10459"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "TANGERANG BSD", "0461", "10461"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA TAMAN PALEM 1", "0488", "10488"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA TAMAN PALEM 2", "0488", "20488"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA PECENONGAN 1", "0510", "10510"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA PECENONGAN 2", "0510", "20510"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "JAKARTA KEBON JERUK INTERCON", "0514", "10514"));
		regions.add(new Region("REGION 1B - JAKARTA", "8121", "TANGERANG GADING SERPONG", "0524", "10524"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN PUTRI HIJAU 1", "0026", "10026"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN PUTRI HIJAU 2", "0026", "20026"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "PEMATANG SIANTAR SUTOMO", "0227", "10227"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "PEKANBARU RIAU 1", "0478", "10478"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "PEKANBARU RIAU 2", "0478", "20478"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN ASIA", "0479", "10479"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN GOLDEN TRADE CENTRE", "0507", "10507"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN CIREBON 1", "0551", "10551"));
		regions.add(new Region("REGION 2 - SUMBAGUT", "8102", "MEDAN CIREBON 2", "0551", "20551"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "LAMPUNG WOLTER M 1", "0006", "10006"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "LAMPUNG WOLTER M 2", "0006", "20006"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "PALEMBANG CINDE 1", "0312", "10312"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "PALEMBANG CINDE 2", "0312", "20312"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "JAMBI TALANG BANJAR", "7353", "17353"));
		regions.add(new Region("REGION 3 - SUMBAGSEL", "8103", "PALEMBANG SUDIRMAN", "0031", "10031"));
		regions.add(new Region("REGION 4 - JAWA BARAT", "8104", "CIREBON WAHIDIN", "0015", "10015"));
		regions.add(new Region("REGION 4 - JAWA BARAT", "8104", "BANDUNG BURANGRANG", "0059", "10059"));
		regions.add(new Region("REGION 4 - JAWA BARAT", "8104", "BANDUNG KOPO 1", "0547", "10547"));
		regions.add(new Region("REGION 4 - JAWA BARAT", "8104", "BANDUNG KOPO 2", "0547", "20547"));
		regions.add(new Region("REGION 4 - JAWA BARAT", "8104", "TASIKMALAYA YUDANEGARA", "0566", "10566"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "SEMARANG MT HARYONO 1", "0018", "10018"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "SEMARANG MT HARYONO 2", "0018", "30018"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "YOGYAKARTA BINTARAN 1", "0019", "10019"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "YOGYAKARTA BINTARAN 2", "0019", "20019"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "SURAKARTA SLAMET RIYADI", "0020", "10020"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "KUDUS SUDIRMAN", "0027", "10027"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "PURWOKERTO PASAR WAGE", "0035", "10035"));
		regions.add(new Region("REGION 5 - JATENG DIY", "8105", "SAM REGION 5", "B105", "B8105"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "MALANG SUTOYO", "0016", "10016"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "MADIUN SALAK", "0021", "10021"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA INDRAPURA", "0025", "10025"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "KEDIRI KATAMSO", "0034", "10034"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA KERTAJAYA", "0039", "10039"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA DARMO GOLF 1", "0460", "10460"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA DARMO GOLF 2", "0460", "60460"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "DENPASAR TEUKU UMAR", "0485", "10485"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA JEMUR ANDAYANI", "0532", "10532"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "SURABAYA KEDUNGDORO", "0549", "10549"));
		regions.add(new Region("REGION 6 - JATIM, BALI, NUSRA", "8106", "MATARAM PEJANGGIK", "0563", "10563"));
		regions.add(new Region("REGION 7 - KALIMANTAN", "8107", "BANJARMASIN A. YANI", "0008", "10008"));
		regions.add(new Region("REGION 7 - KALIMANTAN", "8107", "BALIKPAPAN SUDIRMAN", "0394", "10394"));
		regions.add(new Region("REGION 7 - KALIMANTAN", "8107", "PONTIANAK A. YANI", "7107", "17107"));		
		regions.add(new Region("REGION 7 - KALIMANTAN", "8107", "SAMARINDA PAHLAWAN 2", "7211", "17211"));
		regions.add(new Region("REGION 7 - KALIMANTAN", "8107", "SAMARINDA PAHLAWAN 1", "7211", "27211"));
		regions.add(new Region("REGION 8 - IBT", "8108", "MAKASSAR BAWAKARAENG", "0029", "10029"));
		regions.add(new Region("REGION 8 - IBT", "8108", "MANADO SAM RATULANGI", "0032", "10032"));
		regions.add(new Region("REGION 8 - IBT", "8108", "PALU MASOMBA", "0470", "10470"));
		regions.add(new Region("REGION 8 - IBT", "8108", "MAKASSAR PANAKUKANG", "0541", "10541"));
		regions.add(new Region("REGION 8 - IBT", "8108", "MAKASSAR ALAUDDIN", "0562", "10562"));
		regions.add(new Region("REGION 8 - IBT", "8108", "KENDARI MADONGA", "7108", "17108"));
		regions.add(new Region("REGION 8 - IBT", "8108", "AMBON A.Y. PATTY", "7547", "17547"));
		regions.add(new Region("MASS ACQUISITION", "8199", "SME - BTB", "0043", "20043"));
		regions.add(new Region("SC HO", "8188", "SME - SUPPLY CHAIN", "0043", "10043"));
		regions.add(new Region("HO - COMERCIAL", "N/A", "COM KCP MENARA BTPN", "0449", "10537"));
		return regions;
	}
}
