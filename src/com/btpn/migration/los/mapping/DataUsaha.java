package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.mapping.datausaha.DlosAppBusiness;
import com.btpn.migration.los.mapping.datausaha.DlosAppBuyChecking;
import com.btpn.migration.los.mapping.datausaha.DlosAppBuyPayment;
import com.btpn.migration.los.mapping.datausaha.DlosAppCovenantHeader;
import com.btpn.migration.los.mapping.datausaha.DlosAppDrawdownConditionHeader;
import com.btpn.migration.los.mapping.datausaha.DlosAppOtherConditionExtHeader;
import com.btpn.migration.los.mapping.datausaha.DlosAppOtherConditionIntHeader;
import com.btpn.migration.los.mapping.datausaha.DlosAppPrecedentHeader;
import com.btpn.migration.los.mapping.datausaha.DlosAppSupChecking;
import com.btpn.migration.los.mapping.datausaha.DlosAppSupPayments;

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
	public void initMapping(String filename, String lobType) {
		DlosAppBusiness.migrate(filename, lobType, MIGRATION, specRows);
//		DlosAppBusinessOth.migrate(filename, lobType, lobType, specRows); Tidak ada di bagian mapping
//		DlosAppBusinessNeighbour.migrate(filename, lobType, MIGRATION, specRows); Tidak ada di bagian mapping
		DlosAppSupChecking.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppSupPayments.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppBuyChecking.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppBuyPayment.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppCovenantHeader.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppPrecedentHeader.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppDrawdownConditionHeader.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppOtherConditionExtHeader.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppOtherConditionIntHeader.migrate(filename, lobType, MIGRATION, specRows);
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}