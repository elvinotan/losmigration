package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Store {
	
	public static String BTPNRelationship = "BTPNRelationship";
	public static String DebtorSource = "DebtorSource";
	public static String DebiturType = "DebiturType";
	public static String Gender = "Gender";
	public static String MaritalStatus = "MaritalStatus";
	public static String HomeOwnership = "HomeOwnership";
	public static String BusinessOwnership = "BusinessOwnership";
	public static String YesNo = "YesNo";
	public static String Position = "Position";
	public static String IDCard = "IDCard";
	public static String LegalEntity = "LegalEntity";
	public static String CreditPurpose = "CreditPurpose";
	public static String SubmissionStatus = "SubmissionStatus";
	public static String ProgramType = "ProgramType";
	public static String FacilityName = "FacilityName";
	public static String FacilityType = "FacilityType";
	public static String Currency = "Currency";
	public static String Instalment = "Instalment";
	public static String BIChecking = "BIChecking";
	public static String IndustrialSector = "IndustrialSector";
	public static String BusinessType = "BusinessType";
	public static String BusinessFounderIndividual = "BusinessFounderIndividual";
	public static String BusinessFounderCorporate = "BusinessFounderCorporate";
	public static String MarketingArea = "MarketingArea";
	public static String ReligionCode = "ReligionCode";
	public static String RetailBasedOnWalkinCustomer = "RetailBasedOnWalkinCustomer";
	public static String ResidenceStatus = "ResidenceStatus";
	public static String CollateralProperty = "CollateralProperty";
	public static String CollateralType = "CollateralType";
	public static String CollateralStatus = "CollateralStatus";
	public static String BusinessScale = "BusinessScale";
	public static String SupDependency = "SupDependency";
	public static String SalesFreq = "SalesFreq";
	public static String GoodsServicesPurchFreq = "GoodsServicesPurchFreq";
	public static String TimelyPymt = "TimelyPymt";
	public static String BuyDependency = "BuyDependency";
	public static String Covenant = "Covenant";
	public static String GoodsServicesQuality = "GoodsServicesQuality";
	public static String GuarantorType = "GuarantorType";
	public static String GuarantorCoverage = "GuarantorCoverage";
	public static String ColAttchAmtIndv = "ColAttchAmtIndv";
	public static String ColAttchAmtCorp = "ColAttchAmtCorp";
	public static String GuarantorProfile = "GuarantorProfile";
	public static String SecondGuarantorProfile = "SecondGuarantorProfile";
	public static String SMESPengajuanTepat = "SMESPengajuanTepat";
	public static String LaporanKeuangan = "LaporanKeuangan";
	public static String CheckRejection = "CheckRejection";
	public static String BusinessStrategy = "BusinessStrategy";
	public static String Frequency = "Frequency";
	public static String PaymentFreq = "PaymentFreq";
	public static String AdditionalInfo = "AdditionalInfo";
	public static String ProductProgram = "ProductProgram";
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Map<String, Lookup> lookupMap = new HashMap<String, Lookup>();
	
	private Map<String, CommonService> commonServiceMap =  new HashMap<String, CommonService>();
	
	public void add(Lookup lookup) {
		this.lookupMap.put(lookup.getGroup()+"_"+lookup.getKey(), lookup);
		this.lookupMap.put(lookup.getGroup()+"_"+lookup.getDescription(), lookup);
	}
	
	public void add(CommonService commonService) {
		//TODO implements add
	}
	
	public Lookup getLookupByKey(String group, String key) {
		return this.lookupMap.get(group+"_"+key);
	}
	
	public Lookup getLookupByDescription(String group, String description) {
		return this.lookupMap.get(group+"_"+description);
	}
	
	public void put(String key, Object object) {
		map.put(key, object);
	}
	
	public Object getObject(String key) {
		return map.get(key);
	}

	public String getString(String key) {
		Object obj = getObject(key);
		if (obj == null) return null;
		
		String rvalue = (String) obj;
		return rvalue.replaceAll("\\'", "\\\\'");
	}
	
	public String getString(String key, String defaultVal) {
		String rvalue = getString(key);
		if (rvalue == null) return defaultVal;
		if ("".equals(rvalue.trim())) return defaultVal;
		
		return rvalue;
	}
}
