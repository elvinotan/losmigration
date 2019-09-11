package com.btpn.migration.los.mapping.tujuandanfasilitas;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppFacilityOth {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {			
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId"); 
				String fac_id = store.getString("fac_id");
				String fac_oth_code = null;
				String fac_oth_desc = null;
				String fac_oth_sts = null;
				String bank_name = mapper.getString("bank_name");
				if (bank_name == null)  return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String fac_name = mapper.getString("fac_name");
				
				String fac_type_code = mapper.getString("fac_type_code");
				Lookup lfac_type_code = store.getLookupByDescription(Lookup.FacilityType, fac_type_code);
				fac_type_code = (lfac_type_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lfac_type_code.getKey();
				
				String is_takeover = mapper.getString("is_takeover");
				Lookup lis_takeover = store.getLookupByDescription(Lookup.YesNo, is_takeover);
				is_takeover = (lis_takeover == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lis_takeover.getKey();
				
				String principal_amt = mapper.getString("principal_amt");
				String os_amt = mapper.getString("os_amt");
				
				String start_date = null;
				if (LobType.isSmes(lobType)) {
					// Cell Type bukan date jadi untuk sementara di set null saja, daripada data yang masuk rusak
				}
				
				if (LobType.isSmel(lobType)) {
					start_date = DateTool.getYMD(mapper.getString("start_date"));
					if (start_date != null && "-".equals(start_date.trim())) start_date = null;
				}
				
				String customer_name = mapper.getString("customer_name");
				
				String coll_type = mapper.getString("coll_type");
				Lookup lcoll_type = store.getLookupByDescription(Lookup.CollateralProperty, coll_type);
				coll_type = (lcoll_type == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lcoll_type.getKey();				
				
				String coll_amt = mapper.getString("coll_amt");
				
				String bi_last_3mos_code = mapper.getString("bi_last_3mos_code");
				Lookup lbi_last_3mos_code = store.getLookupByDescription(Lookup.BIChecking, bi_last_3mos_code);
				bi_last_3mos_code = (lbi_last_3mos_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lbi_last_3mos_code.getKey();
				
				String dpd_last_3_mos = mapper.getString("dpd_last_3_mos");
				String is_active = "1";
				String tenor = mapper.getString("tenor");
				
				String bi_collect_last_2mos = mapper.getString("bi_collect_last_2mos");
				Lookup lbi_collect_last_2mos = store.getLookupByDescription(Lookup.BIChecking, bi_collect_last_2mos);
				bi_collect_last_2mos = (lbi_collect_last_2mos == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lbi_collect_last_2mos.getKey();
				
				String bi_collect_last_1mos = mapper.getString("bi_collect_last_1mos");
				Lookup lbi_collect_last_1mos = store.getLookupByDescription(Lookup.BIChecking, bi_collect_last_1mos);
				bi_collect_last_1mos = (lbi_collect_last_1mos == null) ? mapper.logMapperProblem("migrasiDlosAppFacilityOth") : lbi_collect_last_1mos.getKey();
				
				String modified_date = null;
				String modified_by = mapper.getString("appId");;
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppFacilityOth",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_facility_oth (dataId, fac_id, fac_oth_code, fac_oth_desc, fac_oth_sts, bank_name, fac_name, fac_type_code, is_takeover, principal_amt, os_amt, start_date, customer_name, coll_type, coll_amt, bi_last_3mos_code, dpd_last_3_mos, is_active, modified_date, modified_by, created_date, created_by, tenor, bi_collect_last_2mos, bi_collect_last_1mos) " + 
								"VALUES(                                      '%s',   '%s',   '%s',         '%s',         '%s',        '%s',      '%s',     '%s',          '%s',        '%s',          '%s',   '%s',       '%s',          '%s',      '%s',     '%s',              '%s',           %s,        '%s',          '%s',        '%s',         '%s',       '%s',  '%s',                 '%s'); ", 
								dataId, fac_id, fac_oth_code, fac_oth_desc, fac_oth_sts, bank_name, fac_name, fac_type_code, is_takeover, principal_amt, os_amt, start_date, customer_name, coll_type, coll_amt, bi_last_3mos_code, dpd_last_3_mos, is_active, modified_date, modified_by, created_date, created_by, tenor, bi_collect_last_2mos, bi_collect_last_1mos)
				};
			}
		};
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 15; i++) {
				int ctr = 154 + i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("bank_name", "A"+ctr)
						.xls("bi_collect_last_1mos", "O"+ctr)
						.xls("bi_collect_last_2mos", "N"+ctr)
						.xls("bi_last_3mos_code", "M"+ctr)
						.xls("coll_amt", "K"+ctr)
						.xls("coll_type", "J"+ctr)
						.xls("customer_name", "I"+ctr)
						.xls("dpd_last_3_mos", "P"+ctr)
						.xls("fac_name", "C"+ctr)
						.xls("fac_type_code", "D"+ctr)
						.xls("is_takeover", "E"+ctr)
						.xls("os_amt", "G"+ctr)
						.xls("principal_amt", "F"+ctr)
						.xls("start_date", "H"+ctr)
						.xls("tenor", "L"+ctr)
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 15; i++) {
				int ctr = 152 + i;
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("bank_name", "A"+ctr)
						.xls("bi_collect_last_1mos", "O"+ctr)
						.xls("bi_collect_last_2mos", "N"+ctr)
						.xls("bi_last_3mos_code", "M"+ctr)
						.xls("coll_amt", "K"+ctr)
						.xls("coll_type", "J"+ctr)
						.xls("customer_name", "I"+ctr)
						.xls("dpd_last_3_mos", "P"+ctr)
						.xls("fac_name", "C"+ctr)
						.xls("fac_type_code", "D"+ctr)
						.xls("is_takeover", "E"+ctr)
						.xls("os_amt", "G"+ctr)
						.xls("principal_amt", "F"+ctr)
						.xls("start_date", "H"+ctr)		
						.xls("tenor", "L"+ctr)
						);
			}
		}
	}
}
