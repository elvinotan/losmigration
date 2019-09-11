package com.btpn.migration.los.mapping.datausaha;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppPrecedentHeader {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String precedent_header_detail_code = mapper.getString("precedent_header_detail_code");
				if (precedent_header_detail_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id");
				String precedent_header_code = null; 
				String precedent_header_sts = null; 
				
				String precedent_header_monitored_by_code = mapper.getString("precedent_header_monitored_by_code"); 
				
				String precedent_header_existing_condition_code = mapper.getString("precedent_header_existing_condition_code");
				Lookup lprecedent_header_existing_condition_code = store.getLookupByDescription(Lookup.YesNo, precedent_header_existing_condition_code);
				precedent_header_existing_condition_code = (lprecedent_header_existing_condition_code == null) ? mapper.logMapperProblem("migrasiDlosAppPrecedentHeader") : lprecedent_header_existing_condition_code.getKey();
				
				String precedent_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppPrecedentHeader",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_precedent_header(dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by) "+ 
								"VALUES(                                         '%s',   '%s',        '%s',                  '%s',                 '%s',                         '%s',                               '%s',                                     '%s',                  %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by)
						
				};
			}
		}; 

		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 10; i++) {
				int ctr = 286+i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("precedent_header_detail_code", "A"+ctr)
						.xls("precedent_header_existing_condition_code", "O"+ctr)
						.xls("precedent_header_monitored_by_code", "L"+ctr)
						
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 10; i++) {
				int ctr = 284+i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("precedent_header_detail_code", "A"+ctr)
						.xls("precedent_header_existing_condition_code", "M"+ctr)
						.xls("precedent_header_monitored_by_code", "L"+ctr)
						);
			}
		}
	}
}
