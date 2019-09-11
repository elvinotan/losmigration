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

public class DlosAppBusiness {
	
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `business_activity` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `business_detail` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `business_strategy_desc` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `project_plan_list` TEXT NULL;
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `key_person_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_business MODIFY COLUMN `source_of_info_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId"); 
				String business_code = null; 
				String business_name = null;
				String business_sts = null;
				String company_name = null;
				String ind_sec_code = mapper.getString("ind_sec_code");
				Lookup lind_sec_code = store.getLookupByDescription(Lookup.IndustrialSector, ind_sec_code);
				ind_sec_code = (lind_sec_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lind_sec_code.getKey();
				
				String business_type_code = mapper.getString("business_type_code");
				Lookup lbusiness_type_code = store.getLookupByDescription(Lookup.BusinessType, business_type_code);
				business_type_code = (lbusiness_type_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lbusiness_type_code.getKey();
				
				String economy_sector_code = null;
				String business_desc = mapper.getString("business_desc");
				String company_history = null;
				String company_risk = null;
				String stock_rotation = null;
				String annual_cycle = null;
				String account_receivable_policy = null; 
				String business_manager_admin = null;
				String competitor_analysis = null;
				String other_company_party = null;
				String business_scale_code = mapper.getString("business_scale_code");
				Lookup lbusiness_scale_code = store.getLookupByDescription(Lookup.BusinessScale, business_scale_code);
				business_scale_code = (lbusiness_scale_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lbusiness_scale_code.getKey();
				
				String business_main_founder_code = mapper.getString("business_main_founder_code");
				Lookup lbusiness_main_founder_code = store.getLookupByDescription(Lookup.BusinessFounderCorporate, business_main_founder_code);
				business_main_founder_code = (lbusiness_main_founder_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lbusiness_main_founder_code.getKey();
				
				String marketing_area_code = mapper.getString("marketing_area_code");
				Lookup lmarketing_area_code = store.getLookupByDescription(Lookup.MarketingArea, marketing_area_code);
				marketing_area_code = (lmarketing_area_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lmarketing_area_code.getKey();
				
				String market_share_cnt = mapper.getString("market_share_cnt");
				String employee_cnt = mapper.getString("employee_cnt");
				String operate_since_cnt = mapper.getString("operate_since_cnt");
				String business_experience_cnt = mapper.getString("business_experience_cnt");
				String registered_company_period_cnt = null;
				String source_of_info_code = mapper.getString("source_of_info_code");
				String key_person_code = mapper.getString("key_person_code");
				
				String business_strategy_code = mapper.getString("business_strategy_code");
				Lookup lbusiness_strategy_code = store.getLookupByDescription(Lookup.BusinessStrategy, business_strategy_code);
				business_strategy_code = (lbusiness_strategy_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lbusiness_strategy_code.getKey();
				
				String business_strategy_desc = StringTool.combine(mapper.getString("business_strategy_desc0"), mapper.getString("business_strategy_desc1"), mapper.getString("business_strategy_desc2"));
				String empty_store_last_6mo_code = mapper.getString("empty_store_last_6mo_code");
				
				String has_other_business = mapper.getString("has_other_business");
				Lookup lhas_other_business = store.getLookupByDescription(Lookup.YesNo, has_other_business);
				has_other_business = (lhas_other_business == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lhas_other_business.getKey();
				
				String other_business_type_code = mapper.getString("other_business_type_code");
				Lookup lother_business_type_code = store.getLookupByDescription(Lookup.BusinessType, other_business_type_code);
				other_business_type_code = (lother_business_type_code == null) ? mapper.logMapperProblem("migrasiDlosAppBusiness") : lother_business_type_code.getKey();
				
				String other_business_pct = mapper.getString("other_business_pct");
				if (!StringTool.isEmpty(other_business_pct)) {
					other_business_pct = NumberTool.format(Double.valueOf(other_business_pct) * 100);
				}
				
				String main_competitor = mapper.getString("main_competitor");
				String business_activity = StringTool.combine(mapper.getString("business_activity0"), mapper.getString("business_activity1"), mapper.getString("business_activity2"), mapper.getString("business_activity3"));
				String business_detail = StringTool.combine(mapper.getString("business_detail0"), mapper.getString("business_detail1"), mapper.getString("business_detail2"), mapper.getString("business_detail3"));
				String project_plan_list = StringTool.combine(mapper.getString("project_plan_list0"), mapper.getString("project_plan_list1"), mapper.getString("project_plan_list2"), mapper.getString("project_plan_list3"));
				String nik_match_code = null;
				String name_match_code = null;
				String income_monthly_omzet_amt = null; 
				String income_financial_rec_based_omzet_amt = null; 
				String income_last_3mo_amt = null;
				String income_last_2mo_amt = null; 
				String income_last_1mo_amt = null;
				String income_omzet_source_desc = null;
				String doc_financial_rec_based_omzet_amt = null; 
				String doc_last_3mo_amt = null;
				String doc_last_2mo_amt = null;
				String doc_last_1mo_amt = null;
				String doc_omzet_source_desc = null;
				String notes = StringTool.combine(mapper.getString("notes0"), mapper.getString("notes1"), mapper.getString("notes2"), mapper.getString("notes3"));
				String is_active = "1";
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppBusiness",
						
						String.format(
								"INSERT INTO dlos_core.dlos_app_business (dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(								  '%s',   '%s',          '%s',          '%s',         '%s',         '%s',         '%s',               '%s',                '%s',          '%s',            '%s',         '%s',           '%s',         '%s',                      '%s',                   '%s',                '%s',                '%s',                '%s',                       '%s',                '%s',             '%s',         '%s',              '%s',                    '%s',                          '%s',                '%s',            '%s',                   '%s',                   '%s',                      '%s',               '%s',                     '%s',               '%s',            '%s',              '%s',            '%s',              '%s',           '%s',            '%s',                     '%s',                                 '%s',                '%s',                '%s',                '%s',                     '%s',                              '%s',             '%s',             '%s',             '%s',                  '%s',  %s,        '%s',          '%s',        '%s',         '%s'); ", 
								dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by)
				};
				
			}
		};
		
		if (LobType.isSmes(lobType)) {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("business_activity0", "A53")
					.xls("business_activity1", "A54")
					.xls("business_activity2", "A55")
					.xls("business_activity3", "A56")
					.xls("business_desc", "G32")
					.xls("business_detail0", "A58")
					.xls("business_detail1", "A59")
					.xls("business_detail2", "A60")
					.xls("business_detail3", "A61")
					.xls("business_experience_cnt", "D33")
					.xls("business_main_founder_code", "F38")
					.xls("business_scale_code", "D39")
					.xls("business_strategy_code", "B113")
					.xls("business_strategy_desc0", "A114")
					.xls("business_strategy_desc1", "A115")
					.xls("business_strategy_desc2", "A116")
					.xls("business_type_code", "D32")
					.xls("employee_cnt", "D35")
					.xls("empty_store_last_6mo_code", "F42")
					.xls("has_other_business", "F43")
					.xls("ind_sec_code", "D31")
					.xls("key_person_code", "J34")
					.xls("main_competitor", "D51")
					.xls("market_share_cnt", "D41")
					.xls("marketing_area_code", "D40")
					.xls("notes0", "A330")
					.xls("notes1", "A331")
					.xls("notes2", "A332")
					.xls("notes3", "A333")
					.xls("operate_since_cnt", "D30")
					.xls("other_business_pct", "H46")
					.xls("other_business_type_code", "F46")
					.xls("project_plan_list0", "A63")
					.xls("project_plan_list1", "A64")
					.xls("project_plan_list2", "A65")
					.xls("project_plan_list3", "A66")
					.xls("source_of_info_code", "J33")
					.pk("business_id"));
		}
		
		if (LobType.isSmel(lobType)) {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("business_activity0", "A53")
					.xls("business_activity1", "A54")
					.xls("business_activity2", "A55")
					.xls("business_activity3", "A56")
					.xls("business_desc", "G32")
					.xls("business_detail0", "A58")
					.xls("business_detail1", "A59")
					.xls("business_detail2", "A60")
					.xls("business_detail3", "A61")
					.xls("business_experience_cnt", "D33")
					.xls("business_main_founder_code", "F38")
					.xls("business_scale_code", "D39")
					.xls("business_strategy_code", "B113")
					.xls("business_strategy_desc0", "A114")
					.xls("business_strategy_desc1", "A115")
					.xls("business_strategy_desc2", "A116")
					.xls("business_type_code", "D32")
					.xls("employee_cnt", "D35")
					.xls("empty_store_last_6mo_code", "F42")
					.xls("has_other_business", "F43")
					.xls("ind_sec_code", "D31")
					.xls("key_person_code", "J34")
					.xls("main_competitor", "D51")
					.xls("market_share_cnt", "D41")
					.xls("marketing_area_code", "D40")
					.xls("notes0", "A328")
					.xls("notes1", "A329")
					.xls("notes2", "A330")
					.xls("notes3", "A331")
					.xls("operate_since_cnt", "D30")
					.xls("other_business_pct", "H46")
					.xls("other_business_type_code", "F46")
					.xls("project_plan_list0", "A63")
					.xls("project_plan_list1", "A64")
					.xls("project_plan_list2", "A65")
					.xls("project_plan_list3", "A66")
					.xls("source_of_info_code", "J33")
					.pk("business_id"));
		}
	}
}
