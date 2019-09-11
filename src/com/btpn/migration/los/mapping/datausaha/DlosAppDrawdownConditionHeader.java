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

public class DlosAppDrawdownConditionHeader {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String drawdown_header_detail_code = mapper.getString("drawdown_header_detail_code");
				if (drawdown_header_detail_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String drawdown_header_code = null; 
				String drawdown_header_sts = null;
				String drawdown_header_monitored_by_code = mapper.getString("drawdown_header_monitored_by_code"); 
				
				String drawdown_header_existing_condition_code = mapper.getString("drawdown_header_existing_condition_code");
				Lookup ldrawdown_header_existing_condition_code = store.getLookupByDescription(Lookup.YesNo, drawdown_header_existing_condition_code);
				drawdown_header_existing_condition_code = (ldrawdown_header_existing_condition_code == null) ? mapper.logMapperProblem("migrasiDlosAppDrawdownConditionHeader") : ldrawdown_header_existing_condition_code.getKey();
				
				String drawdown_header_desc = null;
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppDrawdownConditionHeader",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_drawdown_condition_header(dataId, business_id, drawdown_header_code, drawdown_header_sts, drawdown_header_detail_code, drawdown_header_monitored_by_code, drawdown_header_existing_condition_code, drawdown_header_desc, is_active, modified_date, modified_by, created_date, created_by)" + 
								"VALUES(                                                  '%s',   '%s',        '%s',                 '%s',                '%s',                        '%s',                             '%s',                                     '%s',                 %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, drawdown_header_code, drawdown_header_sts, drawdown_header_detail_code, drawdown_header_monitored_by_code, drawdown_header_existing_condition_code, drawdown_header_desc, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 8; i++) {
				int ctr = 299 +i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("drawdown_header_detail_code", "A"+ctr)
						.xls("drawdown_header_existing_condition_code", "O"+ctr)
						.xls("drawdown_header_monitored_by_code", "L"+ctr)
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 8; i++) {
				int ctr = 297 +i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("drawdown_header_detail_code", "A"+ctr)
						.xls("drawdown_header_existing_condition_code", "M"+ctr)
						.xls("drawdown_header_monitored_by_code", "L"+ctr)
						);
			}
		}
	}
}
