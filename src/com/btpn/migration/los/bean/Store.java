package com.btpn.migration.los.bean;

import java.util.HashMap;
import java.util.Map;

public class Store {
	
	// Lookup
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
	
	// Common Service
	public static String TYPE_ACCOUNT_STATEMENT = "TYPE_ACCOUNT_STATEMENT";
	public static String TYPE_ADDITIONAL_INCOME = "TYPE_ADDITIONAL_INCOME";
	public static String TYPE_APPLICANT_TITLE = "TYPE_APPLICANT_TITLE";
	public static String TYPE_APPLICANT = "TYPE_APPLICANT";
	public static String TYPE_CITY = "TYPE_CITY";
	public static String TYPE_CITY_BI = "TYPE_CITY_BI";
	public static String TYPE_COMPANY_CHARACTERISTICS = "TYPE_COMPANY_CHARACTERISTICS";
	public static String TYPE_COMPANY = "TYPE_COMPANY";
	public static String TYPE_COMPANY_BI = "TYPE_COMPANY_BI";
	public static String TYPE_COUNTRY = "TYPE_COUNTRY";
	public static String TYPE_CI_IND_MONTHLY = "TYPE_CI_IND_MONTHLY";
	public static String TYPE_CI_NONIND_YEARLY = "TYPE_CI_NONIND_YEARLY";
	public static String TYPE_DISTRICT = "TYPE_DISTRICT";
	public static String TYPE_EDUCATION = "TYPE_EDUCATION";
	public static String TYPE_FORECAST_TRANSACTION = "TYPE_FORECAST_TRANSACTION";
	public static String TYPE_GENDER = "TYPE_GENDER";
	public static String TYPE_IDENTIFICATION = "TYPE_IDENTIFICATION";
	public static String TYPE_INDUSTRIAL_SECTOR = "TYPE_INDUSTRIAL_SECTOR";
	public static String TYPE_JOB_PROFESSION = "TYPE_JOB_PROFESSION";
	public static String TYPE_JOB_TITLE = "TYPE_JOB_TITLE";
	public static String TYPE_MARITAL_STATUS = "TYPE_MARITAL_STATUS";
	public static String TYPE_PROVINCE_BI = "TYPE_PROVINCE_BI";
	public static String TYPE_PROVINCE = "TYPE_PROVINCE";
	public static String TYPE_PURPOSE_OF_ACCOUNT = "TYPE_PURPOSE_OF_ACCOUNT";
	public static String TYPE_RELIGION = "TYPE_RELIGION";
	public static String TYPE_RESIDENTIAL_STATUS = "TYPE_RESIDENTIAL_STATUS";
	public static String TYPE_SUB_DISTRICT = "TYPE_SUB_DISTRICT";
	public static String TYPE_ZIP_CODE = "TYPE_ZIP_CODE";
	
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Map<String, Lookup> lookupMap = new HashMap<String, Lookup>();
	
	private Map<String, CommonService> commonServiceMap =  new HashMap<String, CommonService>();
	
	public void add(Lookup lookup) {
		this.lookupMap.put(lookup.getGroup()+"_"+lookup.getKey(), lookup);
		this.lookupMap.put(lookup.getGroup()+"_"+lookup.getDescription(), lookup);
	}
	
	public void add(CommonService commonService) {
		this.commonServiceMap.put(commonService.getGroup()+"_"+commonService.getDescription(), commonService);
	}
	
	public Lookup getLookupByKey(String group, String key) {
		return this.lookupMap.get(group+"_"+key);
	}
	
	public Lookup getLookupByDescription(String group, String description) {
		return this.lookupMap.get(group+"_"+description);
	}
	
	public CommonService getCommonByDescription(String group, String description) {
		return this.commonServiceMap.get(group+"_"+description);
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
		return rvalue.replaceAll("\\'", "\\\\'").trim();
	}
	
	public String getString(String key, String defaultVal) {
		String rvalue = getString(key);
		if (rvalue == null) return defaultVal;
		if ("".equals(rvalue.trim())) return defaultVal;
		
		return rvalue;
	}
	
	public void clear() {
		lookupMap.clear();
		commonServiceMap.clear();
		map.clear();
	}
}
