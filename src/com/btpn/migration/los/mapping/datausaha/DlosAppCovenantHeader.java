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

public class DlosAppCovenantHeader {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String cov_header_detail_code = mapper.getString("cov_header_detail_code");
				if (cov_header_detail_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String cov_header_code = null; 
				String cov_header_sts = null;				 
				String cov_header_monitored_by_code = mapper.getString("cov_header_monitored_by_code");
				
				String cov_header_freq_code = mapper.getString("cov_header_freq_code"); 
				Lookup lcov_header_freq_code = store.getLookupByDescription(Lookup.Frequency, cov_header_freq_code);
				cov_header_freq_code = (lcov_header_freq_code == null) ? mapper.logMapperProblem("migrasiDlosAppCovenantHeader") : lcov_header_freq_code.getKey();
				
				String cov_header_existing_condition_code = mapper.getString("cov_header_existing_condition_code");
				Lookup lcov_header_existing_condition_code = store.getLookupByDescription(Lookup.YesNo, cov_header_existing_condition_code);
				cov_header_existing_condition_code = (lcov_header_existing_condition_code == null) ? mapper.logMapperProblem("migrasiDlosAppCovenantHeader") : lcov_header_existing_condition_code.getKey();
				
				String cov_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppCovenantHeader",
						
						String.format(
								"INSERT INTO dlos_core.dlos_app_covenant_header (dataId, business_id, cov_header_code, cov_header_sts, cov_header_detail_code, cov_header_monitored_by_code, cov_header_freq_code, cov_header_existing_condition_code, cov_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                         '%s',   '%s',        '%s',            '%s',           '%s',                   '%s',                         '%s',                 '%s',                               '%s',            %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, cov_header_code, cov_header_sts, cov_header_detail_code, cov_header_monitored_by_code, cov_header_freq_code, cov_header_existing_condition_code, cov_header_desc, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 15; i++) {
				int ctr = 266+i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("cov_header_existing_condition_code", "P"+ctr)
						.xls("cov_header_freq_code", "O"+ctr)
						.xls("cov_header_detail_code", "A"+ctr)
						.xls("cov_header_monitored_by_code", "L"+ctr)						
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 15; i++) {
				int ctr = 264+i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("cov_header_existing_condition_code", "N"+ctr)
					.xls("cov_header_freq_code", "M"+ctr)
					.xls("cov_header_detail_code", "A"+ctr)		
					.xls("cov_header_monitored_by_code", "L"+ctr)
					);
			}
		}
	}
}
