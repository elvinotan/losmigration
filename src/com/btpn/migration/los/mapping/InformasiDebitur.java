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
		migrasiDlosAppVerificationDebitur(lobType);
		migrasiDlosAppLegal(lobType);
		migrasiDlosAppManagement(lobType);
		migrasiDlosAppProperty(lobType);
	}

	private void migrasiDlosAppDetail(String lobType) {
		
		IActions insertDlosAppDetail = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String branchCode = mapper.getString("branchCode"); // Branch Code, berasal dari Region.java/ Hierarchy LOS.xlsx
				Region rBranchCode = store.getBranchByDescription(branchCode);
				branchCode = (rBranchCode == null) ?  null : rBranchCode.getBranchCode();
				
				String salesSquadName = (rBranchCode == null) ? null : rBranchCode.getPmsCode(); // SalesSqualName berasal dari Region.java / Hierarchy LOS.xlsx
				String areaName = null; // Belum
				
				String regionName = mapper.getString("regionName");
				if (regionName != null) {
					regionName = regionName.toUpperCase(); // RegionName berasal dari Region.java / Hierarchy LOS.xlsx
					Region region = store.getRegionByDescription(regionName);
					regionName = (region == null) ? null : region.getRegion();
				}
				
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
				if (debiturType != null) {
					String debiturTypeDesc = debiturType.split("_")[1].trim();
					Lookup ldebiturType = store.getLookupByDescription(Lookup.DebiturType, debiturTypeDesc);
					debiturType = (ldebiturType == null) ? null : ldebiturType.getKey();
				}
				
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

				return new String[] {
						"migrasiDlosAppDetail",
				        
						String.format(
						"INSERT INTO dlos_core.dlos_app_detail (`branchCode`, `salesSquadName`, `areaName`, `regionName`, `debiturName`, `aliasName`, `KTPNumber`, `KTPExpDate`, `NPWPNumber`, `placeOfBirth`, `dateOfBirth`, `religionCode`, `nationalityCode`, `residenceStatus`, `educationStatus`, `mobileNumber`, email, `KTPAddress`, `KTPPostalCode`, `KTPCityCode`, `KTPSubDistrict`, `KTPDistrict`, `currentAddress`, `currentAddressPostalCode`, `currentAddressSubDistrict`, `currentAddressDistrict`, `motherMaidenName`, `btpnRelationCode`, `sourceOfDebtor`, `debiturType`, gender, `maritalStatus`, `spouseName`, `customerType`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`, `businessAddress`, `businessCityCode`, `businessProvinceCode`, `businessPostalCode`, `businessPhoneCode`, `currentAddressCityCode`, cif, `rmName`, `rmCode`, `acmName`, `acmNik`, `btpnRelationDate`, `programProduct`, information, `managementRelation`, `managementChange`, `KTPRTRWCode`, `currentAddressRTRWCode`) "
								+ "VALUES(						'%s', 		  '%s',             '%s',       '%s',         '%s',          '%s',        '%s',        '%s',         '%s',         '%s',           '%s',          '%s',           '%s',              '%s',              '%s',              '%s',           '%s',  '%s',         '%s',            '%s',          '%s',             '%s',          '%s',             '%s',                       '%s',                        '%s',                     '%s',               '%s',               '%s',             '%s',          '%s',   '%s',            '%s',         '%s',           %s,         '%s',           '%s',         '%s',          '%s',        '%s',              '%s',               '%s',                   '%s',                 '%s',                '%s',                     '%s', '%s',    '%s',     '%s',      '%s',     '%s',               '%s',             '%s',        '%s',                 %s,                 '%s',          '%s');",
						branchCode, salesSquadName, areaName, regionName, debiturName, aliasName, KTPNumber, KTPExpDate, NPWPNumber, placeOfBirth, dateOfBirth, religionCode, nationalityCode, residenceStatus,
						educationStatus, mobileNumber, email, KTPAddress, KTPPostalCode, KTPCityCode, KTPSubDistrict, KTPDistrict, currentAddress, currentAddressPostalCode, currentAddressSubDistrict,
						currentAddressDistrict, motherMaidenName, btpnRelationCode, sourceOfDebtor, debiturType, gender, maritalStatus, spouseName, customerType, isActive, modifiedDate, modifiedBy, createdDate,
						createdBy, businessAddress, businessCityCode, businessProvinceCode, businessPostalCode, businessPhoneCode, currentAddressCityCode, cif, rmName, rmCode, acmName, acmNik,
						btpnRelationDate, programProduct, information, managementRelation, managementChange, KTPRTRWCode, currentAddressRTRWCode)
				};
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
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
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

				return new String[] {
						"migrasiDlosLoanProcess",				
						
						String.format(
						"INSERT INTO dlos_core.dlos_loan_process (`APPID`, `dataId`, `processStatus`, `processCode`, `stateCode`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) "
								+ "VALUES(						  '%s',     %s,      '%s',            '%s',          '%s',        %s,         '%s',           '%s',         '%s',            '%s');",
						APPID, dataId, processStatus, processCode, stateCode, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
			}
		};
		specRows.add(SpecRow.get(insertDlosLoanProcess).setSheet(Sheet.InformasiDebitur).xls("appId", "J7").fix("processStatus", "1").xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppContact(String lobType) {
		
		IActions insertDlosAppContact = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
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

				return new String[] {
						"migrasiDlosLoanProcess",
						
						String.format(
						"INSERT INTO dlos_core.dlos_app_contact (`contactName`, `genderCode`, `positionCode`, `fixedLineNumber`, `mobileNumber`, email, `dataId`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) "
								+ "VALUES(						 '%s',          '%s',         '%s',           '%s',              '%s',           '%s',   %s,       %s,        '%s',           '%s',         '%s',          '%s');",
						contactName, genderCode, positionCode, fixedLineNumber, mobileNumber, email, dataId, isActive,
						modifiedDate, modifiedBy, createdDate, createdBy)
				};
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
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String groupDebiturName = mapper.getString("groupDebiturName");
				if (groupDebiturName == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String groupOwnershipPercentage = mapper.getString("groupOwnershipPercentage"); // data yang masukan berupa range 0 s/d 1, sehingga harus di kali 100
				if (!StringTool.isEmpty(groupOwnershipPercentage)) {
					groupOwnershipPercentage = NumberTool.format(Double.valueOf(groupOwnershipPercentage) * 100);
				}
				 
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

				return new String[] {
						"migrasiDlosAppGroupDebitur",
				
						String.format( 
						"INSERT INTO dlos_core.dlos_app_groupdebitur (groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                     '%s',              '%s',                     '%s',               '%s',              '%s',              '%s',                '%s',   %s,     '%s',         '%s',       '%s',        '%s');", 
						groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
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
	
	private void migrasiDlosAppVerificationDebitur(String lobType) {
		
		IActions insertDlosAppVerificationDebitur = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				
				String is_bi_list = mapper.getString("is_bi_list");
				Lookup lis_bi_list = store.getLookupByDescription(Lookup.YesNo, is_bi_list);
				is_bi_list = (lis_bi_list == null) ? null : lis_bi_list.getKey();
						
				String bi_check_last_3mos = mapper.getString("bi_check_last_3mos");
				Lookup lbi_check_last_3mos = store.getLookupByDescription(Lookup.YesNo, bi_check_last_3mos);
				bi_check_last_3mos = (lbi_check_last_3mos == null) ? null : lbi_check_last_3mos.getKey();
				
				String is_business_nonIndustry = mapper.getString("is_business_nonIndustry");
				Lookup lis_business_nonIndustry = store.getLookupByDescription(Lookup.YesNo, is_business_nonIndustry);
				is_business_nonIndustry = (lis_business_nonIndustry == null) ? null : lis_business_nonIndustry.getKey();
				
				String positive_check = mapper.getString("positive_check");
				Lookup lpositive_check = store.getLookupByDescription(Lookup.YesNo, positive_check);
				positive_check = (lpositive_check == null) ? null : lpositive_check.getKey();
				
				String is_business_min_2years = mapper.getString("is_business_min_2years");
				Lookup lis_business_min_2years = store.getLookupByDescription(Lookup.YesNo, is_business_min_2years);
				is_business_min_2years = (lis_business_min_2years == null) ? null : lis_business_min_2years.getKey();
				
				String notes = StringTool.combine(mapper.getString("notes0"), mapper.getString("notes1"), mapper.getString("notes2"), mapper.getString("notes3"));
				notes = notes.replaceAll("<< Berikan penjelasan, jika terdapat informasi tambahan terkait kredibilitas debitur>>", "").trim();
				
				String is_active = "1";				
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				
				return new String[] {
						"migrasiDlosAppVerificationDebitur",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_verification_debitur (dataId, is_bi_list, bi_check_last_3mos, is_business_nonIndustry, positive_check, notes, is_active, created_date, created_by, modified_date, modified_by, is_business_min_2years) "+
						"VALUES(                                             '%s',    '%s',       '%s',               '%s',                    '%s',           '%s',  %s,        '%s',         '%s',       '%s',          '%s',        '%s') ",
						dataId, is_bi_list, bi_check_last_3mos, is_business_nonIndustry, positive_check, notes, is_active, created_date, created_by, modified_date, modified_by, is_business_min_2years)
				};
			}
		};
		
		if (LobType.isSmes(lobType)) {
			specRows.add(SpecRow.get(insertDlosAppVerificationDebitur).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("is_bi_list", "H138")
					.xls("bi_check_last_3mos", "H139")
					.xls("is_business_nonIndustry", "H141")
					.xls("positive_check", "H142")
					.xls("is_business_min_2years", "H140")
					.xls("notes0", "A144")
					.xls("notes1", "A145")
					.xls("notes2", "A146")
					.xls("notes3", "A147"));
		}
		
		if (LobType.isSmel(lobType)) {
			specRows.add(SpecRow.get(insertDlosAppVerificationDebitur).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("is_bi_list", "G136")
					.xls("bi_check_last_3mos", "G137")
					.xls("is_business_nonIndustry", "G139")
					.xls("positive_check", "G140")
					.xls("is_business_min_2years", "G138")
					.xls("notes0", "A142")
					.xls("notes1", "A143")
					.xls("notes2", "A144")
					.xls("notes3", "A145"));
		}
	}
	
	private void migrasiDlosAppLegal(String lobType) {
		
		IActions insertDlosAppLegal = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String legalEntityCode = mapper.getString("legalEntityCode");
				Lookup lLegalEntityCode = store.getLookupByDescription(Lookup.LegalEntity, legalEntityCode);
				legalEntityCode = (lLegalEntityCode == null) ? null : lLegalEntityCode.getKey();
						
				String PTSKNumber = null;
				String PTSKDate = null;
				String PTBNRINumber = null; 
				String PTBNRIDate = null;
				String CVRegistrationNumber = null; 
				String CVRegistrationDate = null;
				String lastDeedAmendmentNumber = null; 
				String lastDeedAmendmentDate = null;
				String ministryApprovalNumber = null; 
				String ministryApprovalDate = null;
				String lastDeedNumber = mapper.getString("lastDeedNumber");
				String lastDeedDate = DateTool.getYMD(mapper.getString("lastDeedDate"));
				String establishmentDeedDate = DateTool.getYMD(mapper.getString("establishmentDeedDate")); 
				String NPWPNumber = mapper.getString("NPWPNumber");
				String NPWPName = mapper.getString("NPWPName");
				String SIUPNumber = mapper.getString("SIUPNumber"); 
				String SIUPName =  mapper.getString("SIUPName");
				String SIUPDate = null;
				String SIUPYear = mapper.getString("SIUPYear");
				String TDPNIBNumber = mapper.getString("TDPNIBNumber");
				String TDPNIBName = mapper.getString("TDPNIBName"); 
				String TDPNIBDate = null;
				String TDPNIBYear = mapper.getString("TDPNIBYear");
				String TDPExpiryDate = null; 
				String SKDPNumber = null;
				String SKDPDate = null;
				String SKDPExpiryDate = null;
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null; 
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;
				
				return new String[] {
						"insertDlosAppLegal",
				
						String.format( 
						"INSERT INTO dlos_core.dlos_app_legal (legalEntityCode, PTSKNumber, PTSKDate, PTBNRINumber, PTBNRIDate, CVRegistrationNumber, CVRegistrationDate, lastDeedAmendmentNumber, lastDeedAmendmentDate, ministryApprovalNumber, ministryApprovalDate, lastDeedNumber, lastDeedDate, establishmentDeedDate, NPWPNumber, NPWPName, SIUPNumber, SIUPName, SIUPDate, TDPNIBNumber, TDPNIBDate, TDPExpiryDate, SKDPNumber, SKDPDate, SKDPExpiryDate, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy, TDPNIBName, SIUPYear, TDPNIBYear) " + 
						"VALUES(                               '%s',            '%s',       '%s',     '%s',         '%s',       '%s',                 '%s',               '%s',                    '%s',                  '%s',                   '%s',                 '%s',           '%s',         '%s',                  '%s',       '%s',     '%s',       '%s',     '%s',     '%s',         '%s',       '%s',          '%s',       '%s',     '%s',           '%s',   %s,       '%s',         '%s',       '%s',        '%s',      '%s',       '%s',     '%s');", 
						legalEntityCode, PTSKNumber, PTSKDate, PTBNRINumber, PTBNRIDate, CVRegistrationNumber, CVRegistrationDate, lastDeedAmendmentNumber, lastDeedAmendmentDate, ministryApprovalNumber, ministryApprovalDate, lastDeedNumber, lastDeedDate, establishmentDeedDate, NPWPNumber, NPWPName, SIUPNumber, SIUPName, SIUPDate, TDPNIBNumber, TDPNIBDate, TDPExpiryDate, SKDPNumber, SKDPDate, SKDPExpiryDate, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy, TDPNIBName, SIUPYear, TDPNIBYear)
				};
			}
		};
		specRows.add(SpecRow.get(insertDlosAppLegal).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4")
				.xls("establishmentDeedDate", "C91")
				.xls("lastDeedDate", "C90")
				.xls("lastDeedNumber", "C89")
				.xls("legalEntityCode", "C88")
				.xls("NPWPName", "G92")				
				.xls("NPWPNumber", "C92")
				.xls("SIUPYear", "K93")
				.xls("SIUPName", "G93")
				.xls("SIUPNumber", "C93")
				.xls("TDPNIBYear", "K94")
				.xls("TDPNIBName", "G94")
				.xls("TDPNIBNumber", "C94"));
	}
	
	private void migrasiDlosAppManagement(String lobType) {
		
		IActions insertDlosAppManagement = new IActions() {

			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String managementName = mapper.getString("managementName");
				if (managementName == null) return null; // ini artinya  datanya kosong dan jgn di process querynya
				
				String managementPosition = mapper.getString("managementPosition");
				Lookup lmanagementPosition = store.getLookupByDescription(Lookup.Position, managementPosition);
				managementPosition = (lmanagementPosition == null) ? null : lmanagementPosition.getKey();
				
				String idCode = mapper.getString("idCode");
				Lookup lIdCode = store.getLookupByDescription(Lookup.IDCard, idCode); 
				idCode = (lIdCode == null) ? null : lIdCode.getKey(); 
						
				String idNumber =  mapper.getString("idNumber");
				String sharePercentage = mapper.getString("sharePercentage");
				if (sharePercentage != null && "-".equalsIgnoreCase(sharePercentage.trim())) sharePercentage = null;
				if (!StringTool.isEmpty(sharePercentage)) {
					sharePercentage = NumberTool.format(Double.valueOf(sharePercentage) * 100);
				}
				
				String managementAddress = mapper.getString("managementAddress");
				String datiII = mapper.getString("datiII"); 
				CommonService csDati2 = store.getCommonByDescription(CommonService.TYPE_CITY, datiII);
				datiII = (csDati2 == null) ? null : csDati2.getCode();
				
				String NPWPNumber = mapper.getString("NPWPNumber");
				String age = mapper.getString("age");
				String experienceInYears = mapper.getString("experienceInYears");
				String joinedSinceYears = mapper.getString("joinedSinceYears");
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));
				String createdBy = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppManagement",
				
						String.format( 	
						"INSERT INTO dlos_core.dlos_app_management (managementName, managementPosition, idCode, idNumber, sharePercentage, managementAddress, datiII, NPWPNumber, age, experienceInYears, joinedSinceYears, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                    '%s',           '%s',               '%s',   '%s',     '%s',            '%s',              '%s',   '%s',       '%s','%s',              '%s',             '%s',   %s,     '%s',         '%s',       '%s',        '%s');", 
						managementName, managementPosition, idCode, idNumber, sharePercentage, managementAddress, datiII, NPWPNumber, age, experienceInYears, joinedSinceYears, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
			}			
		};
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 11; i++) {
				int inc = 122 + i;
				
				specRows.add(SpecRow.get(insertDlosAppManagement).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("age", "L"+inc)
						.xls("datiII", "H"+inc)
						.xls("experienceInYears", "M"+inc)
						.xls("idCode", "B"+inc)
						.xls("idNumber", "C"+inc)
						.xls("joinedSinceYears", "N"+inc)
						.xls("managementAddress", "F"+inc)
						.xls("managementName", "A"+inc)
						.xls("managementPosition", "J"+inc)
						.xls("NPWPNumber", "K"+inc)
						.xls("sharePercentage", "E"+inc));
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 11; i++) {
				int inc = 120 + i;
				
				specRows.add(SpecRow.get(insertDlosAppManagement).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("age", "L"+inc)
						.xls("datiII", "H"+inc)
						.xls("experienceInYears", "M"+inc)
						.xls("idCode", "B"+inc)
						.xls("idNumber", "C"+inc)
						.xls("joinedSinceYears", "N"+inc)
						.xls("managementAddress", "E"+inc)
						.xls("managementName", "A"+inc)
						.xls("managementPosition", "J"+inc)
						.xls("NPWPNumber", "K"+inc)
						.xls("sharePercentage", "D"+inc));
			}
		}
	}
	
	private void migrasiDlosAppProperty(String lobType) {
		
		IActions insertDlosAppProperty = new IActions() {

			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String homeOwnershipStatus = mapper.getString("homeOwnershipStatus");
				Lookup lhomeOwnershipStatus = store.getLookupByDescription(Lookup.HomeOwnership, homeOwnershipStatus);
				homeOwnershipStatus = (lhomeOwnershipStatus == null) ? null : lhomeOwnershipStatus.getKey();
				
				String businessOwnershipStatus = null;
				String ownershipDate = DateTool.getYMD(mapper.getString("ownershipDate"));
				String leaseDate = DateTool.getYMD(mapper.getString("leaseDate"));
				String businessEntityType = null;
				String businessName = null;
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");;
				String createdDate = DateTool.getYMD(mapper.getString("createdDate"));;
				String createdBy = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppProperty",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_property (homeOwnershipStatus, businessOwnershipStatus, ownershipDate, leaseDate, businessEntityType, businessName, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                  '%s',                '%s',                    '%s',          '%s',      '%s',               '%s',         '%s',   %s,       '%s',         '%s',       '%s',        '%s'); ", 
						homeOwnershipStatus, businessOwnershipStatus, ownershipDate, leaseDate, businessEntityType, businessName, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy
					)
				};
			}
			
		};
		specRows.add(SpecRow.get(insertDlosAppProperty).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4")
				.xls("homeOwnershipStatus", "D27")
				.xls("leaseDate", "H29")
				.xls("ownershipDate", "H28"));
				
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}

}
