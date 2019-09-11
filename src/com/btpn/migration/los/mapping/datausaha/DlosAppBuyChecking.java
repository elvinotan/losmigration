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

public class DlosAppBuyChecking {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
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
				goods_services_quality_code = (lgoods_services_quality_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyChecking") : lgoods_services_quality_code.getKey();
				
				String is_buyer_verified_code = mapper.getString("is_buyer_verified_code");
				Lookup lis_buyer_verified_code = store.getLookupByDescription(Lookup.YesNo, is_buyer_verified_code);
				is_buyer_verified_code = (lis_buyer_verified_code == null) ? mapper.logMapperProblem("migrasiDlosAppBuyChecking") : lis_buyer_verified_code.getKey();
				
				String avg_mo_purchase_amt = mapper.getString("avg_mo_purchase_amt"); 
				String purch_pct = mapper.getString("purch_pct"); 
				if (!StringTool.isEmpty(purch_pct)) {
					purch_pct = NumberTool.format(Double.valueOf(purch_pct) * 100);
				}
				
				String goods_services_purch_freq_cnt = mapper.getString("goods_services_purch_freq_cnt");
				Lookup lgoods_services_purch_freq_cnt = store.getLookupByDescription(Lookup.GoodsServicesPurchFreq, goods_services_purch_freq_cnt);
				goods_services_purch_freq_cnt = (lgoods_services_purch_freq_cnt == null) ? mapper.logMapperProblem("migrasiDlosAppBuyChecking") : lgoods_services_purch_freq_cnt.getKey();
				
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
				
				specRows.add(SpecRow.get(insert)
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
				
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
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
}
