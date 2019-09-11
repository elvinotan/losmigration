package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.mapping.tujuandanfasilitas.DlosAppFacility;
import com.btpn.migration.los.mapping.tujuandanfasilitas.DlosAppFacilityDtl;
import com.btpn.migration.los.mapping.tujuandanfasilitas.DlosAppFacilityOth;

public class TujuanDanFasilitas implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// Mencakup
	// dlos_app_facility (Master)
	// dlos_app_facility_dtl (*)
	// dlos_app_facility_oth (*)
	
	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_facility_oth where created_by ='%s';", MIGRATION),
			String.format("delete from dlos_app_facility_dtl where created_by ='%s';", MIGRATION),
			String.format("delete from dlos_app_facility where created_by ='%s';", MIGRATION),
		};
	}

	@Override
	public void initMapping(String filename, String lobType) {
		DlosAppFacility.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppFacilityDtl.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppFacilityOth.migrate(filename, lobType, MIGRATION, specRows);
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}