package com.btpn.migration.los.mapping.laporankeuangan;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppPosLabarugi {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
			
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String pLobType) throws Exception{
				String dataId = mapper.getString("dataId");
				String fyear = DateTool.getYear(mapper.getString("fyear"));
				String lobCode = mapper.getString("lobCode");
				String lobType = mapper.getString("lobType");
				
				String labaKotor = mapper.getString("labaKotor", "0");
				String totBiayaUsaha = mapper.getString("totBiayaUsaha", "0");
				String labaRugiUsah = mapper.getString("labaRugiUsah", "0");
				String labaRugiSebelumPajak = mapper.getString("labaRugiSebelumPajak", "0");
				String labaBersih = mapper.getString("labaBersih", "0");
				String reForPeriode = mapper.getString("reForPeriode", "0");
				
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = mapper.getString("createdDate");
				String createdBy = mapper.getString("createdBy");
				
				// Untuk Senitize column fyear= -1, sedangakan untuk proyeksi column fyear=-2
				String address = mapper.getAddress("fyear");
				if ("I9".equals(address)) { fyear = "-1"; }
				else if ("O9".equals(address)) { fyear = "-2"; }
				
				return new String[] {
						"insertAppLabaRugi",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_pos_labarugi (dataId, fyear, lobCode, lobType, labaKotor, totBiayaUsaha, labaRugiUsah, labaRugiSebelumPajak, labaBersih, reForPeriode, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(%s, %s, '%s', '%s', %s, %s, %s, %s, %s, %s, '%s', '%s', %s, '%s');", 
						dataId, fyear, lobCode, lobType, labaKotor, totBiayaUsaha, labaRugiUsah, labaRugiSebelumPajak, labaBersih, reForPeriode, modifiedDate, modifiedBy, createdDate, createdBy
					)
				};
			}
		};
		
		String[] columns = new String[] {"C", "E", "G", "I", "O"};
		for (String column : columns) {
			SpecRow header = SpecRow.get(null).setSheet(Sheet.AnalisaLapKue).xls("appId", "B4").xls("fyear", column+"9").fix("lobCode", "01").fix("lobType", "SMES").fix("dataId", "-1").fix("createdDate", "CURRENT_TIMESTAMP").fix("createdBy", MIGRATION);

			specRows.add(SpecRow.get(insert, header).setSheet(Sheet.AnalisaLapKue)
					.xls("labaKotor", column+"71")
					.xls("totBiayaUsaha", column+"77")
					.xls("labaRugiUsah", column+"78")
					.xls("labaRugiSebelumPajak", column+"82")
					.xls("labaBersih", column+"85")
					.xls("reForPeriode", column+"87"));
		}
	}
}
