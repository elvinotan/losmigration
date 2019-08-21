package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.Region;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.NumberTool;
import com.btpn.migration.los.tool.StringTool;

public class InformasiDebitur implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();

	// Mencakup
	// dlos_app_detail (Master)
	// dlos_loan_process (1)
	// dlos_app_contact (*)
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
	public void initMapping(String lobType) {
		migrasiDlosAppDetail(lobType);
		migrasiDlosLoanProcess(lobType);
		migrasiDlosAppContact(lobType);
		migrasiDlosAppSocialMedia(lobType);
		migrasiDlosAppGroupDebitur(lobType);
	}

	private void migrasiDlosAppDetail(String lobType) {
		
		IActions insertDlosAppDetail = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String branchCode = mapper.getString("branchCode"); // Branch Code, berasal dari Region.java/ Hierarchy LOS.xlsx
				Region rBranchCode = store.getBranchByDescription(branchCode);
				branchCode = (rBranchCode == null) ?  null : rBranchCode.getBranchCode();
				
				String salesSquadName = (rBranchCode == null) ? null : rBranchCode.getPmsCode(); // SalesSqualName berasal dari Region.java / Hierarchy LOS.xlsx
				String areaName = null; // Belum
				
				String regionName = mapper.getString("regionName").toUpperCase(); // RegionName berasal dari Region.java / Hierarchy LOS.xlsx
				Region region = store.getRegionByDescription(regionName);
				regionName = (region == null) ? null : region.getRegion();
				
				String debiturName = mapper.getString("debiturName");
				String aliasName = null; // Belum
				String KTPNumber = null; // Belum
				String KTPExpDate = null; // Belum
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
				
				String sourceOfDebtor = mapper.getString("sourceOfDebtor"); // SourceOfDebtor berasal dari Lookup.DebtorSource
				Lookup lsourceOfDebtor = store.getLookupByDescription(Lookup.DebtorSource, sourceOfDebtor);
				sourceOfDebtor = (lsourceOfDebtor == null) ? null : lsourceOfDebtor.getKey();
				
				String debiturType = mapper.getString("debiturType"); // DebiturType berasal dari Lookup.DebiturType
				String debiturTypeDesc = debiturType.split("_")[1].trim();
				Lookup ldebiturType = store.getLookupByDescription(Lookup.DebiturType, debiturTypeDesc);
				debiturType = (ldebiturType == null) ? null : ldebiturType.getKey();
				
				String gender = null; // Belum
				String maritalStatus = null; // Belum
				String spouseName = mapper.getString("spouseName");
				String customerType = null; // Belum
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;
				String businessAddress = StringTool.combine(mapper.getString("businessAddress0"), mapper.getString("businessAddress1"));
				
				String businessCityCode = mapper.getString("businessCityCode"); // BusinessCityCode berasal dari commonService TYPE_CITY
				CommonService csbusinessCityCode = store.getCommonByDescription(CommonService.TYPE_CITY, businessCityCode);
				businessCityCode = (csbusinessCityCode == null) ? null : csbusinessCityCode.getCode();
				
				String businessProvinceCode = mapper.getString("businessProvinceCode");  // BusinessCityCode berasal dari commonService TYPE_PROVINCE
				CommonService csbusinessProvinceCode = store.getCommonByDescription(CommonService.TYPE_PROVINCE, businessProvinceCode);
				businessProvinceCode = (csbusinessProvinceCode == null) ? null : csbusinessProvinceCode.getCode();
				
				String businessPostalCode = mapper.clearDecimal(mapper.getString("businessPostalCode"));
				String businessPhoneCode = mapper.getString("businessPhoneCode");
				String currentAddressCityCode = null;
				String cif = mapper.getString("cif");
				String rmName = mapper.getString("rmName");
				String rmCode = mapper.getString("rmCode");
				String acmName = mapper.getString("acmName");
				String acmNik = mapper.getString("acmNik");
				String btpnRelationDate = DateTool.getYMD(mapper.getString("btpnRelationDate"));
				
				String programProduct = mapper.getString("programProduct"); //ProgramProduct berasal dari Lookup.ProductProgram
				Lookup lProgramProduct = store.getLookupByDescription(Lookup.ProductProgram, programProduct);
				programProduct = (lProgramProduct == null) ? null : lProgramProduct.getKey();
				
				String information = mapper.getString("information");
				String managementRelation = StringTool.combine(mapper.getString("managementRelation0"), mapper.getString("managementRelation1"));
				String managementChange = mapper.getString("managementChange");
				String KTPRTRWCode = null; // Belum
				String currentAddressRTRWCode = null; // Belum

				return String.format(
						"INSERT INTO dlos_core.dlos_app_detail (`branchCode`, `salesSquadName`, `areaName`, `regionName`, `debiturName`, `aliasName`, `KTPNumber`, `KTPExpDate`, `NPWPNumber`, `placeOfBirth`, `dateOfBirth`, `religionCode`, `nationalityCode`, `residenceStatus`, `educationStatus`, `mobileNumber`, email, `KTPAddress`, `KTPPostalCode`, `KTPCityCode`, `KTPSubDistrict`, `KTPDistrict`, `currentAddress`, `currentAddressPostalCode`, `currentAddressSubDistrict`, `currentAddressDistrict`, `motherMaidenName`, `btpnRelationCode`, `sourceOfDebtor`, `debiturType`, gender, `maritalStatus`, `spouseName`, `customerType`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`, `businessAddress`, `businessCityCode`, `businessProvinceCode`, `businessPostalCode`, `businessPhoneCode`, `currentAddressCityCode`, cif, `rmName`, `rmCode`, `acmName`, `acmNik`, `btpnRelationDate`, `programProduct`, information, `managementRelation`, `managementChange`, `KTPRTRWCode`, `currentAddressRTRWCode`) "
								+ "VALUES(						'%s', 		  '%s',             '%s',       '%s',         '%s',          '%s',        '%s',        '%s',         '%s',         '%s',           '%s',          '%s',           '%s',              '%s',              '%s',              '%s',           '%s',  '%s',         '%s',            '%s',          '%s',             '%s',          '%s',             '%s',                       '%s',                        '%s',                     '%s',               '%s',               '%s',             '%s',          '%s',   '%s',            '%s',         '%s',           %s,         '%s',           '%s',         '%s',          '%s',        '%s',              '%s',               '%s',                   '%s',                 '%s',                '%s',                     '%s', '%s',    '%s',     '%s',      '%s',     '%s',               '%s',             '%s',        '%s',                 %s,                 '%s',          '%s');",
						branchCode, salesSquadName, areaName, regionName, debiturName, aliasName, KTPNumber, KTPExpDate, NPWPNumber, placeOfBirth, dateOfBirth, religionCode, nationalityCode, residenceStatus,
						educationStatus, mobileNumber, email, KTPAddress, KTPPostalCode, KTPCityCode, KTPSubDistrict, KTPDistrict, currentAddress, currentAddressPostalCode, currentAddressSubDistrict,
						currentAddressDistrict, motherMaidenName, btpnRelationCode, sourceOfDebtor, debiturType, gender, maritalStatus, spouseName, customerType, isActive, modifiedDate, modifiedBy, createdDate,
						createdBy, businessAddress, businessCityCode, businessProvinceCode, businessPostalCode, businessPhoneCode, currentAddressCityCode, cif, rmName, rmCode, acmName, acmNik,
						btpnRelationDate, programProduct, information, managementRelation, managementChange, KTPRTRWCode, currentAddressRTRWCode);
			}
		};
		specRows.add(SpecRow.get(insertDlosAppDetail).setSheet(Sheet.InformasiDebitur).xls("appId", "J7")
				.xls("branchCode", "C9").xls("salesSquadName", "").xls("areaName", "").xls("regionName", "C8").xls("debiturName", "J5").xls("aliasName", "").xls("KTPNumber", "").xls("KTPExpDate", "")
				.xls("NPWPNumber", "").xls("placeOfBirth", "").xls("dateOfBirth", "").xls("religionCode", "").xls("nationalityCode", "").xls("residenceStatus", "").xls("educationStatus", "")
				.xls("mobileNumber", "").xls("email", "").xls("KTPAddress", "").xls("KTPPostalCode", "").xls("KTPCityCode", "").xls("KTPSubDistrict", "").xls("KTPDistrict", "").xls("currentAddress", "")
				.xls("currentAddressPostalCode", "").xls("currentAddressSubDistrict", "").xls("currentAddressDistrict", "").xls("motherMaidenName", "").xls("btpnRelationCode", "")
				.xls("sourceOfDebtor", "C77").xls("debiturType", "D26").xls("gender", "").xls("maritalStatus", "").xls("spouseName", "D36").xls("customerType", "").xls("isActive", "").xls("modifiedDate", "")
				.xls("modifiedBy", "").xls("createdDate", "J4").xls("createdBy", "").xls("businessAddress0", "D20").xls("businessAddress1", "D21").xls("businessCityCode", "D22").xls("businessProvinceCode", "D23")
				.xls("businessPostalCode", "D24").xls("businessPhoneCode", "D25").xls("currentAddressCityCode", "").xls("cif", "J6").xls("rmName", "C4").xls("rmCode", "C5").xls("acmName", "C6").xls("acmNik", "C7")
				.xls("btpnRelationDate", "J8").xls("programProduct", "J9").xls("information", "A81").xls("managementRelation0", "A134").xls("managementRelation1", "A135").xls("managementChange", "D34")
				.xls("KTPRTRWCode", "").xls("currentAddressRTRWCode", "")
				.pk("dataId"));
	}

	private void migrasiDlosLoanProcess(String lobType) {
		
		IActions insertDlosLoanProcess = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String APPID = mapper.getString("appId");
				String dataId = store.getString("dataId");
				String processStatus = mapper.getString("processStatus");
				String processCode = null;
				String stateCode = mapper.getString("stateCode");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;

				return String.format(
						"INSERT INTO dlos_core.dlos_loan_process (`APPID`, `dataId`, `processStatus`, `processCode`, `stateCode`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) "
								+ "VALUES(						  '%s',     %s,      '%s',            '%s',          '%s',        %s,         '%s',           '%s',         '%s',            '%s');",
						APPID, dataId, processStatus, processCode, stateCode, isActive, modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		specRows.add(SpecRow.get(insertDlosLoanProcess).setSheet(Sheet.InformasiDebitur).xls("appId", "J7").fix("processStatus", "1").fix("stateCode", "DRAFT").xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppContact(String lobType) {
		
		IActions insertDlosAppContact = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String contactName = mapper.getString("contactName");
				if (contactName == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute

				String genderCode = mapper.getString("genderCode");
				Lookup lgenderCode = store.getLookupByDescription(Lookup.Gender, genderCode);
				genderCode = (lgenderCode == null) ? null : lgenderCode.getKey();

				String positionCode = mapper.getString("positionCode");
				Lookup lpositionCode = store.getLookupByDescription(Lookup.Position, positionCode);
				positionCode = (lpositionCode == null) ? null : lpositionCode.getKey();
				
				String fixedLineNumber = mapper.getString("fixedLineNumber");
				String mobileNumber = mapper.getString("mobileNumber");
				String email = mapper.getString("email");
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;

				return String.format(
						"INSERT INTO dlos_core.dlos_app_contact (`contactName`, `genderCode`, `positionCode`, `fixedLineNumber`, `mobileNumber`, email, `dataId`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) "
								+ "VALUES(						 '%s',          '%s',         '%s',           '%s',              '%s',           '%s',   %s,       %s,        '%s',           '%s',         '%s',          '%s');",
						contactName, genderCode, positionCode, fixedLineNumber, mobileNumber, email, dataId, isActive,
						modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		specRows.add(SpecRow.get(insertDlosAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A71").xls("genderCode", "K71").xls("positionCode", "C71").xls("fixedLineNumber", "E71").xls("mobileNumber", "G71").xls("email", "I71").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertDlosAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A72").xls("genderCode", "K72").xls("positionCode", "C72").xls("fixedLineNumber", "E72").xls("mobileNumber", "G72").xls("email", "I72").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertDlosAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A73").xls("genderCode", "K73").xls("positionCode", "C73").xls("fixedLineNumber", "E73").xls("mobileNumber", "G73").xls("email", "I73").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertDlosAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A74").xls("genderCode", "K74").xls("positionCode", "C74").xls("fixedLineNumber", "E74").xls("mobileNumber", "G74").xls("email", "I74").xls("createdDate", "J4").xls("appId", "J7"));
		specRows.add(SpecRow.get(insertDlosAppContact).setSheet(Sheet.InformasiDebitur).xls("contactName", "A75").xls("genderCode", "K75").xls("positionCode", "C75").xls("fixedLineNumber", "E75").xls("mobileNumber", "G75").xls("email", "I75").xls("createdDate", "J4").xls("appId", "J7"));
	}
	
	private void migrasiDlosAppSocialMedia(String lobType) {
		// Saat ini tidak ada data SocialMedia yang dapat di migrasi, right ?
	}
	
	private void migrasiDlosAppGroupDebitur(String lobtype) {
		
		IActions insertDlosAppGroupDebitur = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String groupDebiturName = mapper.getString("groupDebiturName");
				if (groupDebiturName == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String groupOwnershipPercentage = mapper.getString("groupOwnershipPercentage"); // data yang masukan berupa range 0 s/d 1, sehingga harus di kali 100
				groupOwnershipPercentage = NumberTool.format(Double.valueOf(groupOwnershipPercentage) * 100);
				 
				String industrySectorCode = mapper.getString("industrySectorCode"); // data di ambil dari lookup.IndustrialSector
				Lookup lindustrySectorCode = store.getLookupByDescription(Lookup.IndustrialSector, industrySectorCode);
				industrySectorCode = (lindustrySectorCode == null) ? null : lindustrySectorCode.getKey();
				
				String yearsOfExperience = mapper.getString("yearsOfExperience"); // Data yang di input seperti '32 tahun' sehingga kita harus mengextract number saja
				yearsOfExperience = NumberTool.extractNumberOnly(yearsOfExperience);
				
				String contactPersonName = mapper.getString("contactPersonName");
				String groupDebiturAddress = mapper.getString("groupDebiturAddress");
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;

				return String.format( 
						"INSERT INTO dlos_core.dlos_app_groupdebitur (groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                     '%s',              '%s',                     '%s',               '%s',              '%s',              '%s',                '%s',   %s,     '%s',         '%s',       '%s',        '%s');", 
						groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy);
			}
		};
		
		if (LobType.isSmes(lobtype)) {
			specRows.add(SpecRow.get(insertDlosAppGroupDebitur).setSheet(Sheet.InformasiDebitur)
					.xls("createdDate", "J4")
					.xls("appId", "J7")
					.xls("groupDebiturName", "B258")
					.xls("groupOwnershipPercentage", "H258")
					.xls("industrySectorCode", "B259")
					.xls("yearsOfExperience", "H259")
					.xls("contactPersonName", "B260")
					.xls("groupDebiturAddress", "H260"));
		}
		
		if (LobType.isSmel(lobtype)) {
			specRows.add(SpecRow.get(insertDlosAppGroupDebitur).setSheet(Sheet.InformasiDebitur)
					.xls("createdDate", "J4")
					.xls("appId", "J7")
					.xls("groupDebiturName", "B256")
					.xls("groupOwnershipPercentage", "H256")
					.xls("industrySectorCode", "B257")
					.xls("yearsOfExperience", "H257")
					.xls("contactPersonName", "B258")
					.xls("groupDebiturAddress", "H258"));
		}
				
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}

}
