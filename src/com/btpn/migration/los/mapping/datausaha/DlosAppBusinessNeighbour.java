package com.btpn.migration.los.mapping.datausaha;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppBusinessNeighbour {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String business_neighbour_code = null; 
				String business_neighbour_sts = null;
				String verification_method_code = null;
				String neighbour_name = null;
				String neighbour_category_code = null; 
				String phone_no = null;
				String well_known_pros_debtor_code = null; 
				String often_talking_pros_debtor_code = null;
				String marriage_sts_pros_debtor_code = null;
				String business_loc_pros_debtor_code = null;
				String neg_info_pros_debtor_code = null;
				String is_active = "1";
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;		
				
				return new String[] {
						"migrasiDlosAppBusinessNeighbour",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_business_neighbour (dataId, business_id, business_neighbour_code, business_neighbour_sts, verification_method_code, neighbour_name, neighbour_category_code, phone_no, well_known_pros_debtor_code, often_talking_pros_debtor_code, marriage_sts_pros_debtor_code, business_loc_pros_debtor_code, neg_info_pros_debtor_code, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(											'%s',   '%s',        '%s',                    '%s',                   '%s',                     '%s',           '%s',                    '%s',     '%s',                        '%s',                           '%s',                          '%s',                          '%s',                      %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, business_neighbour_code, business_neighbour_sts, verification_method_code, neighbour_name, neighbour_category_code, phone_no, well_known_pros_debtor_code, often_talking_pros_debtor_code, marriage_sts_pros_debtor_code, business_loc_pros_debtor_code, neg_info_pros_debtor_code, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
}
