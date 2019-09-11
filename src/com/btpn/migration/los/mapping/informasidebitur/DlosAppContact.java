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

public class DlosAppContact {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String contactName = mapper.getString("contactName");
				if (StringTool.isEmptyTag(contactName)) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute

				String genderCode = mapper.getString("genderCode");
				Lookup lgenderCode = store.getLookupByDescription(Lookup.Gender, genderCode);
				genderCode = (lgenderCode == null) ? mapper.logMapperProblem("migrasiDlosAppContact") : lgenderCode.getKey();

				String positionCode = mapper.getString("positionCode");
				if (StringTool.isEmptyTag(positionCode)) { positionCode = null;
				}else {
					Lookup lpositionCode = store.getLookupByDescription(Lookup.Position, positionCode);
					positionCode = (lpositionCode == null) ? mapper.logMapperProblem("migrasiDlosAppContact") : lpositionCode.getKey();	
				}
				
				String fixedLineNumber = mapper.getString("fixedLineNumber");
				if (StringTool.isEmptyTag(fixedLineNumber)) { fixedLineNumber = null; 
				}else {
					if ("Debitur_Perorangan".equals(fixedLineNumber)) fixedLineNumber = null;
					if ("Direktur CV. Melana Motor".equals(fixedLineNumber)) fixedLineNumber = null;
					if ("Nomor telepon (fixed line)".equals(fixedLineNumber)) fixedLineNumber = null;
					if ("Persero Komanditer".equals(fixedLineNumber)) fixedLineNumber = null;
					if ("idem".equals(fixedLineNumber)) fixedLineNumber = null;
					
					fixedLineNumber = StringTool.cleanPhone(fixedLineNumber); 
				}
				
				String mobileNumber = mapper.getString("mobileNumber");
				if (StringTool.isEmptyTag(mobileNumber)) { mobileNumber = null; 
				}else {
					if ("Nomor mobile phone".equals(mobileNumber)) mobileNumber = null;
					
					mobileNumber = StringTool.cleanPhone(mobileNumber); 
				}
				
				String email = mapper.getString("email");
				if (StringTool.isEmptyTag(email)) { email = null; }
				
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
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
		
		for (int i = 0; i < 5; i++) {
			int ctr = 71;
			
			if (StringTool.inArray(filename, "099. PT. BPR Utomo Manunggal Sejahtera.xls", "199 PT. BPR Nusamba Singaparna.xls")) {
				ctr = 75;
			}
			
			ctr = ctr+i;
			
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("contactName", "A"+ctr)
					.xls("genderCode", "K"+ctr)
					.xls("positionCode", "C"+ctr)
					.xls("fixedLineNumber", "E"+ctr)
					.xls("mobileNumber", "G"+ctr)
					.xls("email", "I"+ctr)
					);
		}
	}
}
