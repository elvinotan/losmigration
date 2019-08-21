package com.btpn.migration.los.bean;

public class Lookup {
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
	
	private Long lookupId;
	private String key;
	private String group;
	private String description;
	private Boolean active;
	
	public Lookup(Long lookupId, String key, String group, String description, Boolean active) {
		super();
		this.lookupId = lookupId;
		this.key = key;
		this.group = group;
		this.description = description;
		this.active = active;
	}
	
	public Long getLookupId() { return lookupId;	}
	public void setLookupId(Long lookupId) { this.lookupId = lookupId; }
	
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	
	public String getGroup() { return group; }
	public void setGroup(String group) { this.group = group; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public Boolean isActive() { return active; }
	public void setActive(Boolean active) { this.active = active; }

	@Override
	public String toString() {
		return "Lookup [lookupId=" + lookupId + ", key=" + key + ", group=" + group + ", description=" + description + ", active=" + active + "]";
	}
}
