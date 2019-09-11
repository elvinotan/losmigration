package com.btpn.migration.los.mapping.laporankeuangan;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;

public class DlosAppPosLaporan {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {

			@Override
			public String[] insert(Mapper mapper, Store store, String pLobType) {
				String dataId = mapper.getString("dataId");
				String lobCode = mapper.getString("lobCode");
				String lobType = mapper.getString("lobType");
				
				String grossUp = mapper.getString("grossUp", "0");
				String cpltd = mapper.getString("cpltd", "0");
				String kebutuhanInvestasi = mapper.getString("kebutuhanInvestasi", "0");
				String penjelasanKebutuhanModalKerja = (mapper.getString("penjelasanKebutuhanModalKerja1").trim() +" "+ mapper.getString("penjelasanKebutuhanModalKerja2").trim() +" "+ mapper.getString("penjelasanKebutuhanModalKerja3").trim()).trim();
				String penjelasanKebutuhanInvestasi = (mapper.getString("penjelasanKebutuhanInvestasi1").trim() +" "+ mapper.getString("penjelasanKebutuhanInvestasi2").trim() +" "+ mapper.getString("penjelasanKebutuhanInvestasi3").trim() +" "+ mapper.getString("penjelasanKebutuhanInvestasi4")).trim();
				String debiturAjukanKreditTepat = mapper.getString("debiturAjukanKreditTepat");
				String informasiKeuanganHistoris = (mapper.getString("informasiKeuanganHistoris1").trim() +" "+ mapper.getString("informasiKeuanganHistoris2").trim() +" "+ mapper.getString("informasiKeuanganHistoris3").trim() +" "+ mapper.getString("informasiKeuanganHistoris4").trim() +" "+ mapper.getString("informasiKeuanganHistoris5").trim() +" "+ mapper.getString("informasiKeuanganHistoris6")).trim();
				String asumsiProyeksiKeuangan = (mapper.getString("asumsiProyeksiKeuangan1") .trim() +" "+  mapper.getString("asumsiProyeksiKeuangan2") .trim() +" "+  mapper.getString("asumsiProyeksiKeuangan3") .trim() +" "+  mapper.getString("asumsiProyeksiKeuangan4") .trim() +" "+  mapper.getString("asumsiProyeksiKeuangan5") .trim() +" "+  mapper.getString("asumsiProyeksiKeuangan6")).trim();
				
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = mapper.getString("createdDate"); ;
				String createdBy = mapper.getString("createdBy"); ;
				
				return new String [] {
					"insertAppLaporan",
				
					String.format(
						"INSERT INTO dlos_core.dlos_app_pos_laporan (dataId, lobCode, lobType, grossUp, cpltd, kebutuhanInvestasi, penjelasanKebutuhanModalKerja, penjelasanKebutuhanInvestasi, debiturAjukanKreditTepat, informasiKeuanganHistoris, asumsiProyeksiKeuangan, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(%s, '%s', '%s', %s, %s, %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s');",
						dataId, lobCode, lobType, grossUp, cpltd, kebutuhanInvestasi, penjelasanKebutuhanModalKerja, penjelasanKebutuhanInvestasi, debiturAjukanKreditTepat, informasiKeuanganHistoris, asumsiProyeksiKeuangan, modifiedDate, modifiedBy, createdDate, createdBy
					)
				};
			}
		};
		
		SpecRow header = SpecRow.get(null).setSheet(Sheet.AnalisaLapKue).xls("appId", "B4").fix("lobCode", "01").fix("lobType", "SMES").fix("dataId", "-1").fix("createdDate", "CURRENT_TIMESTAMP").fix("createdBy", MIGRATION);
		
		specRows.add(SpecRow.get(insert, header).setSheet(Sheet.AnalisaLapKue)
				.xls("grossUp", "I8")
				.xls("cpltd", "C65")
				.xls("kebutuhanInvestasi", "E113")
				.xls("penjelasanKebutuhanModalKerja1", "A107")
				.xls("penjelasanKebutuhanModalKerja2", "A108")
				.xls("penjelasanKebutuhanModalKerja3", "A109")
				.xls("penjelasanKebutuhanInvestasi1", "A117")
				.xls("penjelasanKebutuhanInvestasi2", "A118")
				.xls("penjelasanKebutuhanInvestasi3", "A119")
				.xls("penjelasanKebutuhanInvestasi4", "A120")
				.xls("debiturAjukanKreditTepat", "D122")
				.xls("informasiKeuanganHistoris1", "A149")
				.xls("informasiKeuanganHistoris2", "A150")
				.xls("informasiKeuanganHistoris3", "A151")
				.xls("informasiKeuanganHistoris4", "A152")
				.xls("informasiKeuanganHistoris5", "A153")
				.xls("informasiKeuanganHistoris6", "A154")
				.xls("asumsiProyeksiKeuangan1", "A158")
				.xls("asumsiProyeksiKeuangan2", "A159")
				.xls("asumsiProyeksiKeuangan3", "A160")
				.xls("asumsiProyeksiKeuangan4", "A161")
				.xls("asumsiProyeksiKeuangan5", "A162")
				.xls("asumsiProyeksiKeuangan6", "A163")
				);
	}
}
