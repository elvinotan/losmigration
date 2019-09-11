package com.btpn.migration.los.mapping.datausaha;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppBusinessOth {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id");
				String business_oth_code = null;
				String business_oth_sts = null;
				String other_business_type_code = null; 
				String other_business_pct = null;
				String company_period_cnt = null;
				String is_active = "1";
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppBusinessOth",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_business_oth (dataId, business_id, business_oth_code, business_oth_sts, other_business_type_code, other_business_pct, company_period_cnt, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                      '%s',   '%s',        '%s',              '%s',             '%s',                     '%s',               '%s',               %s,        '%s',          '%s',        '%s',         '%s'); ", 
								dataId, business_id, business_oth_code, business_oth_sts, other_business_type_code, other_business_pct, company_period_cnt, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
}
