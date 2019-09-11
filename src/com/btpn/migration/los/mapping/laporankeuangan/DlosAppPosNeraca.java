package com.btpn.migration.los.mapping.laporankeuangan;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosAppPosNeraca {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String plobType) throws Exception{
				String dataId = mapper.getString("dataId");
				String fyear = DateTool.getYear(mapper.getString("fyear"));
				String lobCode = mapper.getString("lobCode");
				String lobType = mapper.getString("lobType");
				
				String totAktivaLancar = mapper.getString("totAktivaLancar", "0");
				String totAktivaTetap = mapper.getString("totAktivaTetap", "0");
				String totAktiva = mapper.getString("totAktiva", "0");
				String totHutangLancar = mapper.getString("totHutangLancar", "0");
				String totHutangJangkaPanjang = mapper.getString("totHutangJangkaPanjang", "0");
				String totModal = mapper.getString("totModal", "0");
				String totPassiva = mapper.getString("totPassiva", "0");
				
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = mapper.getString("createdDate"); ;
				String createdBy = mapper.getString("createdBy"); ;
				
				// Untuk Senitize column fyear= -1, sedangakan untuk proyeksi column fyear=-2
				String address = mapper.getAddress("fyear");
				if ("I9".equals(address)) { fyear = "-1"; }
				else if ("O9".equals(address)) { fyear = "-2"; }
				
				return new String[] {
						"insertAppNeraca",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_pos_neraca(dataId, fyear, lobCode, lobType, totAktivaLancar, totAktivaTetap, totAktiva, totHutangLancar, totHutangJangkaPanjang, totModal, totPassiva, modifiedDate, modifiedBy, createdDate, createdBy) "+
						"VALUES(%s, %s, '%s', '%s', %s, %s, %s, %s, %s, %s, %s, '%s', '%s', %s, '%s');",
						dataId, fyear, lobCode, lobType, totAktivaLancar, totAktivaTetap, totAktiva, totHutangLancar, totHutangJangkaPanjang, totModal, totPassiva, modifiedDate, modifiedBy, createdDate, createdBy
					)
				};
			}
		};
		
		String[] columns = new String[] {"C", "E", "G", "I", "O"};
		for (String column : columns) {
			SpecRow header = SpecRow.get(null).setSheet(Sheet.AnalisaLapKue).xls("appId", "B4").xls("fyear", column+"9").fix("lobCode", "01").fix("lobType", "SMES").fix("dataId", "-1").fix("createdDate", "CURRENT_TIMESTAMP").fix("createdBy", MIGRATION);
			
			specRows.add(SpecRow.get(insert, header).setSheet(Sheet.AnalisaLapKue)
					.xls("totAktivaLancar", column+"30")
					.xls("totAktivaTetap", column+"39")
					.xls("totAktiva", column+"42")
					.xls("totHutangLancar", column+"50")
					.xls("totHutangJangkaPanjang", column+"55")
					.xls("totModal", column+"62")
					.xls("totPassiva", column+"63")
					
			);
		}
	}
}
