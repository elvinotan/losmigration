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

public class DlosAppSupPayments {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String supplier_name = mapper.getString("supplier_name"); 
				if (supplier_name == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String supplier_payment_code = null; 
				String supplier_payment_sts = null; 
				String cash_pymt_pct = mapper.getString("cash_pymt_pct"); 
				String credit_pymt_pct = mapper.getString("credit_pymt_pct"); 
				String pymt_dur_day_cnt = mapper.getString("pymt_dur_day_cnt"); 
				
				String timely_pymt_code = mapper.getString("timely_pymt_code");
				Lookup ltimely_pymt_code = store.getLookupByDescription(Lookup.TimelyPymt, timely_pymt_code);
				timely_pymt_code = (ltimely_pymt_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : ltimely_pymt_code.getKey();
				
				String is_cont_relation_code = mapper.getString("is_cont_relation_code");
				Lookup lis_cont_relation_code = store.getLookupByDescription(Lookup.YesNo, is_cont_relation_code);
				is_cont_relation_code = (lis_cont_relation_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : lis_cont_relation_code.getKey();
				
				String is_neg_info_code = mapper.getString("is_neg_info_code");
				Lookup lis_neg_info_code = store.getLookupByDescription(Lookup.YesNo, is_neg_info_code);
				is_neg_info_code = (lis_neg_info_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : lis_neg_info_code.getKey();
				
				
				String neg_info_desc = StringTool.combine(mapper.getString("neg_info_desc0"), mapper.getString("neg_info_desc1"));
				
				String pymt_freq_majority_cnt = mapper.getString("pymt_freq_majority_cnt");
				Lookup lpymt_freq_majority_cnt = store.getLookupByDescription(Lookup.PaymentFreq, pymt_freq_majority_cnt);
				pymt_freq_majority_cnt = (lpymt_freq_majority_cnt == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : lpymt_freq_majority_cnt.getKey();
						
				String top3_sup_sales_pct = mapper.getString("top3_sup_sales_pct"); 
				if (!StringTool.isEmpty(top3_sup_sales_pct)) {
					top3_sup_sales_pct = NumberTool.format(Double.valueOf(top3_sup_sales_pct) * 100);
				}
				
				String main_sup_dependency_code = mapper.getString("main_sup_dependency_code");
				Lookup lmain_sup_dependency_code = store.getLookupByDescription(Lookup.SupDependency, main_sup_dependency_code);
				main_sup_dependency_code = (lmain_sup_dependency_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : lmain_sup_dependency_code.getKey(); 
				
				String is_single_sup_code = null; 
				
				String additional_info = mapper.getString("additional_info");
				Lookup ladditional_info = store.getLookupByDescription(Lookup.AdditionalInfo, additional_info);
				additional_info = (ladditional_info == null) ? mapper.logMapperProblem("migrasiDlosAppSupPayments") : ladditional_info.getKey();				
				
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppSupPayments",
				
						String.format(  
								"INSERT INTO dlos_core.dlos_app_sup_payments (dataId, business_id, supplier_payment_code, supplier_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, pymt_freq_majority_cnt, top3_sup_sales_pct, main_sup_dependency_code, is_single_sup_code, additional_info, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                      '%s',   '%s',        '%s',                  '%s',                 '%s',          '%s',            '%s',             '%s',             '%s',                  '%s',             '%s',          '%s',                   '%s',               '%s',                     '%s',               '%s',            %s,      '%s',          '%s',        '%s',         '%s');",
								dataId, business_id, supplier_payment_code, supplier_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, pymt_freq_majority_cnt, top3_sup_sales_pct, main_sup_dependency_code, is_single_sup_code, additional_info, is_active, modified_date, modified_by, created_date, created_by)
				};
				
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 5; i++) {
				int ctr = 199+i;
				int ctrCashPaymentPct = 28 + (26*i);
				int ctrCreditPaymentPct = 28 + (26*i);
				int ctrIsContRelationCode = 31 + (26*i);
				int ctrIsNegInfoCode = 32 + (26*i);
				int ctrNegInfoDesc0 = 33 + (26*i);
				int ctrNegInfoDesc1 = 34 + (26*i);
				int timelyPymtCode = 30 + (26*i);
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("supplier_name", "A"+ctr)
						.xls("pymt_dur_day_cnt", "L"+ctr)
						
						.setSheet(Sheet.SupplierChecking)
						.xls("additional_info", "C146")
						.xls("cash_pymt_pct", "C"+ctrCashPaymentPct)
						.xls("credit_pymt_pct", "E"+ctrCreditPaymentPct)
						.xls("is_cont_relation_code", "C"+ctrIsContRelationCode)
						.xls("is_neg_info_code", "C"+ctrIsNegInfoCode)
						.xls("main_sup_dependency_code", "C145")
						.xls("neg_info_desc0", "E"+ctrNegInfoDesc0)
						.xls("neg_info_desc1", "C"+ctrNegInfoDesc1)
						.xls("pymt_freq_majority_cnt", "C142")
						.xls("timely_pymt_code", "C"+timelyPymtCode)
						.xls("top3_sup_sales_pct", "C143")
						);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 5; i++) {
				int ctr = 197+i;
				int ctrCashPaymentPct = 33 + (26*i);
				int ctrCreditPaymentPct = 33 + (26*i);
				int ctrIsContRelationCode = 36 + (26*i);
				int ctrIsNegInfoCode = 37 + (26*i);
				int ctrNegInfoDesc0 = 38 + (26*i);
				int ctrNegInfoDesc1 = 39 + (26*i);
				int timelyPymtCode = 35 + (26*i);
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("supplier_name", "A"+ctr)
						.xls("pymt_dur_day_cnt", "L"+ctr)						
						
						.setSheet(Sheet.SupplierChecking)
						.xls("additional_info", "C151")
						.xls("cash_pymt_pct", "C"+ctrCashPaymentPct)
						.xls("credit_pymt_pct", "E"+ctrCreditPaymentPct)
						.xls("is_cont_relation_code", "C"+ctrIsContRelationCode)
						.xls("is_neg_info_code", "C"+ctrIsNegInfoCode)
						.xls("main_sup_dependency_code", "C11")
						.xls("neg_info_desc0", "E"+ctrNegInfoDesc0)
						.xls("neg_info_desc1", "C"+ctrNegInfoDesc1)
						.xls("pymt_freq_majority_cnt", "C10")
						.xls("timely_pymt_code", "C"+timelyPymtCode)		
						.xls("top3_sup_sales_pct", "C12")
						);
			}
		}
	}
}
