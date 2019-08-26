package com.btpn.migration.los.mapping;

import java.util.ArrayList;
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

public class DataUsaha implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// Perubahan sql
	// dlos_app_business.business_activity = Text
	// dlos_app_business.business_detail = Text
	// dlos_app_business.business_strategy_desc = Text
	// dlos_app_business.key_person_code = Varchar (255)
	// dlos_app_business.source_of_info_code = Varchar (255)
	// dlos_app_business.project_plan_list = Text
	
	// Mencakup
	//	select * from dlos_app_business
	//	select * from dlos_app_business_oth
	//	select * from dlos_app_business_neighbour
	//	select * from dlos_app_sup_checking
	//	select * from dlos_app_sup_payments
	//	select * from dlos_app_buy_checking
	//	select * from dlos_app_buy_payments
	//	select * from dlos_app_covenant_header
	//	select * from dlos_app_precedent_header
	//	select * from dlos_app_drawdown_condition_header
	//	select * from dlos_app_other_condition_ext_header
	//	select * from dlos_app_other_condition_int_header
	

	@Override
	public String[] clearTable() {
		return new String[] { 
				String.format("delete from dlos_app_business where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_business_oth where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_business_neighbour where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_sup_checking where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_sup_payments where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_buy_checking where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_buy_payments where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_covenant_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_precedent_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_drawdown_condition_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_other_condition_ext_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_other_condition_int_header where created_by ='%s';", MIGRATION),
			};
	}
	
	@Override
	public void initMapping(String lobType) {
		migrasiDlosAppBusiness(lobType);
		migrasiDlosAppBusinessOth(lobType);
		migrasiDlosAppBusinessNeighbour(lobType);
		migrasiDlosAppSupChecking(lobType);
		migrasiDlosAppSupPayments(lobType);
		migrasiDlosAppBuyChecking(lobType);
		migrasiDlosAppBuyPayment(lobType);
		migrasiDlosAppCovenantHeader(lobType);
		migrasiDlosAppPrecedentHeader(lobType);
		migrasiDlosAppDrawdownConditionHeader(lobType);
		migrasiDlosAppOtherConditionExtHeader(lobType);
		migrasiDlosAppOtherConditionIntHeader(lobType);
	}
	
	private void migrasiDlosAppBusiness(String lobType) {
		IActions insertDlosAppBusiness = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId"); 
				String business_code = null; 
				String business_name = null;
				String business_sts = null;
				String company_name = null;
				String ind_sec_code = mapper.getString("ind_sec_code");
				Lookup lind_sec_code = store.getLookupByDescription(Lookup.IndustrialSector, ind_sec_code);
				ind_sec_code = (lind_sec_code == null) ? null : lind_sec_code.getKey();
				
				String business_type_code = mapper.getString("business_type_code");
				Lookup lbusiness_type_code = store.getLookupByDescription(Lookup.BusinessType, business_type_code);
				business_type_code = (lbusiness_type_code == null) ? null : lbusiness_type_code.getKey();
				
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
				business_scale_code = (lbusiness_scale_code == null) ? null : lbusiness_scale_code.getKey();
				
				String business_main_founder_code = mapper.getString("business_main_founder_code");
				Lookup lbusiness_main_founder_code = store.getLookupByDescription(Lookup.BusinessFounderCorporate, business_main_founder_code);
				business_main_founder_code = (lbusiness_main_founder_code == null) ? null : lbusiness_main_founder_code.getKey();
				
				String marketing_area_code = mapper.getString("marketing_area_code");
				Lookup lmarketing_area_code = store.getLookupByDescription(Lookup.MarketingArea, marketing_area_code);
				marketing_area_code = (lmarketing_area_code == null) ? null : lmarketing_area_code.getKey();
				
				String market_share_cnt = mapper.getString("market_share_cnt");
				String employee_cnt = mapper.getString("employee_cnt");
				String operate_since_cnt = mapper.getString("operate_since_cnt");
				String business_experience_cnt = mapper.getString("business_experience_cnt");
				String registered_company_period_cnt = null;
				String source_of_info_code = mapper.getString("source_of_info_code");
				String key_person_code = mapper.getString("key_person_code");
				
				String business_strategy_code = mapper.getString("business_strategy_code");
				Lookup lbusiness_strategy_code = store.getLookupByDescription(Lookup.BusinessStrategy, business_strategy_code);
				business_strategy_code = (lbusiness_strategy_code == null) ? null : lbusiness_strategy_code.getKey();
				
				String business_strategy_desc = StringTool.combine(mapper.getString("business_strategy_desc0"), mapper.getString("business_strategy_desc1"), mapper.getString("business_strategy_desc2"));
				String empty_store_last_6mo_code = mapper.getString("empty_store_last_6mo_code");
				
				String has_other_business = mapper.getString("has_other_business");
				Lookup lhas_other_business = store.getLookupByDescription(Lookup.YesNo, has_other_business);
				has_other_business = (lhas_other_business == null) ? null : lhas_other_business.getKey();
				
				String other_business_type_code = mapper.getString("other_business_type_code");
				Lookup lother_business_type_code = store.getLookupByDescription(Lookup.BusinessType, other_business_type_code);
				other_business_type_code = (lother_business_type_code == null) ? null : lother_business_type_code.getKey();
				
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
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_business (dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(								  '%s',   '%s',          '%s',          '%s',         '%s',         '%s',         '%s',               '%s',                '%s',          '%s',            '%s',         '%s',           '%s',         '%s',                      '%s',                   '%s',                '%s',                '%s',                '%s',                       '%s',                '%s',             '%s',         '%s',              '%s',                    '%s',                          '%s',                '%s',            '%s',                   '%s',                   '%s',                      '%s',               '%s',                     '%s',               '%s',            '%s',              '%s',            '%s',              '%s',           '%s',            '%s',                     '%s',                                 '%s',                '%s',                '%s',                '%s',                     '%s',                              '%s',             '%s',             '%s',             '%s',                  '%s',  %s,        '%s',          '%s',        '%s',         '%s'); ", 
						dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by);
			}
		};
		
		if (LobType.isSmes(lobType)) {
			specRows.add(SpecRow.get(insertDlosAppBusiness).setSheet(Sheet.InformasiDebitur)
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
			specRows.add(SpecRow.get(insertDlosAppBusiness).setSheet(Sheet.InformasiDebitur)
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
	
	private void migrasiDlosAppBusinessOth(String lobType) {
		IActions insertDlosAppBusinessOth = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId");
				String business_id = null; 
				String business_oth_code = null;
				String business_oth_sts = null;
				String other_business_type_code = null; 
				String other_business_pct = null;
				String company_period_cnt = null;
				String is_active = "1";
				String modified_date = null;
				String modified_by = null;
				String created_date = null;
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_business_oth (dataId, business_id, business_oth_code, business_oth_sts, other_business_type_code, other_business_pct, company_period_cnt, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                      '%s',   '%s',        '%s',              '%s',             '%s',                     '%s',               '%s',               %s,        '%s',          '%s',        '%s',         '%s'); ", 
						dataId, business_id, business_oth_code, business_oth_sts, other_business_type_code, other_business_pct, company_period_cnt, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBusinessOth).setSheet(Sheet.InformasiDebitur));
	}
	
	private void migrasiDlosAppBusinessNeighbour(String lobType) {
		IActions insertDlosAppBusinessNeighbour = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
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
				String modified_by = null;
				String created_date = null;
				String created_by = MIGRATION;				
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_business_neighbour (dataId, business_id, business_neighbour_code, business_neighbour_sts, verification_method_code, neighbour_name, neighbour_category_code, phone_no, well_known_pros_debtor_code, often_talking_pros_debtor_code, marriage_sts_pros_debtor_code, business_loc_pros_debtor_code, neg_info_pros_debtor_code, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(											'%s',   '%s',        '%s',                    '%s',                   '%s',                     '%s',           '%s',                    '%s',     '%s',                        '%s',                           '%s',                          '%s',                          '%s',                      %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, business_neighbour_code, business_neighbour_sts, verification_method_code, neighbour_name, neighbour_category_code, phone_no, well_known_pros_debtor_code, often_talking_pros_debtor_code, marriage_sts_pros_debtor_code, business_loc_pros_debtor_code, neg_info_pros_debtor_code, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBusinessNeighbour).setSheet(Sheet.InformasiDebitur));
	}
	
	private void migrasiDlosAppSupChecking(String lobType) {
		IActions insertDlosAppSupChecking = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String supplier_checking_code = null; 
				String supplier_checking_sts = null; 
				String check_date = null; 
				String verification_method = null; 
				String supplier_name = null; 
				String business_type = null; 
				String info_provider_name = null; 
				String info_provider_title = null; 
				String supplier_address = null; 
				String supplier_phone_no = null; 
				String is_owner_pros_debtor = null; 
				String business_relation_yr_cnt = null; 
				String goods_services_sold = null; 
				String is_supplier_verified_code = null; 
				String avg_mo_sales_amt = null; 
				String sales_pct = null; 
				String sales_freq_code = null; 
				String payment_freq_code = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_sup_checking (dataId, business_id, supplier_checking_code, supplier_checking_sts, check_date, verification_method, supplier_name, business_type, info_provider_name, info_provider_title, supplier_address, supplier_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, is_supplier_verified_code, avg_mo_sales_amt, sales_pct, sales_freq_code, payment_freq_code, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                      '%s',   '%s',        '%s',                   '%s',                  '%s',       '%s',                '%s',          '%s',          '%s',               '%s',                '%s',             '%s',              '%s',                 '%s',                     '%s',                '%s',                      '%s',             '%s',      '%s',            '%s',              %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, supplier_checking_code, supplier_checking_sts, check_date, verification_method, supplier_name, business_type, info_provider_name, info_provider_title, supplier_address, supplier_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, is_supplier_verified_code, avg_mo_sales_amt, sales_pct, sales_freq_code, payment_freq_code, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppSupChecking).setSheet(Sheet.InformasiDebitur));
	} 
	
	private void migrasiDlosAppSupPayments(String lobType) {
		IActions insertDlosAppSupPayments = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String supplier_payment_code = null; 
				String supplier_payment_sts = null; 
				String cash_pymt_pct = null; 
				String credit_pymt_pct = null; 
				String pymt_dur_day_cnt = null; 
				String timely_pymt_code = null; 
				String is_cont_relation_code = null; 
				String is_neg_info_code = null; 
				String neg_info_desc = null; 
				String pymt_freq_majority_cnt = null; 
				String top3_sup_sales_pct = null; 
				String main_sup_dependency_code = null; 
				String is_single_sup_code = null; 
				String additional_info = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format(  
						"INSERT INTO dlos_core.dlos_app_sup_payments (dataId, business_id, supplier_payment_code, supplier_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, pymt_freq_majority_cnt, top3_sup_sales_pct, main_sup_dependency_code, is_single_sup_code, additional_info, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                      '%s',   '%s',        '%s',                  '%s',                 '%s',          '%s',            '%s',             '%s',             '%s',                  '%s',             '%s',          '%s',                   '%s',               '%s',                     '%s',               '%s',            %s,      '%s',          '%s',        '%s',         '%s');",
						dataId, business_id, supplier_payment_code, supplier_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, pymt_freq_majority_cnt, top3_sup_sales_pct, main_sup_dependency_code, is_single_sup_code, additional_info, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppSupPayments).setSheet(Sheet.InformasiDebitur));
	}
	
	private void migrasiDlosAppBuyChecking(String lobType) {
		IActions insertDlosAppBuyChecking = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String buyer_checking_code = null; 
				String buyer_checking_desc = null; 
				String buyer_checking_sts = null; 
				String check_date = null; 
				String verification_method = null; 
				String buyer_name = null; 
				String buyer_type_code = null; 
				String business_type_code = null; 
				String info_provider_name = null; 
				String info_provider_title = null; 
				String buyer_address = null; 
				String buyer_phone_no = null; 
				String is_owner_pros_debtor = null; 
				String business_relation_yr_cnt = null; 
				String goods_services_sold = null; 
				String goods_services_quality_code = null; 
				String is_buyer_verified_code = null; 
				String avg_mo_purchase_amt = null; 
				String purch_pct = null; 
				String goods_services_purch_freq_cnt = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_buy_checking (dataId, business_id, buyer_checking_code, buyer_checking_desc, buyer_checking_sts, check_date, verification_method, buyer_name, buyer_type_code, business_type_code, info_provider_name, info_provider_title, buyer_address, buyer_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, goods_services_quality_code, is_buyer_verified_code, avg_mo_purchase_amt, purch_pct, goods_services_purch_freq_cnt, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                      '%s',   '%s',        '%s',                '%s',                '%s',               '%s',       '%s',                '%s',       '%s',            '%s',               '%s',               '%s',                '%s',          '%s',           '%s',                 '%s',                     '%s',                '%s',                        '%s',                   '%s',                '%s',      '%s',                          %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, buyer_checking_code, buyer_checking_desc, buyer_checking_sts, check_date, verification_method, buyer_name, buyer_type_code, business_type_code, info_provider_name, info_provider_title, buyer_address, buyer_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, goods_services_quality_code, is_buyer_verified_code, avg_mo_purchase_amt, purch_pct, goods_services_purch_freq_cnt, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBuyChecking).setSheet(Sheet.InformasiDebitur));		
	}
	
	private void migrasiDlosAppBuyPayment(String lobType) {		
		IActions insertDlosAppBuyPayment = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId");
				String business_id = null; 
				String buyer_payment_code = null; 
				String buyer_payment_desc = null; 
				String buyer_payment_sts = null; 
				String cash_pymt_pct = null; 
				String credit_pymt_pct = null; 
				String pymt_dur_day_cnt = null; 
				String timely_pymt_code = null; 
				String is_cont_relation_code = null; 
				String is_neg_info_code = null; 
				String neg_info_desc = null; 
				String top3_sup_sales_pct = null; 
				String main_buy_dependency_code = null;
				String additional_info = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_buy_payments (dataId, business_id, buyer_payment_code, buyer_payment_desc, buyer_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, top3_sup_sales_pct, main_buy_dependency_code, additional_info, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(									  '%s',   '%s',        '%s',               '%s',               '%s',              '%s',          '%s',            '%s',             '%s',             '%s',                  '%s',             '%s',          '%s',               '%s',                     '%s',            %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, buyer_payment_code, buyer_payment_desc, buyer_payment_sts, cash_pymt_pct, credit_pymt_pct, pymt_dur_day_cnt, timely_pymt_code, is_cont_relation_code, is_neg_info_code, neg_info_desc, top3_sup_sales_pct, main_buy_dependency_code, additional_info, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBuyPayment).setSheet(Sheet.InformasiDebitur));		
	}
	
	private void migrasiDlosAppCovenantHeader(String lobType) {
		IActions insertDlosAppCovenantHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String cov_header_code = null; 
				String cov_header_sts = null; 
				String cov_header_detail_code = null; 
				String cov_header_monitored_by_code = null; 
				String cov_header_freq_code = null; 
				String cov_header_existing_condition_code = null; 
				String cov_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_covenant_header (dataId, business_id, cov_header_code, cov_header_sts, cov_header_detail_code, cov_header_monitored_by_code, cov_header_freq_code, cov_header_existing_condition_code, cov_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                         '%s',   '%s',        '%s',            '%s',           '%s',                   '%s',                         '%s',                 '%s',                               '%s',            %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, cov_header_code, cov_header_sts, cov_header_detail_code, cov_header_monitored_by_code, cov_header_freq_code, cov_header_existing_condition_code, cov_header_desc, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppCovenantHeader).setSheet(Sheet.InformasiDebitur));
	}
	
	private void migrasiDlosAppPrecedentHeader(String lobType) {
		IActions insertDlosAppPrecedentHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null;
				String precedent_header_code = null; 
				String precedent_header_sts = null; 
				String precedent_header_detail_code = null; 
				String precedent_header_monitored_by_code = null; 
				String precedent_header_existing_condition_code = null; 
				String precedent_header_desc = null; String is_active = null; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_precedent_header(dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by) "+ 
						"VALUES(                                         '%s',   '%s',        '%s',                  '%s',                 '%s',                         '%s',                               '%s',                                     '%s',                  %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppPrecedentHeader).setSheet(Sheet.InformasiDebitur));	
	}
	
	private void migrasiDlosAppDrawdownConditionHeader(String lobType) {
		IActions insertDlosAppDrawdownConditionHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String drawdown_header_code = null; 
				String drawdown_header_sts = null; 
				String drawdown_header_detail_code = null; 
				String drawdown_header_monitored_by_code = null; 
				String drawdown_header_existing_condition_code = null; 
				String drawdown_header_desc = null;
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_drawdown_condition_header(dataId, business_id, drawdown_header_code, drawdown_header_sts, drawdown_header_detail_code, drawdown_header_monitored_by_code, drawdown_header_existing_condition_code, drawdown_header_desc, is_active, modified_date, modified_by, created_date, created_by)" + 
						"VALUES(                                                  '%s',   '%s',        '%s',                 '%s',                '%s',                        '%s',                             '%s',                                     '%s',                 %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, drawdown_header_code, drawdown_header_sts, drawdown_header_detail_code, drawdown_header_monitored_by_code, drawdown_header_existing_condition_code, drawdown_header_desc, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppDrawdownConditionHeader).setSheet(Sheet.InformasiDebitur));	
	}
	
	private void migrasiDlosAppOtherConditionExtHeader(String lobType) {
		IActions insertDlosAppOtherConditionExtHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String other_condition_ext_header_code = null;
				String other_condition_ext_header_sts = null;
				String other_condition_ext_header_detail_code = null; 
				String other_condition_ext_header_existing_condition_code = null; 
				String other_condition_ext_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null;
				String modified_by = null;
				String created_date = null;
				String created_by = MIGRATION;
				
				return String.format(  
						"INSERT INTO dlos_core.dlos_app_other_condition_ext_header (dataId, business_id, other_condition_ext_header_code, other_condition_ext_header_sts, other_condition_ext_header_detail_code, other_condition_ext_header_existing_condition_code, other_condition_ext_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                                    '%s',   '%s',        '%s',                            '%s',                           '%s',                                   '%s',                                               '%s',                            %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, other_condition_ext_header_code, other_condition_ext_header_sts, other_condition_ext_header_detail_code, other_condition_ext_header_existing_condition_code, other_condition_ext_header_desc, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppOtherConditionExtHeader).setSheet(Sheet.InformasiDebitur));		
	}
	
	private void migrasiDlosAppOtherConditionIntHeader(String lobType) {
		IActions insertDlosAppOtherConditionIntHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = null; 
				String other_condition_int_header_code = null; 
				String other_condition_int_header_sts = null; 
				String other_condition_int_header_detail_code = null; 
				String other_condition_int_header_existing_condition_code = null; 
				String other_condition_int_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = null; 
				String created_date = null; 
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_other_condition_int_header(dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(                                                   '%s',   '%s',        '%s',                            '%s',                           '%s',                                   '%s',                                               '%s',                            %s,        '%s',          '%s',        '%s',         '%s');", 
						dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppOtherConditionIntHeader).setSheet(Sheet.InformasiDebitur));		
	}


	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}