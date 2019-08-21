package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.Branch;
import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class InformasiDebiturSMES implements Mapping{
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// 1. Dari Excel Lisa Pekan Baru Riau 1, dr xls Irfan PEKANBARU RIAU 1 > (Beda case beda susunan data gimana cara mappingnya ?)
	// 2. Bagaiman car ambil salesOfficeCode sedangakan paling kecil adalah branchCode, Lihat hirarcy LOS
	// 3. businessCityCode di xls pekanbaru di commonservice 'KOTA PEKANBARU'
	
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
				// dlos_app_detail.branchCode ambil dari table dlos_branch.description lalu ambil branchCode
				String branchCode = mapper.getString("branchCode");
				Branch branch = store.getBranchByDescription(branchCode, 1);
				if (branch == null) throw new NullPointerException("Branch Mapping not found for branchCode ["+branchCode+"] ");
				branchCode = branch.getBranchCode();
				
				String salesSquadName = null; // Belum
				String areaName = null; // Belum
				String regionName = mapper.getString("regionName").toUpperCase();
				String debiturName = mapper.getString("debiturName");
				String aliasName = null; // Belum
				String KTPNumber = null; // Belum
				String KTPExpDate = null;  // Belum
				String NPWPNumber = null; // Belum
				String placeOfBirth = null; // Belum
				String dateOfBirth = null; // Belum
				String religionCode = null; // Belum
				String nationalityCode = null; // Belum
				String residenceStatus = null; // Belum
				String educationStatus = null; // Belum
				String mobileNumber = null; // Belum
				String email = null; // Belum
				String KTPAddress = null; // Belum
				String KTPPostalCode = null; // Belum
				String KTPCityCode = null; // Belum
				String KTPSubDistrict = null; // Belum
				String KTPDistrict = null; // Belum
				String currentAddress = null; // Belum
				String currentAddressPostalCode = null; // Belum
				String currentAddressSubDistrict = null; // Belum
				String currentAddressDistrict = null; // Belum
				String motherMaidenName = null; // Belum
				String btpnRelationCode = null; // Belum
				String sourceOfDebtor = mapper.getString("sourceOfDebtor");
				String debiturType = mapper.getString("debiturType");
				String debiturTypeDescription = debiturType.split("_")[1].trim();
				Lookup lookup = store.getLookupByDescription(Lookup.DebiturType, debiturTypeDescription);
				debiturType = lookup.getKey();				
				String gender = null; // Belum
				String maritalStatus = null; // Belum
				String spouseName = mapper.getString("spouseName");
				String customerType = null; // Belum
				String isActive = null; // Belum
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION; 
				String businessAddress = (mapper.getString("businessAddress0", "") +" "+ mapper.getString("businessAddress1", "")).trim();
				String businessCityCode = mapper.getString("businessCityCode");
				CommonService csbusinessCityCode = store.getCommonByDescription(CommonService.TYPE_CITY, businessCityCode);
				businessCityCode = csbusinessCityCode.getCode();
				String businessProvinceCode = mapper.getString("businessProvinceCode");;
				CommonService csbusinessProvinceCode = store.getCommonByDescription(CommonService.TYPE_PROVINCE, businessProvinceCode);
				businessProvinceCode = csbusinessProvinceCode.getCode();				
				String businessPostalCode = mapper.clearDecimal(mapper.getString("businessPostalCode"));
				String businessPhoneCode = mapper.getString("businessPhoneCode");
				String currentAddressCityCode = "currentAddressCityCode";
				String cif = mapper.getString("cif");
				String rmName = mapper.getString("rmName");
				String rmCode = mapper.getString("rmCode");
				String acmName = mapper.getString("acmName");
				String acmNik = mapper.getString("acmNik");
				String btpnRelationDate = DateTool.getYMD(mapper.getString("btpnRelationDate"));
				String programProduct = mapper.getString("programProduct");
				String information =  mapper.getString("information");
				String managementRelation = (mapper.getString("managementRelation0") +" "+mapper.getString("managementRelation1")).trim();
				String managementChange = mapper.getString("managementChange");
				String KTPRTRWCode = null; //Belum
				String currentAddressRTRWCode = null; //Belum
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_detail (`branchCode`, `salesSquadName`, `areaName`, `regionName`, `debiturName`, `aliasName`, `KTPNumber`, `KTPExpDate`, `NPWPNumber`, `placeOfBirth`, `dateOfBirth`, `religionCode`, `nationalityCode`, `residenceStatus`, `educationStatus`, `mobileNumber`, email, `KTPAddress`, `KTPPostalCode`, `KTPCityCode`, `KTPSubDistrict`, `KTPDistrict`, `currentAddress`, `currentAddressPostalCode`, `currentAddressSubDistrict`, `currentAddressDistrict`, `motherMaidenName`, `btpnRelationCode`, `sourceOfDebtor`, `debiturType`, gender, `maritalStatus`, `spouseName`, `customerType`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`, `businessAddress`, `businessCityCode`, `businessProvinceCode`, `businessPostalCode`, `businessPhoneCode`, `currentAddressCityCode`, cif, `rmName`, `rmCode`, `acmName`, `acmNik`, `btpnRelationDate`, `programProduct`, information, `managementRelation`, `managementChange`, `KTPRTRWCode`, `currentAddressRTRWCode`) " + 
						"VALUES(								'%s', 		  '%s',             '%s',       '%s',         '%s',          '%s',        '%s',        '%s',         '%s',         '%s',           '%s',          '%s',           '%s',              '%s',              '%s',              '%s',           '%s',  '%s',         '%s',            '%s',          '%s',             '%s',          '%s',             '%s',                       '%s',                        '%s',                     '%s',               '%s',               '%s',             '%s',          '%s',   '%s',            '%s',         '%s',           %s,         '%s',           '%s',         '%s',          '%s',        '%s',              '%s',               '%s',                   '%s',                 '%s',                '%s',                     '%s', '%s',    '%s',     '%s',      '%s',     '%s',               '%s',             '%s',        '%s',                 %s,                 '%s',          '%s');", 
						branchCode, salesSquadName, areaName, regionName, debiturName, aliasName, KTPNumber, KTPExpDate, NPWPNumber, placeOfBirth, dateOfBirth, religionCode, nationalityCode, residenceStatus, educationStatus, mobileNumber, email, KTPAddress, KTPPostalCode, KTPCityCode, KTPSubDistrict, KTPDistrict, currentAddress, currentAddressPostalCode, currentAddressSubDistrict, currentAddressDistrict, motherMaidenName, btpnRelationCode, sourceOfDebtor, debiturType, gender, maritalStatus, spouseName, customerType, isActive, modifiedDate, modifiedBy, createdDate, createdBy, businessAddress, businessCityCode, businessProvinceCode, businessPostalCode, businessPhoneCode, currentAddressCityCode, cif, rmName, rmCode, acmName, acmNik, btpnRelationDate, programProduct, information, managementRelation, managementChange, KTPRTRWCode, currentAddressRTRWCode
				);
			}
		};
		specRows.add(SpecRow.get(insertAppDetail).setSheet(Sheet.InformasiDebitur).xls("appId","J7").xls("branchCode","C9").xls("salesSquadName","").xls("areaName","")
				.xls("regionName","C8").xls("debiturName","J5").xls("aliasName","").xls("KTPNumber","").xls("KTPExpDate","").xls("NPWPNumber","").xls("placeOfBirth","")
				.xls("dateOfBirth","").xls("religionCode","").xls("nationalityCode","").xls("residenceStatus","").xls("educationStatus","").xls("mobileNumber","")
				.xls("email","").xls("KTPAddress","").xls("KTPPostalCode","").xls("KTPCityCode","").xls("KTPSubDistrict","").xls("KTPDistrict","").xls("currentAddress","")
				.xls("currentAddressPostalCode","").xls("currentAddressSubDistrict","").xls("currentAddressDistrict","").xls("motherMaidenName","").xls("btpnRelationCode","")
				.xls("sourceOfDebtor","C77").xls("debiturType","D26").xls("gender","").xls("maritalStatus","").xls("spouseName","D36").xls("customerType","").xls("isActive","")
				.xls("modifiedDate","").xls("modifiedBy","").xls("createdDate","J4").xls("createdBy","").xls("businessAddress0","D20").xls("businessAddress1","D21")
				.xls("businessCityCode","D22").xls("businessProvinceCode","D23").xls("businessPostalCode","D24").xls("businessPhoneCode","D25").xls("currentAddressCityCode","")
				.xls("cif","J6").xls("rmName","C4").xls("rmCode","C5").xls("acmName","C6").xls("acmNik","C7").xls("btpnRelationDate","J8").xls("programProduct","J9")
				.xls("information","A81").xls("managementRelation0","A134").xls("managementRelation1","A135").xls("managementChange","D34").xls("KTPRTRWCode","")
				.xls("currentAddressRTRWCode","").pk("dataId"));
		
		IActions insertAppLoanProcess = new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) throws Exception {
				String APPID = mapper.getString("appId");
				String dataId = store.getString("dataId"); 
				String processStatus = mapper.getString("processStatus");
				String processCode = null; 
				String stateCode = mapper.getString("stateCode");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate =  DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;
				
				return String.format(
						"INSERT INTO dlos_core.dlos_loan_process (`APPID`, `dataId`, `processStatus`, `processCode`, `stateCode`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) " + 
						"VALUES(								  '%s',     %s,      '%s',            '%s',          '%s',        %s,         '%s',           '%s',         '%s',            '%s');" 
						, APPID, dataId, processStatus, processCode, stateCode, isActive, modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		specRows.add(SpecRow.get(insertAppLoanProcess).setSheet(Sheet.InformasiDebitur).xls("appId", "J7").fix("processStatus", "1").fix("stateCode", "DRAFT").xls("createdDate", "J4"));

		IActions insertAppContact = new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) throws Exception {
				String contactName = mapper.getString("contactName");
				if (contactName == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String genderCode = mapper.getString("genderCode");
				Lookup lookup = store.getLookupByDescription(Lookup.Gender, genderCode);
				genderCode = lookup.getKey();
				
				String positionCode = null;
				String fixedLineNumber = mapper.getString("fixedLineNumber");
				String mobileNumber = mapper.getString("mobileNumber");
				String email = mapper.getString("email");
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy =  mapper.getString("appId");
				String createdDate =  DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_contact (`contactName`, `genderCode`, `positionCode`, `fixedLineNumber`, `mobileNumber`, email, `dataId`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) " + 
						"VALUES(								 '%s',          '%s',         '%s',           '%s',              '%s',           '%s',   %s,       %s,        '%s',           '%s',         '%s',          '%s');",
						contactName, genderCode, positionCode, fixedLineNumber, mobileNumber, email, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		specRows.add(SpecRow.get(insertAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A71").xls("genderCode", "K71").xls("positionCode", "C71").xls("fixedLineNumber", "E71").xls("mobileNumber", "G71").xls("email", "I71").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A72").xls("genderCode", "K72").xls("positionCode", "C72").xls("fixedLineNumber", "E72").xls("mobileNumber", "G72").xls("email", "I72").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A73").xls("genderCode", "K73").xls("positionCode", "C73").xls("fixedLineNumber", "E73").xls("mobileNumber", "G73").xls("email", "I73").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A74").xls("genderCode", "K74").xls("positionCode", "C74").xls("fixedLineNumber", "E74").xls("mobileNumber", "G74").xls("email", "I74").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A75").xls("genderCode", "K75").xls("positionCode", "C75").xls("fixedLineNumber", "E75").xls("mobileNumber", "G75").xls("email", "I75").xls("createdDate", "J4").xls("appId", "J7"));
	
	
		IActions insertSocialMedia = new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) throws Exception {
				if (true) return null;
				
				String facebookId = null; 
				String instagramId = null; 
				String twitterId = null;
				String dataId = null;
				String isActive = null;
				String modifiedDate = null;
				String modifiedBy = null;
				String createdDate = null;
				String createdBy = null;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_socialmedia (`facebookId`, `instagramId`, `twitterId`, `dataId`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) " + 
						"VALUES('%s', '%s', '%s', %s, %s, '%s', '%s', '%s', '%s');", 
						facebookId, instagramId, twitterId, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		specRows.add(SpecRow.get(insertSocialMedia).setSheet(Sheet.InformasiDebitur));
	}

	@Override
	public List<SpecRow> getSpecRows() {
		return this.specRows;
	}

}
