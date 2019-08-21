package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class LaporanKeuanganSMES implements Mapping {
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_pos where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_pos_neraca where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_pos_labarugi where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_pos_laporan where createdBy ='%s';", MIGRATION), };
	}
	
	@Override
	public void initMapping(String lobType) {
		String[] columns = new String[] {"C", "E", "G", "I", "O"};
		
		IActions insertAppPos = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String plobType) throws Exception{				
				String dataId = mapper.getString("dataId");
				String fyear = DateTool.getYear(mapper.getString("fyear"));
				String lobCode = mapper.getString("lobCode");
				String lobType = mapper.getString("lobType");
				
				String pos = mapper.getString("pos");
				String amount = mapper.getString("amount", "0");
				
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				String createdDate = mapper.getString("createdDate"); ;
				String createdBy = mapper.getString("createdBy"); ;
				String status = mapper.getString("status");
				String million = null;
				
				// Menyimpan Tahun Laporan Keuangan layaknya pos
				if ("tahun_laporan_keuangan".equals(pos)) {
					amount = fyear;
				}
				
				// Untuk post status_laporan_keuangan column amount di set jadi null
				if ("status_laporan_keuangan".equals(pos)) {
					amount = null;
					status = store.getLookupByDescription(Lookup.LaporanKeuangan, status).getKey();
				}
				
				// Untuk Senitize column fyear= -1, sedangakan untuk proyeksi column fyear=-2
				String address = mapper.getAddress("fyear");
				if ("I9".equals(address)) { fyear = "-1"; }
				else if ("O9".equals(address)) { fyear = "-2"; }
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_pos (dataId, fyear, lobCode, lobType, pos, amount, modifiedDate, modifiedBy, createdDate, createdBy, status, million) "+
						"VALUES(%s, %s, '%s', '%s', '%s', %s, %s, '%s', %s, '%s', '%s', %s);", 
						dataId, fyear, lobCode, lobType, pos, amount, modifiedDate, modifiedBy, createdDate, createdBy, status, million 
				);
			}
		};
		
		IActions insertAppNeraca = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String plobType) throws Exception{
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
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_pos_neraca(dataId, fyear, lobCode, lobType, totAktivaLancar, totAktivaTetap, totAktiva, totHutangLancar, totHutangJangkaPanjang, totModal, totPassiva, modifiedDate, modifiedBy, createdDate, createdBy) "+
						"VALUES(%s, %s, '%s', '%s', %s, %s, %s, %s, %s, %s, %s, '%s', '%s', %s, '%s');",
						dataId, fyear, lobCode, lobType, totAktivaLancar, totAktivaTetap, totAktiva, totHutangLancar, totHutangJangkaPanjang, totModal, totPassiva, modifiedDate, modifiedBy, createdDate, createdBy
				);
			}
		};
		
		IActions insertAppLabaRugi = new IActions() {
			
			@Override
			public String insert(Mapper mapper, Store store, String pLobType) throws Exception{
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
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_pos_labarugi (dataId, fyear, lobCode, lobType, labaKotor, totBiayaUsaha, labaRugiUsah, labaRugiSebelumPajak, labaBersih, reForPeriode, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(%s, %s, '%s', '%s', %s, %s, %s, %s, %s, %s, '%s', '%s', %s, '%s');", 
						dataId, fyear, lobCode, lobType, labaKotor, totBiayaUsaha, labaRugiUsah, labaRugiSebelumPajak, labaBersih, reForPeriode, modifiedDate, modifiedBy, createdDate, createdBy
				);
			}
		};
		
		for (String column : columns) {
			SpecRow header = SpecRow.get(null).setSheet(Sheet.AnalisaLapKue).xls("appId", "B4").xls("fyear", column+"9").fix("lobCode", "01").fix("lobType", "SMES").fix("dataId", "-1").fix("createdDate", "CURRENT_TIMESTAMP").fix("createdBy", MIGRATION);
	
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "tahun_laporan_keuangan").xls("amount", column+"9").pk("pk_tahun_laporan_keuangan"+column));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "status_laporan_keuangan").xls("status", column+"7"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lancar_kas").xls("amount", column+"25"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lancar_piutang").xls("amount", column+"26"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lancar_persediaan").xls("amount", column+"27"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lancar_biaya_dibayar_dimuka").xls("amount", column+"28"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lancar_aktiva_lancar_lainnya").xls("amount", column+"29"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_tanah").xls("amount", column+"32"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_tanah_dan_bangunan").xls("amount", column+"33"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_akumulasi_penyusutan_tanah_dan_bangunan").xls("amount", column+"34"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_kendaraan").xls("amount", column+"35"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_akumulasi_penyusutan_kendaraan").xls("amount", column+"36"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_mesin_peralatan_inventaris").xls("amount", column+"37"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_tetap_akumulasi_penyusutan_mesin_peralatan_inventaris").xls("amount", column+"38"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_aktiva_tidak_berwujud").xls("amount", column+"40"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_aktiva_lainnya").xls("amount", column+"41"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_lancar_hutang_kurang_satu_tahun").xls("amount", column+"45"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_lancar_hutang_cpltd").xls("amount", column+"46"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_lancar_hutang_hutang_dagang").xls("amount", column+"47"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_lancar_hutang_biaya_yang_masih_harus_dibayar").xls("amount", column+"48"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_lancar_hutang_lainnya").xls("amount", column+"49"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_jangka_panjang_hutang_bank_lebih_satu_tahun").xls("amount", column+"52"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_jangka_panjang_hutang_jangka_panjang_bukan_bank").xls("amount", column+"53"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_hutang_jangka_panjang_lainnya").xls("amount", column+"54"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_modal_modal_disetor").xls("amount", column+"57"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_modal_saham").xls("amount", column+"58"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_modal_lainnya").xls("amount", column+"59"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_modal_laba_ditahan").xls("amount", column+"60"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "neraca_passiva_modal_laba_tahun_berjalan").xls("amount", column+"61"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_penjualan").xls("amount", column+"68"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_harga_pokok_penjualan").xls("amount", column+"69"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_depresiasi_dan_amortisasi_hpp").xls("amount", column+"70"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_oprasional").xls("amount", column+"72"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_penjulan_dan_administrasi").xls("amount", column+"73"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_depresiasi_dan_amortisasi").xls("amount", column+"74"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_tenaga_kerja_tidak_langsung").xls("amount", column+"75"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_biaya_lainnya_terkait_usaha").xls("amount", column+"76"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_bunga_seluruh_fasilitas").xls("amount", column+"79"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_pendapatan_lainnya").xls("amount", column+"80"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_beban_lainnya").xls("amount", column+"81"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_pajak").xls("amount", column+"83"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_dividen_yang_dibagikan").xls("amount", column+"84"));
			specRows.add(SpecRow.get(insertAppPos,header).setSheet(Sheet.AnalisaLapKue).fix("pos", "labarugi_re_adjust").xls("amount", column+"86"));
			
			specRows.add(SpecRow.get(insertAppNeraca,header).setSheet(Sheet.AnalisaLapKue)
					.xls("totAktivaLancar", column+"30")
					.xls("totAktivaTetap", column+"39")
					.xls("totAktiva", column+"42")
					.xls("totHutangLancar", column+"50")
					.xls("totHutangJangkaPanjang", column+"55")
					.xls("totModal", column+"62")
					.xls("totPassiva", column+"63")
					
			);
			
			specRows.add(SpecRow.get(insertAppLabaRugi,header).setSheet(Sheet.AnalisaLapKue)
					.xls("labaKotor", column+"71")
					.xls("totBiayaUsaha", column+"77")
					.xls("labaRugiUsah", column+"78")
					.xls("labaRugiSebelumPajak", column+"82")
					.xls("labaBersih", column+"85")
					.xls("reForPeriode", column+"87"));
		
		}
		
		IActions insertAppLaporan = new IActions() {

			@Override
			public String insert(Mapper mapper, Store store, String pLobType) {
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
				
				return String.format(
						"INSERT INTO dlos_core.dlos_app_pos_laporan (dataId, lobCode, lobType, grossUp, cpltd, kebutuhanInvestasi, penjelasanKebutuhanModalKerja, penjelasanKebutuhanInvestasi, debiturAjukanKreditTepat, informasiKeuanganHistoris, asumsiProyeksiKeuangan, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(%s, '%s', '%s', %s, %s, %s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, '%s');",
						dataId, lobCode, lobType, grossUp, cpltd, kebutuhanInvestasi, penjelasanKebutuhanModalKerja, penjelasanKebutuhanInvestasi, debiturAjukanKreditTepat, informasiKeuanganHistoris, asumsiProyeksiKeuangan, modifiedDate, modifiedBy, createdDate, createdBy
				);
			}
		};
		
		SpecRow headerLaporan = SpecRow.get(null).setSheet(Sheet.AnalisaLapKue).xls("appId", "B4").fix("lobCode", "01").fix("lobType", "SMES").fix("dataId", "-1").fix("createdDate", "CURRENT_TIMESTAMP").fix("createdBy", MIGRATION);
		
		specRows.add(SpecRow.get(insertAppLaporan,headerLaporan).setSheet(Sheet.AnalisaLapKue)
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

	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		//log.debug("getSpecRows");
		
		return this.specRows;
	}

}


