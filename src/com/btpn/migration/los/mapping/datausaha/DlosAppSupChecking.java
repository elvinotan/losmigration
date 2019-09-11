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

public class DlosAppSupChecking {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		IActions insert = new IActions() {
			
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
				is_supplier_verified_code = (lis_supplier_verified_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupChecking") : lis_supplier_verified_code.getKey();
				
				String avg_mo_sales_amt = mapper.getString("avg_mo_sales_amt"); 
				String sales_pct = mapper.getString("sales_pct"); 
				if (!StringTool.isEmpty(sales_pct)) {
					sales_pct = NumberTool.format(Double.valueOf(sales_pct) * 100);
				}
				
				String sales_freq_code = mapper.getString("sales_freq_code");
				Lookup lsales_freq_code = store.getLookupByDescription(Lookup.SalesFreq, sales_freq_code);
				sales_freq_code = (lsales_freq_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupChecking") : lsales_freq_code.getKey();
				
				String payment_freq_code = mapper.getString("payment_freq_code");
				Lookup lpayment_freq_code = store.getLookupByDescription(Lookup.PaymentFreq, payment_freq_code);
				payment_freq_code = (lpayment_freq_code == null) ? mapper.logMapperProblem("migrasiDlosAppSupChecking") : lpayment_freq_code.getKey();
				
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
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
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
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
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
}
