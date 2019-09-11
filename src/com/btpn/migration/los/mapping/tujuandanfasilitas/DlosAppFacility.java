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

public class DlosAppFacility {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				
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
				has_ul_fac_code = (lhas_ul_fac_code == null) ? mapper.logMapperProblem("migrasiDlosAppFacility") : lhas_ul_fac_code.getKey();
				
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

				return new String[] {
						"migrasiDlosAppFacility",
						
						String.format(
						"INSERT INTO dlos_core.dlos_app_facility (dataId, fac_code, fac_desc, fac_sts, submission_purpose, credit_purpose_code, submission_sts_code, program_type_code, ttl_subm_amt, ttl_cap_amt, ttl_invest_amt, ttl_etc_amt, ttl_oth_subm_amt, ttl_oth_cap_amt, ttl_oth_invest_amt, ttl_oth_etc_amt, dpd_lte_7d_insuf_bal_cnt, dpd_lte_7d_other_cnt, dpd_gt_7d_14d_cnt, dpd_gt_14d_cnt, overdue_gt_col1_reason, closed_loan_last_2y_cnt, topup_fac_last_yr_cnt, has_ul_fac_code, ttl_ul_cnt, ttl_ul_gt_80pct_cnt, ttl_limit_ul_amt, ttl_os_ul_amt, is_active, modified_date, modified_by, created_date, created_by, ttl_exist_subm_amt, ttl_exist_cap_amt, ttl_exist_invest_amt, ttl_exist_etc_amt, ttl_change_subm_amt, ttl_change_cap_amt, ttl_change_invest_amt, ttl_change_etc_amt, ttl_oth_principal_subm_amt, ttl_oth_principal_cap_amt, ttl_oth_principal_invest_amt, ttl_oth_principal_etc_amt, ttl_oth_os_subm_amt, ttl_oth_os_cap_amt, ttl_oth_os_invest_amt, ttl_oth_os_etc_amt, ttl_idr_subm_amt, ttl_idr_cap_amt, ttl_idr_invest_amt, ttl_idr_etc_amt, ttl_os_subm_amt, ttl_os_cap_amt, ttl_os_invest_amt, ttl_os_etc_amt) " + 
						"VALUES(                                  '%s',   '%s',     '%s',     '%s',    '%s',               '%s',                '%s',                '%s',              '%s',         '%s',        '%s',           '%s',        '%s',             '%s',            '%s',               '%s',            '%s',                     '%s',                 '%s',              '%s',           '%s',                   '%s',                    '%s',                  '%s',            '%s',       '%s',                '%s',             '%s',          %s,        '%s',          '%s',        '%s',         '%s',       '%s',               '%s',              '%s',                 '%s',              '%s',                '%s',               '%s',                  '%s',               '%s',                       '%s',                      '%s',                         '%s',                      '%s',                '%s',               '%s',                  '%s',               '%s',             '%s',            '%s',               '%s',            '%s',            '%s',           '%s',              '%s'); ", 
						dataId, fac_code, fac_desc, fac_sts, submission_purpose, credit_purpose_code, submission_sts_code, program_type_code, ttl_subm_amt, ttl_cap_amt, ttl_invest_amt, ttl_etc_amt, ttl_oth_subm_amt, ttl_oth_cap_amt, ttl_oth_invest_amt, ttl_oth_etc_amt, dpd_lte_7d_insuf_bal_cnt, dpd_lte_7d_other_cnt, dpd_gt_7d_14d_cnt, dpd_gt_14d_cnt, overdue_gt_col1_reason, closed_loan_last_2y_cnt, topup_fac_last_yr_cnt, has_ul_fac_code, ttl_ul_cnt, ttl_ul_gt_80pct_cnt, ttl_limit_ul_amt, ttl_os_ul_amt, is_active, modified_date, modified_by, created_date, created_by, ttl_exist_subm_amt, ttl_exist_cap_amt, ttl_exist_invest_amt, ttl_exist_etc_amt, ttl_change_subm_amt, ttl_change_cap_amt, ttl_change_invest_amt, ttl_change_etc_amt, ttl_oth_principal_subm_amt, ttl_oth_principal_cap_amt, ttl_oth_principal_invest_amt, ttl_oth_principal_etc_amt, ttl_oth_os_subm_amt, ttl_oth_os_cap_amt, ttl_oth_os_invest_amt, ttl_oth_os_etc_amt, ttl_idr_subm_amt, ttl_idr_cap_amt, ttl_idr_invest_amt, ttl_idr_etc_amt, ttl_os_subm_amt, ttl_os_cap_amt, ttl_os_invest_amt, ttl_os_etc_amt)
				};
			}
		}; 
		
		if (LobType.isSmes(lobType)) {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
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
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
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
}
