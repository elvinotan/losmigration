package com.btpn.migration.los.mapping.tujuandanfasilitas;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.NumberTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppFacilityDtl {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String dataId = store.getString("dataId");
				String fac_id = store.getString("fac_id");
				String fac_dtl_code = null; 
				String fac_dtl_desc = null;
				String fac_dtl_sts = null;
				
				String fac_name_code = mapper.getString("fac_name_code");
				Lookup lfac_name_code = store.getLookupByDescription(Lookup.FacilityName, fac_name_code);
				fac_name_code = (lfac_name_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityDtl") : lfac_name_code.getKey();
				if (fac_name_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String fac_type_code = mapper.getString("fac_type_code");
				Lookup lfac_type_code = store.getLookupByDescription(Lookup.FacilityType, fac_type_code);
				fac_type_code = (lfac_type_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityDtl") : lfac_type_code.getKey();
				
				String curr_code = mapper.getString("curr_code");
				if ("Rp".equals(curr_code)) curr_code = "IDR"; // Untuk Currency di manipulasi, mestinya ini gax masalah khan currency udah standard
				else {
					int sIdx = curr_code.indexOf("(");
					int eIdx = curr_code.indexOf(")");
					curr_code = curr_code.substring(sIdx+1, eIdx);
				}
				Lookup lcurr_code = store.getLookupByDescription(Lookup.Currency, curr_code);
				curr_code = (lcurr_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityDtl") : lcurr_code.getKey();
				
				String existing_amt = mapper.getString("existing_amt");
				String change_amt = mapper.getString("change_amt");
				String subm_amt = mapper.getString("subm_amt");
				String tenor =  NumberTool.extractNumberOnly(mapper.getString("tenor"));
				String start_date = DateTool.getYMD(mapper.getString("start_date"));
				String end_date = DateTool.getYMD(mapper.getString("end_date"));
				
				String int_rate = mapper.getString("int_rate");
				if (!StringTool.isEmpty(int_rate)) {
					int_rate = NumberTool.format(Double.valueOf(int_rate) * 100);
				}
				
				String prov_rate = mapper.getString("prov_rate");
				if (!StringTool.isEmpty(prov_rate)) {
					prov_rate = NumberTool.format(Double.valueOf(prov_rate) * 100);
				}
				
				String inst_type_code = mapper.getString("inst_type_code");
				Lookup linst_type_code = store.getLookupByDescription(Lookup.Instalment, inst_type_code);
				inst_type_code = (linst_type_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityDtl") : linst_type_code.getKey(); 
						
				String is_active = "1";
				String idr_subm_amt = mapper.getString("idr_subm_amt");
				String os_amt = mapper.getString("os_amt");
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppFacilityDtl",
						
						String.format(
						"INSERT INTO dlos_core.dlos_app_facility_dtl(dataId, fac_id, fac_dtl_code, fac_dtl_desc, fac_dtl_sts, fac_name_code, fac_type_code, curr_code, existing_amt, change_amt, subm_amt, tenor, start_date, end_date, int_rate, prov_rate, inst_type_code, is_active, modified_date, modified_by, created_date, created_by, idr_subm_amt, os_amt) " + 
						"VALUES(									 '%s',   '%s',   '%s',         '%s',         '%s',        '%s',          '%s',          '%s',      '%s',         '%s',       '%s',     '%s',  '%s',       '%s',     '%s',     '%s',      '%s',           %s,        '%s',          '%s',        '%s',         '%s',       '%s',         '%s');",
						dataId, fac_id, fac_dtl_code, fac_dtl_desc, fac_dtl_sts, fac_name_code, fac_type_code, curr_code, existing_amt, change_amt, subm_amt, tenor, start_date, end_date, int_rate, prov_rate, inst_type_code, is_active, modified_date, modified_by, created_date, created_by, idr_subm_amt, os_amt)
				};
			}
		};
		
		for (int i = 0; i < 7; i++) {
			int ctr = 101 + i;
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("change_amt", "E"+ctr)
					.xls("curr_code", "C"+ctr)
					.xls("end_date", "K"+ctr)
					.xls("existing_amt", "D"+ctr)
					.xls("fac_name_code", "A"+ctr)
					.xls("fac_type_code", "B"+ctr)
					.xls("idr_subm_amt", "G"+ctr)
					.xls("inst_type_code", "N"+ctr)
					.xls("int_rate", "L"+ctr)
					.xls("os_amt", "H"+ctr)
					.xls("prov_rate", "M"+ctr)
					.xls("start_date", "J"+ctr)
					.xls("subm_amt", "F"+ctr)
					.xls("tenor", "I"+ctr)
					);
			
		}
	}
}
