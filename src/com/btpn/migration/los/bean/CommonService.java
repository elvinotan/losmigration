package com.btpn.migration.los.bean;

public class CommonService {
	
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
	
	private Long id;
	private String group;
	private String code;
	private String description;
	
	public CommonService(Long id, String group, String code, String description) {
		super();
		this.id = id;
		this.group = group;
		this.code = code;
		this.description = description;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getGroup() { return group; }
	public void setGroup(String group) { this.group = group; }

	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() {
		return "CommonService [id=" + id + ", group=" + group + ", code=" + code + ", description=" + description + "]";
	}
}