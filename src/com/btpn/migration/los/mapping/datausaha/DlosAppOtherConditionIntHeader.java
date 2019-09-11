package com.btpn.migration.los.mapping.datausaha;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppOtherConditionIntHeader {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String other_condition_int_header_detail_code = mapper.getString("other_condition_int_header_detail_code");
				if (other_condition_int_header_detail_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String other_condition_int_header_code = null; 
				String other_condition_int_header_sts = null;				 
				String other_condition_int_header_existing_condition_code = null; 
				String other_condition_int_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppOtherConditionIntHeader",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_other_condition_int_header(dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                                   '%s',   '%s',        '%s',                            '%s',                           '%s',                                   '%s',                                               '%s',                            %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 7; i++) {
				int ctr = 321 + i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("other_condition_int_header_detail_code", "A"+ctr)						
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 7; i++) {
				int ctr = 319 + i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("other_condition_int_header_detail_code", "A"+ctr)
						);
			}
		}
	}
}
