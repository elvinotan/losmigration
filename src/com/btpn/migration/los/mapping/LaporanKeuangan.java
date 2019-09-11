package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.mapping.laporankeuangan.DlosAppPos;
import com.btpn.migration.los.mapping.laporankeuangan.DlosAppPosLabarugi;
import com.btpn.migration.los.mapping.laporankeuangan.DlosAppPosLaporan;
import com.btpn.migration.los.mapping.laporankeuangan.DlosAppPosNeraca;

public class LaporanKeuangan implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	// Mencakup
	// dlos_app_pos
	// dlos_app_pos_neraca
	// dlos_app_pos_labarugi
	// dlos_app_pos_laporan
	
	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_pos_laporan where createdBy ='%s';", MIGRATION), 
			String.format("delete from dlos_app_pos_labarugi where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_pos_neraca where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_pos where createdBy ='%s';", MIGRATION)
		};
	}
	
	@Override
	public void initMapping(String filename, String lobType) {
		DlosAppPos.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppPosNeraca.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppPosLabarugi.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppPosLaporan.migrate(filename, lobType, MIGRATION, specRows);
	}

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}