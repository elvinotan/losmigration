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
import com.btpn.migration.los.tool.NumberTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppBuyPayment {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {	
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String buyer_name = mapper.getString("buyer_name");
				if (buyer_name == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String buyer_payment_code = null; 
				String buyer_payment_desc = null; 
				String buyer_payment_sts = null; 
				
				String cash_pymt_pct = mapper.getString("cash_pymt_pct");
				
				String credit_pymt_pct = mapper.getString("credit_pymt_pct");
				String pymt_dur_day_cnt = mapper.getString("pymt_dur_day_cnt"); 
				
				String timely_pymt_code = mapper.getString("timely_pymt_code"); 
				Lookup ltimely_pymt_code = store.getLookupByDescription(Lookup.TimelyPymt, timely_pymt_code);
				timely_pymt_code = (ltimely_pymt_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyPayment") : ltimely_pymt_code.getKey();
				
				String is_cont_relation_code = mapper.getString("is_cont_relation_code");
				Lookup lis_cont_relation_code = store.getLookupByDescription(Lookup.YesNo, is_cont_relation_code);
				is_cont_relation_code = (lis_cont_relation_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyPayment") : lis_cont_relation_code.getKey();
				
				String is_neg_info_code = mapper.getString("is_neg_info_code");
				Lookup lis_neg_info_code = store.getLookupByDescription(Lookup.YesNo, is_neg_info_code);
				is_neg_info_code = (lis_neg_info_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyPayment") : lis_neg_info_code.getKey();
				
				String neg_info_desc = StringTool.combine(mapper.getString("neg_info_desc0"), mapper.getString("neg_info_desc1"));
				
				String top3_sup_sales_pct = mapper.getString("top3_sup_sales_pct"); 
				if (!StringTool.isEmpty(top3_sup_sales_pct)) {
					top3_sup_sales_pct = NumberTool.format(Double.valueOf(top3_sup_sales_pct) * 100);
				}
				
				String main_buy_dependency_code = mapper.getString("main_buy_dependency_code");
				Lookup lmain_buy_dependency_code = store.getLookupByDescription(Lookup.BuyDependency, main_buy_dependency_code);
				main_buy_dependency_code = (lmain_buy_dependency_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyPayment") : lmain_buy_dependency_code.getKey();				
				
				String additional_info = mapper.getString("additional_info");
				Lookup ladditional_info = store.getLookupByDescription(Lookup.AdditionalInfo, additional_info);
				additional_info = (ladditional_info == null) ? mapper.logMapperProblem("migrasiDlosAppBuyPayment") : ladditional_info.getKey();
				
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppBuyPayment",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_buy_payments (dataId, business_id, buyer_payment_code, buyer_payment_desc, buyer_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, top3_sup_sales_pct, main_buy_dependency_code, additional_info, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(									  '%s',   '%s',        '%s',               '%s',               '%s',              '%s',          '%s',            '%s',             '%s',             '%s',                  '%s',             '%s',          '%s',               '%s',                     '%s',            %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, buyer_payment_code, buyer_payment_desc, buyer_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, top3_sup_sales_pct, main_buy_dependency_code, additional_info, is_active, modified_date, modified_by, created_date, created_by)						
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 5; i++) {				
				int ctr = 207 + i;				
				int cashPymtPct = 29 + (26*i);
				int isContRelationCode = 32 + (26*i);
				int isNegInfoCode = 33 + (26*i);
				int negInfoDesc0 = 34 + (26*i);
				int negInfoDesc1 = 35 + (26*i); 
				int timelyPymtCode = 31 + (26*i); 
				
				specRows.add(SpecRow.get(insert)
						.setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("buyer_name", "A"+ctr)
						.xls("pymt_dur_day_cnt", "L"+ctr)
						
						.setSheet(Sheet.BuyerChecking)
						.xls("additional_info", "C147")
						.xls("cash_pymt_pct", "C"+cashPymtPct)
						.xls("credit_pymt_pct", "E"+cashPymtPct)
						.xls("is_cont_relation_code", "C"+isContRelationCode)
						.xls("is_neg_info_code", "C"+isNegInfoCode)
						.xls("main_buy_dependency_code", "C146")
						.xls("neg_info_desc0", "E"+negInfoDesc0)
						.xls("neg_info_desc1", "C"+negInfoDesc1)
						.xls("timely_pymt_code", "C"+timelyPymtCode)
						.xls("top3_sup_sales_pct", "C143")						
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 5; i++) {				
				int ctr = 205 + i;
				int cashPymtPct = 36 + (26*i);
				int isContRelationCode = 39 + (26*i);
				int isNegInfoCode = 40 + (26*i);
				int negInfoDesc0 = 41 + (26*i);
				int negInfoDesc1 = 42 + (26*i);
				int timelyPymtCode = 38 + (26*i); 
				
				specRows.add(SpecRow.get(insert)
						.setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("buyer_name", "A"+ctr)
						.xls("pymt_dur_day_cnt", "L"+ctr)
						
						.setSheet(Sheet.BuyerChecking)
						.xls("additional_info", "C154")
						.xls("cash_pymt_pct", "C"+cashPymtPct)
						.xls("credit_pymt_pct", "E"+cashPymtPct)
						.xls("is_cont_relation_code", "C"+isContRelationCode)
						.xls("is_neg_info_code", "C"+isNegInfoCode)
						.xls("main_buy_dependency_code", "C13")
						.xls("neg_info_desc0", "E"+negInfoDesc0)
						.xls("neg_info_desc1", "C"+negInfoDesc1)
						.xls("timely_pymt_code", "C"+timelyPymtCode)
						.xls("top3_sup_sales_pct", "C11") 
						);
			}
		}
	}
}
