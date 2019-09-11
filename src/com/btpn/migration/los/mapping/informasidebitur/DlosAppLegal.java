package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppLegal {
	
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `SIUPNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `TDPNIBNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `NPWPName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `SIUPName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `NPWPNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_legal MODIFY COLUMN `TDPNIBName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String legalEntityCode = mapper.getString("legalEntityCode");
				if ("Perseroan Terbatas (PERSERO)".equals(legalEntityCode)) legalEntityCode = "Perseroan Terbatas (PT/PERSEROAN)";
				Lookup lLegalEntityCode = store.getLookupByDescription(Lookup.LegalEntity, legalEntityCode);
				legalEntityCode = (lLegalEntityCode == null) ? mapper.logMapperProblem("migrasiDlosAppLegal") : lLegalEntityCode.getKey();
						
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
				
				String lastDeedDate = mapper.getString("lastDeedDate");
				if (StringTool.isEmptyTag(lastDeedDate)) {
					lastDeedDate = null;
				}else {
					if ("04-03-2011".equals(lastDeedDate)) lastDeedDate = "2011-03-04 00:00:00";
					if ("30-06-2015".equals(lastDeedDate)) lastDeedDate = "2015-06-30 00:00:00";
					if ("25-02-2014".equals(lastDeedDate)) lastDeedDate = "2014-02-25 00:00:00";
					if ("22 Januari 2015".equals(lastDeedDate)) lastDeedDate = "2015-01-22 00:00:00";
					if ("26 Mei 2015".equals(lastDeedDate)) lastDeedDate = "2015-05-26 00:00:00";
					if ("23 Desember 2014".equals(lastDeedDate)) lastDeedDate = "2014-12-23 00:00:00";
					if ("09 Oktober 2014".equals(lastDeedDate)) lastDeedDate = "2014-10-09 00:00:00";
					if ("07 Oktober 2013".equals(lastDeedDate)) lastDeedDate = "2013-10-07 00:00:00";
					if ("18 Desember 2015".equals(lastDeedDate)) lastDeedDate = "2015-12-18 00:00:00";
					if ("3.0".equals(lastDeedDate)) lastDeedDate = null;
					
					lastDeedDate = DateTool.getYMD(lastDeedDate);	
				}
				
				String establishmentDeedDate = mapper.getString("establishmentDeedDate");
				if (StringTool.isEmptyTag(establishmentDeedDate)) {
					establishmentDeedDate = null;
				}else {
					if ("28-08-2000".equals(establishmentDeedDate)) establishmentDeedDate = "2000-08-28 00:00:00";
					if ("16 Maret 2009".equals(establishmentDeedDate)) establishmentDeedDate = "2009-03-16 00:00:00";
					if ("30 Desember 1991".equals(establishmentDeedDate)) establishmentDeedDate = "1991-12-30 00:00:00";
					if ("04-03-2011".equals(establishmentDeedDate)) establishmentDeedDate = "2011-03-04 00:00:00";
					if ("4 Februari 2010".equals(establishmentDeedDate)) establishmentDeedDate = "2010-02-04 00:00:00";
					if ("20 Februari 2006".equals(establishmentDeedDate)) establishmentDeedDate = "2006-02-20 00:00:00";
					if ("09 Oktober 2014".equals(establishmentDeedDate)) establishmentDeedDate = "2014-10-09 00:00:00";
					if ("18 Juni 2009".equals(establishmentDeedDate)) establishmentDeedDate = "2009-06-18 00:00:00";
					if ("18 Desember 2015".equals(establishmentDeedDate)) establishmentDeedDate = "2015-12-18 00:00:00";
					if ("06".equals(establishmentDeedDate)) establishmentDeedDate = null;					
					establishmentDeedDate = DateTool.getYMD(establishmentDeedDate);	
				}
				
				String NPWPNumber = mapper.getString("NPWPNumber");
				String NPWPName = mapper.getString("NPWPName");
				
				String SIUPNumber = mapper.getString("SIUPNumber");
				if (StringTool.isEmptyTag(SIUPNumber)) { SIUPNumber = null;
				}else {
					if (SIUPNumber.startsWith("00180/10-12/PK/III/2018 ")) SIUPNumber = "00180/10-12/PK/III/2018";
				}
				
				String SIUPName =  mapper.getString("SIUPName");
				String SIUPDate = null;
				String SIUPYear = mapper.getString("SIUPYear");

				String TDPNIBNumber = mapper.getString("TDPNIBNumber");
				if (StringTool.isEmptyTag(TDPNIBNumber)) { TDPNIBNumber = null;
				}else {
					if (TDPNIBNumber.startsWith("10.24.5.47.23777 ")) TDPNIBNumber = "10.24.5.47.23777"; 
				}
				
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
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
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
		
		 if (StringTool.inArray(filename, "099. PT. BPR Utomo Manunggal Sejahtera.xls", "199 PT. BPR Nusamba Singaparna.xls")) {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("establishmentDeedDate", "C85")//Tgl Akta Pendirian
					.xls("lastDeedDate", "C84")// Tgl Akta Terakhir
					.xls("lastDeedNumber", "C83") //Nomor Akta Terakhir
					.xls("legalEntityCode", "C82") //Entitas Legal
					.xls("NPWPName", "G86") //NPWP Atasname
					.xls("NPWPNumber", "C86") //NPWP
					.xls("SIUPYear", "K87") //SIUP Tahun
					.xls("SIUPName", "G87") //SIUP Atasnama
					.xls("SIUPNumber", "C87") //SIUP
					.xls("TDPNIBYear", "K88") //TDP Tahun
					.xls("TDPNIBName", "G88") //TDP Atasname
					.xls("TDPNIBNumber", "C88")); //TDP
		}else {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("establishmentDeedDate", "C91") //Tgl Akta Pendirian
					.xls("lastDeedDate", "C90") // Tgl Akta Terakhir
					.xls("lastDeedNumber", "C89") //Nomor Akta Terakhir
					.xls("legalEntityCode", "C88") //Entitas Legal
					.xls("NPWPName", "G92") //NPWP Atasname
					.xls("NPWPNumber", "C92") //NPWP
					.xls("SIUPYear", "K93") //SIUP Tahun
					.xls("SIUPName", "G93") //SIUP Atasnama
					.xls("SIUPNumber", "C93") //SIUP
					.xls("TDPNIBYear", "K94") //TDP Tahun
					.xls("TDPNIBName", "G94") //TDP Atasname
					.xls("TDPNIBNumber", "C94")); //TDP
		}
	}
}
