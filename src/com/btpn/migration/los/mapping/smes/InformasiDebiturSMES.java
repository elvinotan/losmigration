package com.btpn.migration.los.mapping.smes;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.mapping.Mapping;

public class InformasiDebiturSMES implements Mapping{
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// Mencakup
	// dlos_app_detail (Master)
	// dlos_loan_process (1)
	// dlos_app_contact	(*)
	// dlos_app_socialmedia (1)
	// dlos_app_groupdebitur (*)	
	// dlos_app_verification_debitur (1)
	// dlos_app_legal (1)
	// dlos_app_management (*)
	// dlos_app_property (1)

	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_property where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_management where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_legal where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_verification_debitur where created_By ='%s';", MIGRATION),
			String.format("delete from dlos_app_groupdebitur where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_socialmedia where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_contact where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_loan_process where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_detail where createdBy ='%s';", MIGRATION)
		};
	}
	
	@Override
	public void initMapping() {
		IActions insertAppDetail = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store) throws Exception {
				String branchCode = "branchCode"; 
				String salesSquadName = "salesSquadName";
				String areaName = "areaName";
				String regionName = "regionName";
				String debiturName = "debiturName";
				String aliasName = "aliasName";
				String KTPNumber = "KTPNumber";
				String KTPExpDate = "8888-09-08"; 
				String NPWPNumber = "NPWPNumber";
				String placeOfBirth = "placeOfBirth";
				String dateOfBirth = "8888-08-08";
				String religionCode = "religionCode";
				String nationalityCode = "nationalityCode";
				String residenceStatus = "residenceStatus";
				String educationStatus = "educationStatus";
				String mobileNumber = "mobileNumber";
				String email = "email";
				String KTPAddress = "KTPAddress";
				String KTPPostalCode = "KTPPostalCode";
				String KTPCityCode = "KTPCityCode";
				String KTPSubDistrict = "KTPSubDistrict";
				String KTPDistrict = "KTPDistrict";
				String currentAddress = "currentAddress";
				String currentAddressPostalCode = "currentAddressPostalCode";
				String currentAddressSubDistrict = "currentAddressSubDistrict";
				String currentAddressDistrict = "currentAddressDistrict";
				String motherMaidenName = "motherMaidenName";
				String btpnRelationCode = "btpnRelationCode";
				String sourceOfDebtor = "sourceOfDebtor";
				String debiturType = "debiturType";
				String gender = "gender";
				String maritalStatus = "maritalStatus";
				String spouseName = "spouseName";
				String customerType = "customerType";
				String isActive = "isActive";
				String modifiedDate = null;
				String modifiedBy = "modifiedBy";
				String createdDate = "createdDate";
				String createdBy = "createdBy";
				String businessAddress = "businessAddress";
				String businessCityCode = "businessCityCode";
				String businessProvinceCode = "businessProvinceCode";
				String businessPostalCode = "businessPostalCode";
				String businessPhoneCode = "123";
				String currentAddressCityCode = "currentAddressCityCode";
				String cif = "cif";
				String rmName = mapper.getString("rmName");
				String rmCode = "rmCode";
				String acmName = "acmName";
				String acmNik = "acmNik";
				String btpnRelationDate = "8888-08-08";
				String programProduct = "programProduct";
				String information = "information";
				String managementRelation = "managementRelation";
				String managementChange = "managementChange";
				String KTPRTRWCode = "KTPRTRWCode";
				String currentAddressRTRWCode = "currentAddressRTRWCode";
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_detail (`branchCode`, `salesSquadName`, `areaName`, `regionName`, `debiturName`, `aliasName`, `KTPNumber`, `KTPExpDate`, `NPWPNumber`, `placeOfBirth`, `dateOfBirth`, `religionCode`, `nationalityCode`, `residenceStatus`, `educationStatus`, `mobileNumber`, email, `KTPAddress`, `KTPPostalCode`, `KTPCityCode`, `KTPSubDistrict`, `KTPDistrict`, `currentAddress`, `currentAddressPostalCode`, `currentAddressSubDistrict`, `currentAddressDistrict`, `motherMaidenName`, `btpnRelationCode`, `sourceOfDebtor`, `debiturType`, gender, `maritalStatus`, `spouseName`, `customerType`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`, `businessAddress`, `businessCityCode`, `businessProvinceCode`, `businessPostalCode`, `businessPhoneCode`, `currentAddressCityCode`, cif, `rmName`, `rmCode`, `acmName`, `acmNik`, `btpnRelationDate`, `programProduct`, information, `managementRelation`, `managementChange`, `KTPRTRWCode`, `currentAddressRTRWCode`) " + 
						"VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s', '%s');", 
						branchCode, salesSquadName, areaName, regionName, debiturName, aliasName, KTPNumber, KTPExpDate, NPWPNumber, placeOfBirth, dateOfBirth, religionCode, nationalityCode, residenceStatus, educationStatus, mobileNumber, email, KTPAddress, KTPPostalCode, KTPCityCode, KTPSubDistrict, KTPDistrict, currentAddress, currentAddressPostalCode, currentAddressSubDistrict, currentAddressDistrict, motherMaidenName, btpnRelationCode, sourceOfDebtor, debiturType, gender, maritalStatus, spouseName, customerType, isActive, modifiedDate, modifiedBy, createdDate, createdBy, businessAddress, businessCityCode, businessProvinceCode, businessPostalCode, businessPhoneCode, currentAddressCityCode, cif, rmName, rmCode, acmName, acmNik, btpnRelationDate, programProduct, information, managementRelation, managementChange, KTPRTRWCode, currentAddressRTRWCode
				);
			}
			
		};
		
		specRows.add(SpecRow.get(insertAppDetail).setSheet(Sheet.Informasi_Debitur)
				.xls("branchCode","")
				.xls("salesSquadName","")
				.xls("areaName","")
				.xls("regionName","")
				.xls("debiturName","")
				.xls("aliasName","")
				.xls("KTPNumber","")
				.xls("KTPExpDate","")
				.xls("NPWPNumber","")
				.xls("placeOfBirth","")
				.xls("dateOfBirth","")
				.xls("religionCode","")
				.xls("nationalityCode","")
				.xls("residenceStatus","")
				.xls("educationStatus","")
				.xls("mobileNumber","")
				.xls("email","")
				.xls("KTPAddress","")
				.xls("KTPPostalCode","")
				.xls("KTPCityCode","")
				.xls("KTPSubDistrict","")
				.xls("KTPDistrict","")
				.xls("currentAddress","")
				.xls("currentAddressPostalCode","")
				.xls("currentAddressSubDistrict","")
				.xls("currentAddressDistrict","")
				.xls("motherMaidenName","")
				.xls("btpnRelationCode","")
				.xls("sourceOfDebtor","")
				.xls("debiturType","")
				.xls("gender","")
				.xls("maritalStatus","")
				.xls("spouseName","")
				.xls("customerType","")
				.xls("isActive","")
				.xls("modifiedDate","")
				.xls("modifiedBy","")
				.xls("createdDate","")
				.xls("createdBy","")
				.xls("businessAddress","")
				.xls("businessCityCode","")
				.xls("businessProvinceCode","")
				.xls("businessPostalCode","")
				.xls("businessPhoneCode","")
				.xls("currentAddressCityCode","")
				.xls("cif","")
				.xls("rmName","C4")
				.xls("rmCode","")
				.xls("acmName","")
				.xls("acmNik","")
				.xls("btpnRelationDate","")
				.xls("programProduct","")
				.xls("information","")
				.xls("managementRelation","")
				.xls("managementChange","")
				.xls("KTPRTRWCode","")
				.xls("currentAddressRTRWCode","")
				.pk("dataId")
				);
	}

	@Override
	public List<SpecRow> getSpecRows() {
		return this.specRows;
	}

}
