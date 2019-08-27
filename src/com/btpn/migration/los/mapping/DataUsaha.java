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
				String.format("delete from dlos_app_other_condition_int_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_other_condition_ext_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_drawdown_condition_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_precedent_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_covenant_header where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_buy_payments where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_buy_checking where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_sup_payments where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_sup_checking where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_business_neighbour where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_business_oth where created_by ='%s';", MIGRATION),
				String.format("delete from dlos_app_business where created_by ='%s';", MIGRATION),
			};
	}
	
	@Override
	public void initMapping(String lobType) {
		migrasiDlosAppBusiness(lobType);
//		migrasiDlosAppBusinessOth(lobType); Tidak ada di bagian mapping
//		migrasiDlosAppBusinessNeighbour(lobType); Tidak ada di bagian mapping
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
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {				
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
		specRows.add(SpecRow.get(insertDlosAppBusinessOth).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppBusinessNeighbour(String lobType) {
		IActions insertDlosAppBusinessNeighbour = new IActions() {
			
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
		specRows.add(SpecRow.get(insertDlosAppBusinessNeighbour).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppSupChecking(String lobType) {
		IActions insertDlosAppSupChecking = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String supplier_name = mapper.getString("supplier_name"); 
				if (supplier_name == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String supplier_checking_code = null; 
				String supplier_checking_sts = null;
				String check_date = null;
				
				if (LobType.isSmes(lobType)) {
					// Cell Type bukan date jadi untuk sementara di set null saja, daripada data yang masuk rusak
				}
				if (LobType.isSmel(lobType)) {
					check_date = DateTool.getYMD(mapper.getString("check_date"));  // Untuk samall bermsalah krn dia hanya entry manual bukan cell date	
				}
				
				String verification_method = null;				
				String business_type = mapper.getString("business_type"); 
				String info_provider_name = mapper.getString("info_provider_name"); 
				String info_provider_title = mapper.getString("info_provider_title"); 
				String supplier_address = mapper.getString("supplier_address"); 
				String supplier_phone_no = mapper.getString("supplier_phone_no"); 
				String is_owner_pros_debtor = null; 
				String business_relation_yr_cnt = mapper.getString("business_relation_yr_cnt"); 
				String goods_services_sold = mapper.getString("goods_services_sold"); 
				
				String is_supplier_verified_code = mapper.getString("is_supplier_verified_code");
				Lookup lis_supplier_verified_code = store.getLookupByDescription(Lookup.YesNo, is_supplier_verified_code);
				is_supplier_verified_code = (lis_supplier_verified_code == null) ? null : lis_supplier_verified_code.getKey();
				
				String avg_mo_sales_amt = mapper.getString("avg_mo_sales_amt"); 
				String sales_pct = mapper.getString("sales_pct"); 
				if (!StringTool.isEmpty(sales_pct)) {
					sales_pct = NumberTool.format(Double.valueOf(sales_pct) * 100);
				}
				
				String sales_freq_code = mapper.getString("sales_freq_code");
				Lookup lsales_freq_code = store.getLookupByDescription(Lookup.SalesFreq, sales_freq_code);
				sales_freq_code = (lsales_freq_code == null) ? null : lsales_freq_code.getKey();
				
				String payment_freq_code = mapper.getString("payment_freq_code");
				Lookup lpayment_freq_code = store.getLookupByDescription(Lookup.PaymentFreq, payment_freq_code);
				payment_freq_code = (lpayment_freq_code == null) ? null : lpayment_freq_code.getKey();
				
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppSupChecking",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_sup_checking (dataId, business_id, supplier_checking_code, supplier_checking_sts, check_date, verification_method, supplier_name, business_type, info_provider_name, info_provider_title, supplier_address, supplier_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, is_supplier_verified_code, avg_mo_sales_amt, sales_pct, sales_freq_code, payment_freq_code, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                      '%s',   '%s',        '%s',                   '%s',                  '%s',       '%s',                '%s',          '%s',          '%s',               '%s',                '%s',             '%s',              '%s',                 '%s',                     '%s',                '%s',                      '%s',             '%s',      '%s',            '%s',              %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, supplier_checking_code, supplier_checking_sts, check_date, verification_method, supplier_name, business_type, info_provider_name, info_provider_title, supplier_address, supplier_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, is_supplier_verified_code, avg_mo_sales_amt, sales_pct, sales_freq_code, payment_freq_code, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 5; i++) {
				int ctr = 199+i;
				int ctrBusinessType = 13 + (26*i);
				int ctrInfoProviderTitle = 15 + (26*i);
				int ctrIsSupplierVerifiedCode = 22 + (26*i);
				int ctrPaymentFreqCode = 26 + (26*i);
				int ctrSalesFreqCode = 25 + (26*i);
				int ctrSalesPct = 24 + (26*i);
				
				specRows.add(SpecRow.get(insertDlosAppSupChecking).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("avg_mo_sales_amt", "J"+ctr)
					.xls("business_relation_yr_cnt", "N"+ctr)
					.xls("goods_services_sold", "E"+ctr)
					.xls("info_provider_name", "G"+ctr)
					.xls("supplier_address", "B"+ctr)
					.xls("supplier_name", "A"+ctr)
					.xls("supplier_phone_no", "I"+ctr)
					.setSheet(Sheet.SupplierChecking)
					.xls("business_type", "C"+ctrBusinessType)
					.xls("check_date", "C5")
					.xls("info_provider_title", "C"+ctrInfoProviderTitle)
					.xls("is_supplier_verified_code", "C"+ctrIsSupplierVerifiedCode)
					.xls("payment_freq_code", "C"+ctrPaymentFreqCode)
					.xls("sales_freq_code", "C"+ctrSalesFreqCode)
					.xls("sales_pct", "C"+ctrSalesPct)
					);
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 5; i++) {
				int ctr = 197+i;
				int ctrBusinessType = 18+ (26*i);
				int ctrInfoProviderTitle = 20+ (26*i);
				int ctrIsSupplierVerifiedCode = 27+ (26*i);
				int ctrPaymentFreqCode = 31+ (26*i);
				int ctrSalesFreqCode = 30 + (26*i);
				int ctrSalesPct = 29 + (26*i);
				
				specRows.add(SpecRow.get(insertDlosAppSupChecking).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("avg_mo_sales_amt", "J"+ctr)
					.xls("business_relation_yr_cnt", "N"+ctr)
					.xls("goods_services_sold", "E"+ctr)
					.xls("info_provider_name", "G"+ctr)
					.xls("supplier_address", "B"+ctr)
					.xls("supplier_name", "A"+ctr)
					.xls("supplier_phone_no", "I"+ctr)
					.setSheet(Sheet.SupplierChecking)
					.xls("business_type", "C"+ctrBusinessType)
					.xls("check_date", "C5")
					.xls("info_provider_title", "C"+ctrInfoProviderTitle)
					.xls("is_supplier_verified_code", "C"+ctrIsSupplierVerifiedCode)
					.xls("payment_freq_code", "C"+ctrPaymentFreqCode)
					.xls("sales_freq_code", "C"+ctrSalesFreqCode)
					.xls("sales_pct", "C"+ctrSalesPct)
					);
			}
		}
	} 
	
	private void migrasiDlosAppSupPayments(String lobType) {
		IActions insertDlosAppSupPayments = new IActions() {
			
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
				timely_pymt_code = (ltimely_pymt_code == null) ? null : ltimely_pymt_code.getKey();
				
				String is_cont_relation_code = mapper.getString("is_cont_relation_code");
				Lookup lis_cont_relation_code = store.getLookupByDescription(Lookup.YesNo, is_cont_relation_code);
				is_cont_relation_code = (lis_cont_relation_code == null) ? null : lis_cont_relation_code.getKey();
				
				String is_neg_info_code = mapper.getString("is_neg_info_code");
				Lookup lis_neg_info_code = store.getLookupByDescription(Lookup.YesNo, is_neg_info_code);
				is_neg_info_code = (lis_neg_info_code == null) ? null : lis_neg_info_code.getKey();
				
				
				String neg_info_desc = StringTool.combine(mapper.getString("neg_info_desc0"), mapper.getString("neg_info_desc1"));
				
				String pymt_freq_majority_cnt = mapper.getString("pymt_freq_majority_cnt");
				Lookup lpymt_freq_majority_cnt = store.getLookupByDescription(Lookup.PaymentFreq, pymt_freq_majority_cnt);
				pymt_freq_majority_cnt = (lpymt_freq_majority_cnt == null) ? null : lpymt_freq_majority_cnt.getKey();
						
				String top3_sup_sales_pct = mapper.getString("top3_sup_sales_pct"); 
				if (!StringTool.isEmpty(top3_sup_sales_pct)) {
					top3_sup_sales_pct = NumberTool.format(Double.valueOf(top3_sup_sales_pct) * 100);
				}
				
				String main_sup_dependency_code = mapper.getString("main_sup_dependency_code");
				Lookup lmain_sup_dependency_code = store.getLookupByDescription(Lookup.SupDependency, main_sup_dependency_code);
				main_sup_dependency_code = (lmain_sup_dependency_code == null) ? null : lmain_sup_dependency_code.getKey(); 
				
				String is_single_sup_code = null; 
				
				String additional_info = mapper.getString("additional_info");
				Lookup ladditional_info = store.getLookupByDescription(Lookup.AdditionalInfo, additional_info);
				additional_info = (ladditional_info == null) ? null : ladditional_info.getKey();				
				
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
				
				specRows.add(SpecRow.get(insertDlosAppSupPayments).setSheet(Sheet.InformasiDebitur)
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
				
				specRows.add(SpecRow.get(insertDlosAppSupPayments).setSheet(Sheet.InformasiDebitur)
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
	
	private void migrasiDlosAppBuyChecking(String lobType) {
		IActions insertDlosAppBuyChecking = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String buyer_name = mapper.getString("buyer_name");
				if (buyer_name == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String buyer_checking_code = null; 
				String buyer_checking_desc = null; 
				String buyer_checking_sts = null; 
				String check_date = null; 
				if (LobType.isSmes(lobType)) {
					// Cell Type bukan date jadi untuk sementara di set null saja, daripada data yang masuk rusak
				}
				if (LobType.isSmel(lobType)) {
					check_date = DateTool.getYMD(mapper.getString("check_date"));  // Untuk samall bermsalah krn dia hanya entry manual bukan cell date	
				}
				
				String verification_method = null;
				String buyer_type_code = null; 
				String business_type_code = mapper.getString("business_type_code"); 
				String info_provider_name = mapper.getString("info_provider_name"); 
				String info_provider_title = mapper.getString("info_provider_title"); 
				String buyer_address = mapper.getString("buyer_address"); 
				String buyer_phone_no = mapper.getString("buyer_phone_no"); 
				String is_owner_pros_debtor = null; 
				String business_relation_yr_cnt = mapper.getString("business_relation_yr_cnt"); 
				String goods_services_sold = mapper.getString("goods_services_sold"); 
				
				String goods_services_quality_code = mapper.getString("goods_services_quality_code");
				Lookup lgoods_services_quality_code = store.getLookupByDescription(Lookup.GoodsServicesQuality, goods_services_quality_code);
				goods_services_quality_code = (lgoods_services_quality_code == null) ? null : lgoods_services_quality_code.getKey();
				
				String is_buyer_verified_code = mapper.getString("is_buyer_verified_code");
				Lookup lis_buyer_verified_code = store.getLookupByDescription(Lookup.YesNo, is_buyer_verified_code);
				is_buyer_verified_code = (lis_buyer_verified_code == null) ? null : lis_buyer_verified_code.getKey();
				
				String avg_mo_purchase_amt = mapper.getString("avg_mo_purchase_amt"); 
				String purch_pct = mapper.getString("purch_pct"); 
				if (!StringTool.isEmpty(purch_pct)) {
					purch_pct = NumberTool.format(Double.valueOf(purch_pct) * 100);
				}
				
				String goods_services_purch_freq_cnt = mapper.getString("goods_services_purch_freq_cnt");
				Lookup lgoods_services_purch_freq_cnt = store.getLookupByDescription(Lookup.GoodsServicesPurchFreq, goods_services_purch_freq_cnt);
				goods_services_purch_freq_cnt = (lgoods_services_purch_freq_cnt == null) ? null : lgoods_services_purch_freq_cnt.getKey();
				
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppBuyChecking",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_buy_checking (dataId, business_id, buyer_checking_code, buyer_checking_desc, buyer_checking_sts, check_date, verification_method, buyer_name, buyer_type_code, business_type_code, info_provider_name, info_provider_title, buyer_address, buyer_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, goods_services_quality_code, is_buyer_verified_code, avg_mo_purchase_amt, purch_pct, goods_services_purch_freq_cnt, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                      '%s',   '%s',        '%s',                '%s',                '%s',               '%s',       '%s',                '%s',       '%s',            '%s',               '%s',               '%s',                '%s',          '%s',           '%s',                 '%s',                     '%s',                '%s',                        '%s',                   '%s',                '%s',      '%s',                          %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, buyer_checking_code, buyer_checking_desc, buyer_checking_sts, check_date, verification_method, buyer_name, buyer_type_code, business_type_code, info_provider_name, info_provider_title, buyer_address, buyer_phone_no, is_owner_pros_debtor, business_relation_yr_cnt, goods_services_sold, goods_services_quality_code, is_buyer_verified_code, avg_mo_purchase_amt, purch_pct, goods_services_purch_freq_cnt, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		};
		
		if (LobType.isSmes(lobType)) {			
			for (int i = 0; i < 5; i++) {
				int ctr = 207 + i;
				int ctrBusinessTypeCode = 14 + (26*i);
				int goodsServicesPurchFreqCnt = 27 + (26*i);
				int goodServicesQualityCode = 23 + (26*i);
				int infoProviderTitle = 16 + (26*i);
				int isBuyerVerifiedCode = 24 + (26*i);
				int purchPct = 26 + (26*i);
				
				specRows.add(SpecRow.get(insertDlosAppBuyChecking)
						.setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("avg_mo_purchase_amt", "J"+ctr)
						.xls("business_relation_yr_cnt", "N"+ctr)
						.xls("buyer_address", "B"+ctr)
						.xls("buyer_name", "A"+ctr)
						.xls("buyer_phone_no", "I"+ctr)
						.xls("goods_services_sold", "E"+ctr)
						.xls("info_provider_name", "G"+ctr)
							
						.setSheet(Sheet.BuyerChecking)
						.xls("business_type_code", "C"+ctrBusinessTypeCode)
						.xls("check_date", "C6")
						.xls("goods_services_purch_freq_cnt", "C"+goodsServicesPurchFreqCnt)
						.xls("goods_services_quality_code", "C"+goodServicesQualityCode)
						.xls("info_provider_title", "C"+infoProviderTitle)
						.xls("is_buyer_verified_code", "C"+isBuyerVerifiedCode)
						.xls("purch_pct", "C"+purchPct)
						);	
			}
		}
		
		if (LobType.isSmel(lobType)) {			
			for (int i = 0; i < 5; i++) {
				int ctr = 205 + i;
				int ctrBusinessTypeCode = 21 + (26*i);
				int goodsServicesPurchFreqCnt = 34 + (26*i);
				int goodServicesQualityCode = 30 + (26*i);
				int infoProviderTitle = 23 + (26*i);
				int isBuyerVerifiedCode = 31 + (26*i);
				int purchPct = 33 + (26*i);
				
				specRows.add(SpecRow.get(insertDlosAppBuyChecking).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("avg_mo_purchase_amt", "J"+ctr)
						.xls("business_relation_yr_cnt", "N"+ctr)
						.xls("buyer_address", "B"+ctr)
						.xls("buyer_name", "A"+ctr)
						.xls("buyer_phone_no", "I"+ctr)
						.xls("goods_services_sold", "E"+ctr)
						.xls("info_provider_name", "G"+ctr)
						
						.setSheet(Sheet.BuyerChecking)
						.xls("business_type_code", "C"+ctrBusinessTypeCode)
						.xls("check_date", "C6")
						.xls("goods_services_purch_freq_cnt", "C"+goodsServicesPurchFreqCnt)
						.xls("goods_services_quality_code", "C"+goodServicesQualityCode)
						.xls("info_provider_title", "C"+infoProviderTitle)
						.xls("is_buyer_verified_code", "C"+isBuyerVerifiedCode)
						.xls("purch_pct", "C"+purchPct)
						);
			}
		}
	}
	
	private void migrasiDlosAppBuyPayment(String lobType) {		
		IActions insertDlosAppBuyPayment = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {				
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
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
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppBuyPayment).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppCovenantHeader(String lobType) {
		IActions insertDlosAppCovenantHeader = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String cov_header_code = null; 
				String cov_header_sts = null; 
				String cov_header_detail_code = null; 
				String cov_header_monitored_by_code = null; 
				String cov_header_freq_code = null; 
				String cov_header_existing_condition_code = null; 
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
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppCovenantHeader).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppPrecedentHeader(String lobType) {
		IActions insertDlosAppPrecedentHeader = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id");
				String precedent_header_code = null; 
				String precedent_header_sts = null; 
				String precedent_header_detail_code = null; 
				String precedent_header_monitored_by_code = null; 
				String precedent_header_existing_condition_code = null; 
				String precedent_header_desc = null; String is_active = null; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppPrecedentHeader",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_precedent_header(dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by) "+ 
								"VALUES(                                         '%s',   '%s',        '%s',                  '%s',                 '%s',                         '%s',                               '%s',                                     '%s',                  %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, precedent_header_code, precedent_header_sts, precedent_header_detail_code, precedent_header_monitored_by_code, precedent_header_existing_condition_code, precedent_header_desc, is_active, modified_date, modified_by, created_date, created_by)
						
				};
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppPrecedentHeader).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppDrawdownConditionHeader(String lobType) {
		IActions insertDlosAppDrawdownConditionHeader = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String drawdown_header_code = null; 
				String drawdown_header_sts = null; 
				String drawdown_header_detail_code = null; 
				String drawdown_header_monitored_by_code = null; 
				String drawdown_header_existing_condition_code = null; 
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
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppDrawdownConditionHeader).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppOtherConditionExtHeader(String lobType) {
		IActions insertDlosAppOtherConditionExtHeader = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String other_condition_ext_header_code = null;
				String other_condition_ext_header_sts = null;
				String other_condition_ext_header_detail_code = null; 
				String other_condition_ext_header_existing_condition_code = null; 
				String other_condition_ext_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppOtherConditionExtHeader",
						
						String.format(  
								"INSERT INTO dlos_core.dlos_app_other_condition_ext_header (dataId, business_id, other_condition_ext_header_code, other_condition_ext_header_sts, other_condition_ext_header_detail_code, other_condition_ext_header_existing_condition_code, other_condition_ext_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                                    '%s',   '%s',        '%s',                            '%s',                           '%s',                                   '%s',                                               '%s',                            %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, other_condition_ext_header_code, other_condition_ext_header_sts, other_condition_ext_header_detail_code, other_condition_ext_header_existing_condition_code, other_condition_ext_header_desc, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppOtherConditionExtHeader).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}
	
	private void migrasiDlosAppOtherConditionIntHeader(String lobType) {
		IActions insertDlosAppOtherConditionIntHeader = new IActions() {
			
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				String business_id = store.getString("business_id"); 
				String other_condition_int_header_code = null; 
				String other_condition_int_header_sts = null; 
				String other_condition_int_header_detail_code = null; 
				String other_condition_int_header_existing_condition_code = null; 
				String other_condition_int_header_desc = null; 
				String is_active = "1"; 
				String modified_date = null; 
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate")); 
				String created_by = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppOtherConditionIntHeader",
						
						String.format( 
								"INSERT INTO dlos_core.dlos_app_other_condition_int_header(dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by) " + 
								"VALUES(                                                   '%s',   '%s',        '%s',                            '%s',                           '%s',                                   '%s',                                               '%s',                            %s,        '%s',          '%s',        '%s',         '%s');", 
								dataId, business_id, other_condition_int_header_code, other_condition_int_header_sts, other_condition_int_header_detail_code, other_condition_int_header_existing_condition_code, other_condition_int_header_desc, is_active, modified_date, modified_by, created_date, created_by)
				};
			}
		}; //BELUM DI BUAT
		specRows.add(SpecRow.get(insertDlosAppOtherConditionIntHeader).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.xls("createdDate", "J4"));
	}


	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}