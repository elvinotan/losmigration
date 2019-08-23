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

public class TujuanDanFasilitas implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// Mencakup
	// dlos_app_facility (Master)
	// dlos_app_facility_dtl (*)
	// dlos_app_facility_oth (*)
	
	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_facility where created_by ='%s';", MIGRATION),
			String.format("delete from dlos_app_facility_dtl where created_by ='%s';", MIGRATION),
			String.format("delete from dlos_app_facility_oth where created_by ='%s';", MIGRATION),
		};
	}

	@Override
	public void initMapping(String lobType) {
		migrasiDlosAppFacility(lobType);
		migrasiDlosAppFacilityDtl(lobType);
		migrasiDlosAppFacilityOth(lobType);
	}
	
	private void migrasiDlosAppFacility(String lobType) {
		
		IActions insertDlosAppFacility = new IActions() {
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String dataId = store.getString("dataId"); 
				String fac_code = null; 
				String fac_desc = null;
				String fac_sts = null;
				String submission_purpose = null; 
				String credit_purpose_code = null;
				String submission_sts_code = null;
				String program_type_code = null;
				String ttl_subm_amt = mapper.getString("ttl_subm_amt");
				String ttl_cap_amt = mapper.getString("ttl_cap_amt");
				String ttl_invest_amt = mapper.getString("ttl_invest_amt");
				String ttl_etc_amt = mapper.getString("ttl_etc_amt");
				String ttl_oth_subm_amt = null;
				String ttl_oth_cap_amt = null;
				String ttl_oth_invest_amt = null;
				String ttl_oth_etc_amt = null;
				
				String dpd_lte_7d_insuf_bal_cnt = mapper.getString("dpd_lte_7d_insuf_bal_cnt");
				dpd_lte_7d_insuf_bal_cnt = "Tidak Ada".equals(dpd_lte_7d_insuf_bal_cnt) ? "0" : dpd_lte_7d_insuf_bal_cnt;
				
				String dpd_lte_7d_other_cnt = mapper.getString("dpd_lte_7d_other_cnt");
				dpd_lte_7d_other_cnt = "Tidak Ada".equals(dpd_lte_7d_other_cnt) ? "0" : dpd_lte_7d_other_cnt;
				
				String dpd_gt_7d_14d_cnt = mapper.getString("dpd_gt_7d_14d_cnt");
				dpd_gt_7d_14d_cnt = "Tidak Ada".equals(dpd_gt_7d_14d_cnt) ? "0" : dpd_gt_7d_14d_cnt;
				
				String dpd_gt_14d_cnt = mapper.getString("dpd_gt_14d_cnt");
				dpd_gt_14d_cnt = "Tidak Ada".equals(dpd_gt_14d_cnt) ? "0" : dpd_gt_14d_cnt;
				
				String overdue_gt_col1_reason = mapper.getString("overdue_gt_col1_reason");
				
				String closed_loan_last_2y_cnt = mapper.getString("closed_loan_last_2y_cnt");
				closed_loan_last_2y_cnt = "Tidak Ada".equals(closed_loan_last_2y_cnt) ? "0" : closed_loan_last_2y_cnt;
				
				String topup_fac_last_yr_cnt = mapper.getString("topup_fac_last_yr_cnt");
				topup_fac_last_yr_cnt = "Tidak Ada".equals(topup_fac_last_yr_cnt) ? "0" : topup_fac_last_yr_cnt;
				
				String has_ul_fac_code = mapper.getString("has_ul_fac_code");
				Lookup lhas_ul_fac_code = store.getLookupByDescription(Lookup.YesNo, has_ul_fac_code);
				has_ul_fac_code = (lhas_ul_fac_code == null) ? null : lhas_ul_fac_code.getKey();
				
				String ttl_ul_cnt = mapper.getString("ttl_ul_cnt");
				String ttl_ul_gt_80pct_cnt = mapper.getString("ttl_ul_gt_80pct_cnt");
				ttl_ul_gt_80pct_cnt = "Tidak Ada".equals(ttl_ul_gt_80pct_cnt) ? "0" : ttl_ul_gt_80pct_cnt;				
				
				String ttl_limit_ul_amt = mapper.getString("ttl_limit_ul_amt");
				String ttl_os_ul_amt = mapper.getString("ttl_os_ul_amt");
				String is_active = "1";
				String ttl_exist_subm_amt = mapper.getString("ttl_exist_subm_amt"); 
				String ttl_exist_cap_amt = mapper.getString("ttl_exist_cap_amt");
				String ttl_exist_invest_amt = mapper.getString("ttl_exist_invest_amt");
				String ttl_exist_etc_amt = mapper.getString("ttl_exist_etc_amt");
				String ttl_change_subm_amt = mapper.getString("ttl_change_subm_amt");
				String ttl_change_cap_amt = mapper.getString("ttl_change_cap_amt");
				String ttl_change_invest_amt = mapper.getString("ttl_change_invest_amt");
				String ttl_change_etc_amt = mapper.getString("ttl_change_etc_amt");
				String ttl_oth_principal_subm_amt = mapper.getString("ttl_oth_principal_subm_amt");
				String ttl_oth_principal_cap_amt = mapper.getString("ttl_oth_principal_cap_amt");
				String ttl_oth_principal_invest_amt = mapper.getString("ttl_oth_principal_invest_amt");
				String ttl_oth_principal_etc_amt = mapper.getString("ttl_oth_principal_etc_amt");
				String ttl_oth_os_subm_amt = mapper.getString("ttl_oth_os_subm_amt");
				String ttl_oth_os_cap_amt = mapper.getString("ttl_oth_os_cap_amt");
				String ttl_oth_os_invest_amt = mapper.getString("ttl_oth_os_invest_amt");
				String ttl_oth_os_etc_amt =  mapper.getString("ttl_oth_os_etc_amt");
				String ttl_idr_subm_amt = mapper.getString("ttl_idr_subm_amt");
				String ttl_idr_cap_amt = mapper.getString("ttl_idr_cap_amt");
				String ttl_idr_invest_amt = mapper.getString("ttl_idr_invest_amt");
				String ttl_idr_etc_amt = mapper.getString("ttl_idr_etc_amt");
				String ttl_os_subm_amt = mapper.getString("ttl_os_subm_amt");
				String ttl_os_cap_amt = mapper.getString("ttl_os_cap_amt");
				String ttl_os_invest_amt =  mapper.getString("ttl_os_invest_amt");
				String ttl_os_etc_amt = mapper.getString("ttl_os_etc_amt");
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;

				return String.format(
						"INSERT INTO dlos_core.dlos_app_facility (dataId, fac_code, fac_desc, fac_sts, submission_purpose, credit_purpose_code, submission_sts_code, program_type_code, ttl_subm_amt, ttl_cap_amt, ttl_invest_amt, ttl_etc_amt, ttl_oth_subm_amt, ttl_oth_cap_amt, ttl_oth_invest_amt, ttl_oth_etc_amt, dpd_lte_7d_insuf_bal_cnt, dpd_lte_7d_other_cnt, dpd_gt_7d_14d_cnt, dpd_gt_14d_cnt, overdue_gt_col1_reason, closed_loan_last_2y_cnt, topup_fac_last_yr_cnt, has_ul_fac_code, ttl_ul_cnt, ttl_ul_gt_80pct_cnt, ttl_limit_ul_amt, ttl_os_ul_amt, is_active, modified_date, modified_by, created_date, created_by, ttl_exist_subm_amt, ttl_exist_cap_amt, ttl_exist_invest_amt, ttl_exist_etc_amt, ttl_change_subm_amt, ttl_change_cap_amt, ttl_change_invest_amt, ttl_change_etc_amt, ttl_oth_principal_subm_amt, ttl_oth_principal_cap_amt, ttl_oth_principal_invest_amt, ttl_oth_principal_etc_amt, ttl_oth_os_subm_amt, ttl_oth_os_cap_amt, ttl_oth_os_invest_amt, ttl_oth_os_etc_amt, ttl_idr_subm_amt, ttl_idr_cap_amt, ttl_idr_invest_amt, ttl_idr_etc_amt, ttl_os_subm_amt, ttl_os_cap_amt, ttl_os_invest_amt, ttl_os_etc_amt) " + 
						"VALUES(                                  '%s',   '%s',     '%s',     '%s',    '%s',               '%s',                '%s',                '%s',              '%s',         '%s',        '%s',           '%s',        '%s',             '%s',            '%s',               '%s',            '%s',                     '%s',                 '%s',              '%s',           '%s',                   '%s',                    '%s',                  '%s',            '%s',       '%s',                '%s',             '%s',          %s,        '%s',          '%s',        '%s',         '%s',       '%s',               '%s',              '%s',                 '%s',              '%s',                '%s',               '%s',                  '%s',               '%s',                       '%s',                      '%s',                         '%s',                      '%s',                '%s',               '%s',                  '%s',               '%s',             '%s',            '%s',               '%s',            '%s',            '%s',           '%s',              '%s'); ", 
						dataId, fac_code, fac_desc, fac_sts, submission_purpose, credit_purpose_code, submission_sts_code, program_type_code, ttl_subm_amt, ttl_cap_amt, ttl_invest_amt, ttl_etc_amt, ttl_oth_subm_amt, ttl_oth_cap_amt, ttl_oth_invest_amt, ttl_oth_etc_amt, dpd_lte_7d_insuf_bal_cnt, dpd_lte_7d_other_cnt, dpd_gt_7d_14d_cnt, dpd_gt_14d_cnt, overdue_gt_col1_reason, closed_loan_last_2y_cnt, topup_fac_last_yr_cnt, has_ul_fac_code, ttl_ul_cnt, ttl_ul_gt_80pct_cnt, ttl_limit_ul_amt, ttl_os_ul_amt, is_active, modified_date, modified_by, created_date, created_by, ttl_exist_subm_amt, ttl_exist_cap_amt, ttl_exist_invest_amt, ttl_exist_etc_amt, ttl_change_subm_amt, ttl_change_cap_amt, ttl_change_invest_amt, ttl_change_etc_amt, ttl_oth_principal_subm_amt, ttl_oth_principal_cap_amt, ttl_oth_principal_invest_amt, ttl_oth_principal_etc_amt, ttl_oth_os_subm_amt, ttl_oth_os_cap_amt, ttl_oth_os_invest_amt, ttl_oth_os_etc_amt, ttl_idr_subm_amt, ttl_idr_cap_amt, ttl_idr_invest_amt, ttl_idr_etc_amt, ttl_os_subm_amt, ttl_os_cap_amt, ttl_os_invest_amt, ttl_os_etc_amt);
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			specRows.add(SpecRow.get(insertDlosAppFacility).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("closed_loan_last_2y_cnt", "H186")
					.xls("dpd_gt_14d_cnt", "H181")
					.xls("dpd_gt_7d_14d_cnt", "H180")
					.xls("dpd_lte_7d_insuf_bal_cnt", "H178")
					.xls("dpd_lte_7d_other_cnt", "H179")
					.xls("has_ul_fac_code", "H189")
					.xls("overdue_gt_col1_reason", "F182")
					.xls("topup_fac_last_yr_cnt", "H187")
					.xls("ttl_cap_amt", "F109")
					.xls("ttl_change_cap_amt", "E109")
					.xls("ttl_change_etc_amt", "E111")
					.xls("ttl_change_invest_amt", "E110")
					.xls("ttl_change_subm_amt", "E108")
					.xls("ttl_etc_amt", "F111")
					.xls("ttl_exist_cap_amt", "D109")
					.xls("ttl_exist_etc_amt", "D111")
					.xls("ttl_exist_invest_amt", "D110")
					.xls("ttl_exist_subm_amt", "D108")
					.xls("ttl_idr_cap_amt", "G109")
					.xls("ttl_idr_etc_amt", "G111")
					.xls("ttl_idr_invest_amt", "G110")
					.xls("ttl_idr_subm_amt", "G108")
					.xls("ttl_invest_amt", "F110")
					.xls("ttl_limit_ul_amt", "H192")
					.xls("ttl_os_cap_amt", "H109")
					.xls("ttl_os_etc_amt", "H111")
					.xls("ttl_os_invest_amt", "H110")
					.xls("ttl_os_subm_amt", "H108")
					.xls("ttl_os_ul_amt", "H193")
					.xls("ttl_oth_os_cap_amt", "G170")
					.xls("ttl_oth_os_etc_amt", "G172")
					.xls("ttl_oth_os_invest_amt", "G171")
					.xls("ttl_oth_os_subm_amt", "G169")
					.xls("ttl_oth_principal_cap_amt", "F170")
					.xls("ttl_oth_principal_etc_amt", "F172")
					.xls("ttl_oth_principal_invest_amt", "F171")
					.xls("ttl_oth_principal_subm_amt", "F169")
					.xls("ttl_subm_amt", "F108")
					.xls("ttl_ul_cnt", "H190")
					.xls("ttl_ul_gt_80pct_cnt", "H191")
					.pk("fac_id")
					);
		}
		
		if (LobType.isSmel(lobType)) {
			specRows.add(SpecRow.get(insertDlosAppFacility).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("closed_loan_last_2y_cnt", "G184") //H186
					.xls("dpd_gt_14d_cnt", "G179") //H181
					.xls("dpd_gt_7d_14d_cnt", "G178") // H180
					.xls("dpd_lte_7d_insuf_bal_cnt", "G176") //H178
					.xls("dpd_lte_7d_other_cnt", "G177") //H179
					.xls("has_ul_fac_code", "G187") //H189
					.xls("overdue_gt_col1_reason", "G180") //F182
					.xls("topup_fac_last_yr_cnt", "G185") //H187
					.xls("ttl_cap_amt", "F109") //F109
					.xls("ttl_change_cap_amt", "E109") //E109
					.xls("ttl_change_etc_amt", "E111") //E111
					.xls("ttl_change_invest_amt", "E110") //E110
					.xls("ttl_change_subm_amt", "E108") //E108
					.xls("ttl_etc_amt", "F111") //F111
					.xls("ttl_exist_cap_amt", "D109") //D109
					.xls("ttl_exist_etc_amt", "D111") //D111
					.xls("ttl_exist_invest_amt", "D110") //D110
					.xls("ttl_exist_subm_amt", "D108") //D108
					.xls("ttl_idr_cap_amt", "G109") //G109
					.xls("ttl_idr_etc_amt", "G111") //G111
					.xls("ttl_idr_invest_amt", "G110") //G110
					.xls("ttl_idr_subm_amt", "G108") //G108
					.xls("ttl_invest_amt", "F110") //F110
					.xls("ttl_limit_ul_amt", "G190") //H192
					.xls("ttl_os_cap_amt", "H109") //H109
					.xls("ttl_os_etc_amt", "H111") //H111
					.xls("ttl_os_invest_amt", "H110") //H110
					.xls("ttl_os_subm_amt", "H108") //H108
					.xls("ttl_os_ul_amt", "G191") //H193
					.xls("ttl_oth_os_cap_amt", "G168") //G170
					.xls("ttl_oth_os_etc_amt", "G170") //G172
					.xls("ttl_oth_os_invest_amt", "G169") //G171
					.xls("ttl_oth_os_subm_amt", "G167") //G169
					.xls("ttl_oth_principal_cap_amt", "F168") //F170
					.xls("ttl_oth_principal_etc_amt", "F170") //F172
					.xls("ttl_oth_principal_invest_amt", "F169") //F171
					.xls("ttl_oth_principal_subm_amt", "F167") //F169
					.xls("ttl_subm_amt", "F108") //F108
					.xls("ttl_ul_cnt", "G188") //H190
					.xls("ttl_ul_gt_80pct_cnt", "G189") //H191
					.pk("fac_id")
					);
		}
				
	}
	
	private void migrasiDlosAppFacilityDtl(String lobType) {
		
		IActions insertDlosAppFacilityDtl = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String dataId = store.getString("dataId");
				String fac_id = store.getString("fac_id");
				String fac_dtl_code = null; 
				String fac_dtl_desc = null;
				String fac_dtl_sts = null;
				
				String fac_name_code = mapper.getString("fac_name_code");
				Lookup lfac_name_code = store.getLookupByDescription(Lookup.FacilityName, fac_name_code);
				fac_name_code = (lfac_name_code == null) ? null : lfac_name_code.getKey();
				if (fac_name_code == null) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String fac_type_code = mapper.getString("fac_type_code");
				Lookup lfac_type_code = store.getLookupByDescription(Lookup.FacilityType, fac_type_code);
				fac_type_code = (lfac_type_code == null) ? null : lfac_type_code.getKey();
				
				String curr_code = mapper.getString("curr_code");
				if ("Rp".equals(curr_code)) curr_code = "IDR"; // Untuk Currency di manipulasi, mestinya ini gax masalah khan currency udah standard
				else {
					int sIdx = curr_code.indexOf("(");
					int eIdx = curr_code.indexOf(")");
					curr_code = curr_code.substring(sIdx+1, eIdx);
				}
				Lookup lcurr_code = store.getLookupByDescription(Lookup.Currency, curr_code);
				curr_code = (lcurr_code == null) ? null : lcurr_code.getKey();
				
				String existing_amt = mapper.getString("existing_amt");
				String change_amt = mapper.getString("change_amt");
				String subm_amt = mapper.getString("subm_amt");
				String tenor =  NumberTool.extractNumberOnly(mapper.getString("tenor"));
				String start_date = DateTool.getYMD(mapper.getString("start_date"));
				String end_date = DateTool.getYMD(mapper.getString("end_date"));
				
				String int_rate = mapper.getString("int_rate");
				if (!StringTool.isEmpty(int_rate)) {
					int_rate = NumberTool.format(Double.valueOf(int_rate) * 100);
				}
				
				String prov_rate = mapper.getString("prov_rate");
				if (!StringTool.isEmpty(prov_rate)) {
					prov_rate = NumberTool.format(Double.valueOf(prov_rate) * 100);
				}
				
				String inst_type_code = mapper.getString("inst_type_code");
				Lookup linst_type_code = store.getLookupByDescription(Lookup.Instalment, inst_type_code);
				inst_type_code = (linst_type_code == null) ? null : linst_type_code.getKey(); 
						
				String is_active = "1";
				String idr_subm_amt = mapper.getString("idr_subm_amt");
				String os_amt = mapper.getString("os_amt");
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_facility_dtl(dataId, fac_id, fac_dtl_code, fac_dtl_desc, fac_dtl_sts, fac_name_code, fac_type_code, curr_code, existing_amt, change_amt, subm_amt, tenor, start_date, end_date, int_rate, prov_rate, inst_type_code, is_active, modified_date, modified_by, created_date, created_by, idr_subm_amt, os_amt) " + 
						"VALUES(									 '%s',   '%s',   '%s',         '%s',         '%s',        '%s',          '%s',          '%s',      '%s',         '%s',       '%s',     '%s',  '%s',       '%s',     '%s',     '%s',      '%s',           %s,        '%s',          '%s',        '%s',         '%s',       '%s',         '%s');",
						dataId, fac_id, fac_dtl_code, fac_dtl_desc, fac_dtl_sts, fac_name_code, fac_type_code, curr_code, existing_amt, change_amt, subm_amt, tenor, start_date, end_date, int_rate, prov_rate, inst_type_code, is_active, modified_date, modified_by, created_date, created_by, idr_subm_amt, os_amt);
			}
		};
		
		for (int i = 0; i < 7; i++) {
			int ctr = 101 + i;
			specRows.add(SpecRow.get(insertDlosAppFacilityDtl).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("change_amt", "E"+ctr)
					.xls("curr_code", "C"+ctr)
					.xls("end_date", "K"+ctr)
					.xls("existing_amt", "D"+ctr)
					.xls("fac_name_code", "A"+ctr)
					.xls("fac_type_code", "B"+ctr)
					.xls("idr_subm_amt", "G"+ctr)
					.xls("inst_type_code", "N"+ctr)
					.xls("int_rate", "L"+ctr)
					.xls("os_amt", "H"+ctr)
					.xls("prov_rate", "M"+ctr)
					.xls("start_date", "J"+ctr)
					.xls("subm_amt", "F"+ctr)
					.xls("tenor", "I"+ctr)
					);
			
		}
	}

	private void migrasiDlosAppFacilityOth(String lobType) {
		
		IActions insertDlosAppFacilityOth = new IActions() {			
			
			@Override
			public String insert(Mapper mapper, Store store, String lobType) throws Exception {
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
				fac_type_code = (lfac_type_code == null) ? null : lfac_type_code.getKey();
				
				String is_takeover = mapper.getString("is_takeover");
				Lookup lis_takeover = store.getLookupByDescription(Lookup.YesNo, is_takeover);
				is_takeover = (lis_takeover == null) ? null : lis_takeover.getKey();
				
				String principal_amt = mapper.getString("principal_amt");
				String os_amt = mapper.getString("os_amt");
				
				String start_date = DateTool.getYMD(mapper.getString("start_date"));
				if (start_date != null && "-".equals(start_date.trim())) start_date = null;
				
				String customer_name = mapper.getString("customer_name");
				
				String coll_type = mapper.getString("coll_type");
				Lookup lcoll_type = store.getLookupByDescription(Lookup.CollateralProperty, coll_type);
				coll_type = (lcoll_type == null) ? null : lcoll_type.getKey();				
				
				String coll_amt = mapper.getString("coll_amt");
				
				String bi_last_3mos_code = mapper.getString("bi_last_3mos_code");
				Lookup lbi_last_3mos_code = store.getLookupByDescription(Lookup.BIChecking, bi_last_3mos_code);
				bi_last_3mos_code = (lbi_last_3mos_code == null) ? null : lbi_last_3mos_code.getKey();
				
				String dpd_last_3_mos = mapper.getString("dpd_last_3_mos");
				String is_active = "1";
				String tenor = mapper.getString("tenor");
				
				String bi_collect_last_2mos = mapper.getString("bi_collect_last_2mos");
				Lookup lbi_collect_last_2mos = store.getLookupByDescription(Lookup.BIChecking, bi_collect_last_2mos);
				bi_collect_last_2mos = (lbi_collect_last_2mos == null) ? null : lbi_collect_last_2mos.getKey();
				
				String bi_collect_last_1mos = mapper.getString("bi_collect_last_1mos");
				Lookup lbi_collect_last_1mos = store.getLookupByDescription(Lookup.BIChecking, bi_collect_last_1mos);
				bi_collect_last_1mos = (lbi_collect_last_1mos == null) ? null : lbi_collect_last_1mos.getKey();
				
				String modified_date = null;
				String modified_by = mapper.getString("appId");;
				String created_date = DateTool.getYMD(mapper.getString("createdDate"));
				String created_by = MIGRATION;
				
				return String.format( 
						"INSERT INTO dlos_core.dlos_app_facility_oth (dataId, fac_id, fac_oth_code, fac_oth_desc, fac_oth_sts, bank_name, fac_name, fac_type_code, is_takeover, principal_amt, os_amt, start_date, customer_name, coll_type, coll_amt, bi_last_3mos_code, dpd_last_3_mos, is_active, modified_date, modified_by, created_date, created_by, tenor, bi_collect_last_2mos, bi_collect_last_1mos) " + 
						"VALUES(                                      '%s',   '%s',   '%s',         '%s',         '%s',        '%s',      '%s',     '%s',          '%s',        '%s',          '%s',   '%s',       '%s',          '%s',      '%s',     '%s',              '%s',           %s,        '%s',          '%s',        '%s',         '%s',       '%s',  '%s',                 '%s'); ", 
						dataId, fac_id, fac_oth_code, fac_oth_desc, fac_oth_sts, bank_name, fac_name, fac_type_code, is_takeover, principal_amt, os_amt, start_date, customer_name, coll_type, coll_amt, bi_last_3mos_code, dpd_last_3_mos, is_active, modified_date, modified_by, created_date, created_by, tenor, bi_collect_last_2mos, bi_collect_last_1mos);
			}
		};
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 15; i++) {
				int ctr = 154 + i;
				
				specRows.add(SpecRow.get(insertDlosAppFacilityOth).setSheet(Sheet.InformasiDebitur)
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
				
				specRows.add(SpecRow.get(insertDlosAppFacilityOth).setSheet(Sheet.InformasiDebitur)
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

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}

}
