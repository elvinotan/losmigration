package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.Region;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppDetail {
	
	//ALTER TABLE dlos_core.dlos_app_detail MODIFY COLUMN `businessPhoneCode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_detail MODIFY COLUMN `businessAddress` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_detail MODIFY COLUMN `managementRelation` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_detail MODIFY COLUMN `debiturName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String regionName = mapper.getString("regionName");
				if (regionName != null) {
					regionName = regionName.toUpperCase(); // RegionName berasal dari Region.java / Hierarchy LOS.xlsx
					Region region = store.getRegionByDescription(regionName);
					regionName = (region == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : region.getRegion();
				}
				
				String branchCode = mapper.getString("branchCode"); // Branch Code, berasal dari Region.java/ Hierarchy LOS.xlsx
				Region rBranchCode = store.getBranchByDescription(branchCode);
				branchCode = (rBranchCode == null) ?  mapper.logMapperProblem("migrasiDlosAppDetail") : rBranchCode.getBranchCode();
				
				String salesSquadName = (rBranchCode == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : rBranchCode.getPmsCode(); // SalesSqualName berasal dari Region.java / Hierarchy LOS.xlsx
				String areaName = null; 
				
				String debiturName = mapper.getString("debiturName");
				String aliasName = null; 
				String KTPNumber = null; 
				String KTPExpDate = null; 
				String NPWPNumber = null; 
				String placeOfBirth = null; 
				String dateOfBirth = null; 
				String religionCode = null; 
				String nationalityCode = null; 
				String residenceStatus = null; 
				String educationStatus = null; 
				String mobileNumber = null; 
				String email = null; 
				String KTPAddress = null; 
				String KTPPostalCode = null; 
				String KTPCityCode = null; 
				String KTPSubDistrict = null; 
				String KTPDistrict = null; 
				String currentAddress = null; 
				String currentAddressPostalCode = null; 
				String currentAddressSubDistrict = null; 
				String currentAddressDistrict = null; 
				String motherMaidenName = null; 
				String btpnRelationCode = null; 
				
				String sourceOfDebtor = mapper.getString("sourceOfDebtor"); // SourceOfDebtor berasal dari Lookup.DebtorSource
				Lookup lsourceOfDebtor = store.getLookupByDescription(Lookup.DebtorSource, sourceOfDebtor);
				sourceOfDebtor = (lsourceOfDebtor == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : lsourceOfDebtor.getKey();
				
				String debiturType = mapper.getString("debiturType"); // DebiturType berasal dari Lookup.DebiturType
				if (debiturType != null) {
					String debiturTypeDesc = debiturType.split("_")[1].trim();
					Lookup ldebiturType = store.getLookupByDescription(Lookup.DebiturType, debiturTypeDesc);
					debiturType = (ldebiturType == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : ldebiturType.getKey();
				}
				
				String gender = null; 
				String maritalStatus = null; 
				String spouseName = mapper.getString("spouseName");
				String customerType = null; 
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
				String createdBy = MIGRATION;
				String businessAddress = StringTool.combine(mapper.getString("businessAddress0"), mapper.getString("businessAddress1"));
				
				String businessCityCode = mapper.getString("businessCityCode"); // BusinessCityCode berasal dari commonService TYPE_CITY
				CommonService csbusinessCityCode = store.getCommonByDescription(CommonService.TYPE_CITY, businessCityCode);
				businessCityCode = (csbusinessCityCode == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : csbusinessCityCode.getCode();
				
				String businessProvinceCode = mapper.getString("businessProvinceCode");  // BusinessCityCode berasal dari commonService TYPE_PROVINCE
				CommonService csbusinessProvinceCode = store.getCommonByDescription(CommonService.TYPE_PROVINCE, businessProvinceCode);
				businessProvinceCode = (csbusinessProvinceCode == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : csbusinessProvinceCode.getCode();
				
				String businessPostalCode = mapper.clearDecimal(mapper.getString("businessPostalCode"));
				
				String businessPhoneCode = mapper.getString("businessPhoneCode");
				
				String currentAddressCityCode = null;
				String cif = mapper.getString("cif");
				String rmName = mapper.getString("rmName");
				String rmCode = mapper.getString("rmCode");
				String acmName = mapper.getString("acmName");
				String acmNik = mapper.getString("acmNik");
				
				String btpnRelationDate = mapper.getString("btpnRelationDate");
				if (StringTool.isEmptyTag(btpnRelationDate)) { btpnRelationDate = null;
				}else {
					if ("Maret 2015".equals(btpnRelationDate)) btpnRelationDate = "2015-03-01 00:00:00";
					if ("27 November 2014".equals(btpnRelationDate)) btpnRelationDate = "2014-11-27 00:00:00";
					if ("6 Juli 2015".equals(btpnRelationDate)) btpnRelationDate = "2015-07-06 00:00:00";
					if ("16 Des 2014".equals(btpnRelationDate)) btpnRelationDate = "2014-12-16 00:00:00";
					if ("16 Januari 2015".equals(btpnRelationDate)) btpnRelationDate = "2015-01-16 00:00:00";
					if ("19 Juni 2014".equals(btpnRelationDate)) btpnRelationDate = "2014-06-19 00:00:00";
					if ("13 Juli 2015".equals(btpnRelationDate)) btpnRelationDate = "2015-07-13 00:00:00";
					if ("16 May 2014".equals(btpnRelationDate)) btpnRelationDate = "2014-05-16 00:00:00";
					
					btpnRelationDate = DateTool.getYMD(btpnRelationDate);
				}
				
				String programProduct = mapper.getString("programProduct"); //ProgramProduct berasal dari Lookup.ProductProgram
				Lookup lProgramProduct = store.getLookupByDescription(Lookup.ProductProgram, programProduct);
				programProduct = (lProgramProduct == null) ? mapper.logMapperProblem("migrasiDlosAppDetail") : lProgramProduct.getKey();
				
				String information = mapper.getString("information");
				String managementRelation = StringTool.combine(mapper.getString("managementRelation0"), mapper.getString("managementRelation1"));
				
				String managementChange = mapper.getString("managementChange");
				if (StringTool.isEmptyTag(managementChange)) { managementChange = null;
				}else {
					if (managementChange.startsWith("Tgl 23 Desember 2008 diangkat")) managementChange = null;
					if ("2 bulan".equals(managementChange)) managementChange = null;
					if ("2014-11-05 00:00:00:0000".equals(managementChange)) managementChange = null;
					if ("tidak ada".equals(managementChange)) managementChange = null;
					if ("Nihil".equals(managementChange)) managementChange = null;	
				}
					
				String KTPRTRWCode = null; 
				String currentAddressRTRWCode = null; 

				return new String[] {
						"migrasiDlosAppDetail",
				        
						String.format(
						"INSERT INTO dlos_core.dlos_app_detail (`branchCode`, `salesSquadName`, `areaName`, `regionName`, `debiturName`, `aliasName`, `KTPNumber`, `KTPExpDate`, `NPWPNumber`, `placeOfBirth`, `dateOfBirth`, `religionCode`, `nationalityCode`, `residenceStatus`, `educationStatus`, `mobileNumber`, email, `KTPAddress`, `KTPPostalCode`, `KTPCityCode`, `KTPSubDistrict`, `KTPDistrict`, `currentAddress`, `currentAddressPostalCode`, `currentAddressSubDistrict`, `currentAddressDistrict`, `motherMaidenName`, `btpnRelationCode`, `sourceOfDebtor`, `debiturType`, gender, `maritalStatus`, `spouseName`, `customerType`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`, `businessAddress`, `businessCityCode`, `businessProvinceCode`, `businessPostalCode`, `businessPhoneCode`, `currentAddressCityCode`, cif, `rmName`, `rmCode`, `acmName`, `acmNik`, `btpnRelationDate`, `programProduct`, information, `managementRelation`, `managementChange`, `KTPRTRWCode`, `currentAddressRTRWCode`) "
								+ "VALUES(						'%s', 		  '%s',             '%s',       '%s',         '%s',          '%s',        '%s',        '%s',         '%s',         '%s',           '%s',          '%s',           '%s',              '%s',              '%s',              '%s',           '%s',  '%s',         '%s',            '%s',          '%s',             '%s',          '%s',             '%s',                       '%s',                        '%s',                     '%s',               '%s',               '%s',             '%s',          '%s',   '%s',            '%s',         '%s',           %s,         '%s',           '%s',         '%s',          '%s',        '%s',              '%s',               '%s',                   '%s',                 '%s',                '%s',                     '%s', '%s',    '%s',     '%s',      '%s',     '%s',               '%s',             '%s',        '%s',                 '%s',                 '%s',          '%s');",
						branchCode, salesSquadName, areaName, regionName, debiturName, aliasName, KTPNumber, KTPExpDate, NPWPNumber, placeOfBirth, dateOfBirth, religionCode, nationalityCode, residenceStatus,
						educationStatus, mobileNumber, email, KTPAddress, KTPPostalCode, KTPCityCode, KTPSubDistrict, KTPDistrict, currentAddress, currentAddressPostalCode, currentAddressSubDistrict,
						currentAddressDistrict, motherMaidenName, btpnRelationCode, sourceOfDebtor, debiturType, gender, maritalStatus, spouseName, customerType, isActive, modifiedDate, modifiedBy, createdDate,
						createdBy, businessAddress, businessCityCode, businessProvinceCode, businessPostalCode, businessPhoneCode, currentAddressCityCode, cif, rmName, rmCode, acmName, acmNik,
						btpnRelationDate, programProduct, information, managementRelation, managementChange, KTPRTRWCode, currentAddressRTRWCode)
				};
			}
		};
		
		specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4")
				.xls("branchCode", "C9")
				.xls("regionName", "C8")
				.xls("debiturName", "J5")
				.xls("sourceOfDebtor", "C77")
				.xls("debiturType", "D26")
				.xls("spouseName", "D36")
				.xls("businessAddress0", "D20")
				.xls("businessAddress1", "D21")
				.xls("businessCityCode", "D22")
				.xls("businessProvinceCode", "D23")
				.xls("businessPostalCode", "D24")
				.xls("businessPhoneCode", "D25")
				.xls("cif", "J6")
				.xls("rmName", "C4")
				.xls("rmCode", "C5")
				.xls("acmName", "C6")
				.xls("acmNik", "C7")
				.xls("btpnRelationDate", "J8")
				.xls("programProduct", "J9")
				.xls("information", "A81")
				.xls("managementRelation0", "A134")
				.xls("managementRelation1", "A135")
				.xls("managementChange", "D34")
				.pk("dataId"));
	}
}
