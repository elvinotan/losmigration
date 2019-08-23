package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;

public class DataUsaha implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
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
				String dataId = null; 
				String business_code = null; 
				String business_name = null;
				String business_sts = null;
				String company_name = null;
				String ind_sec_code = null;
				String business_type_code = null; 
				String economy_sector_code = null;
				String business_desc = null;
				String company_history = null;
				String company_risk = null;
				String stock_rotation = null;
				String annual_cycle = null;
				String account_receivable_policy = null; 
				String business_manager_admin = null;
				String competitor_analysis = null;
				String other_company_party = null;
				String business_scale_code = null;
				String business_main_founder_code = null;
				String marketing_area_code = null;
				String market_share_cnt = null;
				String employee_cnt = null;
				String operate_since_cnt = null;
				String business_experience_cnt = null;
				String registered_company_period_cnt = null;
				String source_of_info_code = null;
				String key_person_code = null;
				String business_strategy_code = null;
				String business_strategy_desc = null;
				String empty_store_last_6mo_code = null;
				String has_other_business = null;
				String other_business_type_code = null;
				String other_business_pct = null;
				String main_competitor = null;
				String business_activity = null;
				String business_detail = null;
				String project_plan_list = null;
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
				String notes = null; 
				String is_active = "1";
				String modified_date = null;
				String modified_by = null;
				String created_date = null;
				String created_by = null;
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_business (dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by) " + 
						"VALUES(								  '%s',   '%s',          '%s',          '%s',         '%s',         '%s',         '%s',               '%s',                '%s',          '%s',            '%s',         '%s',           '%s',         '%s',                      '%s',                   '%s',                '%s',                '%s',                '%s',                       '%s',                '%s',             '%s',         '%s',              '%s',                    '%s',                          '%s',                '%s',            '%s',                   '%s',                   '%s',                      '%s',               '%s',                     '%s',               '%s',            '%s',              '%s',            '%s',              '%s',           '%s',            '%s',                     '%s',                                 '%s',                '%s',                '%s',                '%s',                     '%s',                              '%s',             '%s',             '%s',             '%s',                  '%s',  %s,        '%s',          '%s',        '%s',         '%s'); ", 
						dataId, business_code, business_name, business_sts, company_name, ind_sec_code, business_type_code, economy_sector_code, business_desc, company_history, company_risk, stock_rotation, annual_cycle, account_receivable_policy, business_manager_admin, competitor_analysis, other_company_party, business_scale_code, business_main_founder_code, marketing_area_code, market_share_cnt, employee_cnt, operate_since_cnt, business_experience_cnt, registered_company_period_cnt, source_of_info_code, key_person_code, business_strategy_code, business_strategy_desc, empty_store_last_6mo_code, has_other_business, other_business_type_code, other_business_pct, main_competitor, business_activity, business_detail, project_plan_list, nik_match_code, name_match_code, income_monthly_omzet_amt, income_financial_rec_based_omzet_amt, income_last_3mo_amt, income_last_2mo_amt, income_last_1mo_amt, income_omzet_source_desc, doc_financial_rec_based_omzet_amt, doc_last_3mo_amt, doc_last_2mo_amt, doc_last_1mo_amt, doc_omzet_source_desc, notes, is_active, modified_date, modified_by, created_date, created_by);
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBusiness).setSheet(Sheet.InformasiDebitur));
		
	}
	
	private void migrasiDlosAppBusinessOth(String lobType) {
		IActions insertDlosAppBusinessOth = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String dataId = null; 
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
				String created_by = null;
				
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

				String dataId = null; 
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
				String created_by = null;				
				
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
				String dataId = null; 
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
				String created_by = null;
				
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
			
			String dataId = null; 
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
			String created_by = null;
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
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
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppBuyPayment(String lobType) {
		IActions insertDlosAppBuyPayment = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppCovenantHeader(String lobType) {
		IActions insertDlosAppCovenantHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppPrecedentHeader(String lobType) {
		IActions insertDlosAppPrecedentHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppDrawdownConditionHeader(String lobType) {
		IActions insertDlosAppDrawdownConditionHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppOtherConditionExtHeader(String lobType) {
		IActions insertDlosAppOtherConditionExtHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};		
	}
	
	private void migrasiDlosAppOtherConditionIntHeader(String lobType) {
		IActions insertDlosAppOtherConditionIntHeader = new IActions() {
			
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};
	}


	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}