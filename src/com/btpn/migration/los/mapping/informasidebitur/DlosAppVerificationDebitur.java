package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppVerificationDebitur {
	
	//ALTER TABLE dlos_core.dlos_app_verification_debitur MODIFY COLUMN notes TEXT NULL;
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String dataId = store.getString("dataId");
				
				String is_bi_list = mapper.getString("is_bi_list");
				Lookup lis_bi_list = store.getLookupByDescription(Lookup.YesNo, is_bi_list);
				is_bi_list = (lis_bi_list == null) ? mapper.logMapperProblem("migrasiDlosAppVerificationDebitur") : lis_bi_list.getKey();
						
				String bi_check_last_3mos = mapper.getString("bi_check_last_3mos");
				Lookup lbi_check_last_3mos = store.getLookupByDescription(Lookup.YesNo, bi_check_last_3mos);
				bi_check_last_3mos = (lbi_check_last_3mos == null) ? mapper.logMapperProblem("migrasiDlosAppVerificationDebitur") : lbi_check_last_3mos.getKey();
				
				String is_business_nonIndustry = mapper.getString("is_business_nonIndustry");
				Lookup lis_business_nonIndustry = store.getLookupByDescription(Lookup.YesNo, is_business_nonIndustry);
				is_business_nonIndustry = (lis_business_nonIndustry == null) ? mapper.logMapperProblem("migrasiDlosAppVerificationDebitur") : lis_business_nonIndustry.getKey();
				
				String positive_check = mapper.getString("positive_check");
				Lookup lpositive_check = store.getLookupByDescription(Lookup.YesNo, positive_check);
				positive_check = (lpositive_check == null) ? mapper.logMapperProblem("migrasiDlosAppVerificationDebitur") : lpositive_check.getKey();
				
				String is_business_min_2years = mapper.getString("is_business_min_2years");
				Lookup lis_business_min_2years = store.getLookupByDescription(Lookup.YesNo, is_business_min_2years);
				is_business_min_2years = (lis_business_min_2years == null) ? mapper.logMapperProblem("migrasiDlosAppVerificationDebitur") : lis_business_min_2years.getKey();
				
				String notes = StringTool.combine(mapper.getString("notes0"), mapper.getString("notes1"), mapper.getString("notes2"), mapper.getString("notes3"));
				notes = notes.replaceAll("<< Berikan penjelasan, jika terdapat informasi tambahan terkait kredibilitas debitur>>", "").trim();
				
				String is_active = "1";				
				
				String created_date = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(created_date)) created_date = "2018-05-26 00:00:00";
				created_date = DateTool.getYMD(created_date);
				
				String created_by = MIGRATION;
				String modified_date = null;
				String modified_by = mapper.getString("appId");
				
				return new String[] {
						"migrasiDlosAppVerificationDebitur",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_verification_debitur (dataId, is_bi_list, bi_check_last_3mos, is_business_nonIndustry, positive_check, notes, is_active, created_date, created_by, modified_date, modified_by, is_business_min_2years) "+
						"VALUES(                                             '%s',    '%s',       '%s',               '%s',                    '%s',           '%s',  %s,        '%s',         '%s',       '%s',          '%s',        '%s') ",
						dataId, is_bi_list, bi_check_last_3mos, is_business_nonIndustry, positive_check, notes, is_active, created_date, created_by, modified_date, modified_by, is_business_min_2years)
				};
			}
		};
		
		if (LobType.isSmes(lobType)) {
			if (StringTool.inArray(filename, "182. Gunawan Anggrianto - Turun Plafond.xlsx", "153. Harris (Revisi SID).xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "F138") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "F139") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "F141") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "F142") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "F140") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A144") // Catatan
						.xls("notes1", "A145")
						.xls("notes2", "A146")
						.xls("notes3", "A147"));
			}else if (StringTool.inArray(filename, "037. Benyamin Sirapanji - Perpanjangan & Tambahan.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H141") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H142") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H144") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H145") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H143") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A147") // Catatan
						.xls("notes1", "A148")
						.xls("notes2", "A149")
						.xls("notes3", "A150"));
			}else if (StringTool.inArray(filename, "132. PT. Mitra Mulia Bangun Putera.xls", "263. CV. Mulia Sejahtera - BG.xls", "263. Nathan Agus Soegiarto Group.xls", "040. Nathan Agus Soegiarto.xls", "165. PT. Bintang Citra Kasih.xls", "165. CV Central UV.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H139") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H140") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H142") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H143") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H141") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A145") // Catatan
						.xls("notes1", "A146")
						.xls("notes2", "A147")
						.xls("notes3", "A148"));
			}else if (StringTool.inArray(filename, "119. PT Karya Bukit Mandiri.xls", "234. Winyoto.xls", "245. I Wayan Jana.xls", "069. I Wayan Jana.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H148") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H149") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H151") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H152") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H150") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A154") // Catatan
						.xls("notes1", "A155")
						.xls("notes2", "A156")
						.xls("notes3", "A157"));
			}else if (StringTool.inArray(filename, "033.I. Edy Laudy - Perpanjangan & Tambahan.xls", "033. Edy Laudy - Perpanjangan & Tambahan.xls", "098. PT. Palapa Energi Indonesia.xls", "136. PT Sumber Es Makmur.xls", "098. PT. Palapa Energi Indonesia.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H143") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H144") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H146") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H147") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H145") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A149") // Catatan
						.xls("notes1", "A150")
						.xls("notes2", "A151")
						.xls("notes3", "A152"));
			}else if (StringTool.inArray(filename, "053. PT. Neoplast Packaging - 2018.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H144") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H145") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H147") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H148") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H146") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A150") // Catatan
						.xls("notes1", "A151")
						.xls("notes2", "A152")
						.xls("notes3", "A153"));
			}else {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "H138") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "H139") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "H141") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "H142") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "H140") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A144") // Catatan
						.xls("notes1", "A145")
						.xls("notes2", "A146")
						.xls("notes3", "A147"));
			}
		}
		
		if (LobType.isSmel(lobType)) {
			if (StringTool.inArray(filename, "227. Go Ronny Group.xls", "227. Go Ronny - Perpanjangan.xls", "129. Go Ronny - Tambahan.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G138") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G139") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G141") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G142") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G140") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A144") //Catatan
						.xls("notes1", "A145")
						.xls("notes2", "A146")
						.xls("notes3", "A147"));
			}else if (StringTool.inArray(filename, "081. CV. Mega Jasa - Tukar Jaminan.xls", "054. CV. Mega Jasa.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G140") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G141") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G143") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G144") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G142") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A146") //Catatan
						.xls("notes1", "A147")
						.xls("notes2", "A148")
						.xls("notes3", "A149"));
			}else if (StringTool.inArray(filename, "262. Antonius Yogipranata Group - 2.xls", "113.II. PT. Bintang Nusantara Linda - Regularisasi 2.xls", "113.I. PT. Bintang Nusantara Linda.xls", "113. PT. Bintang Nusantara Linda.xls", "280.I. PT Cipta Aneka Pangan Prima.xls", "280. PT Cipta Aneka Pangan Prima.xls", "262. CV. Sinar Sejahtera.xls", "262. CV Sinar Gemilang.xls", "262. Antonius Yogipranata Group – 2.xls", "262. Antonius Yogipranata Group.xls", "0547-BDG Kopo 2- Ricky Group - Renewal Konsolidasi RAC.xls", "0547-BDG Kopo 2- Ricky Group - Konsolidasi (MKK).xls", "0547-BDG Kopo 2- Ricky  - Renewal.xls", "0547-BDG Kopo 2- PT Bintang Mas Indoplast - Renewal.xls", "0547-BDG Kopo 2- CV Golden Indo Plastic - NEW.xls", "0547-BDG Kopo 2- CV Bintang Terang - New.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G141") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G142") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G144") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G145") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G143") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A147") //Catatan
						.xls("notes1", "A148")
						.xls("notes2", "A149")
						.xls("notes3", "A150"));
			}else if (StringTool.inArray(filename, "271. Suwandi Group - 2.xls", "271.II. Suwandi Group.xls", "271.I. Suwandi Group.xls", "271. Suwandi Group.xls", "271. CV. Wijaya Bersaudara.xls", "271. CV. Trinity Karya Mandiri.xls", "271. CV. Tri Ratu Tekstil.xls", "271. CV. Tri Mega Jaya.xls", "271. CV. Subur Triratutex.xls", "271. CV. Andalan Wijaya.xls", "012. PT. Mewah Niagajaya - 2.xls", "012. PT. Mewah Niagajaya.xls", "206.II. PT Lematang.xls", "206.III PT Lematang.xls", "206.IV PT Lematang.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G143") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G144") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G146") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G147") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G145") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A149") //Catatan
						.xls("notes1", "A150")
						.xls("notes2", "A151")
						.xls("notes3", "A152"));
			} else if (StringTool.inArray(filename, "099. PT. BPR Utomo Manunggal Sejahtera.xls", "199 PT. BPR Nusamba Singaparna.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "F128") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "F126") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "F129") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "F127") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A131") //Catatan
						.xls("notes1", "A132")
						.xls("notes2", "A133")
						.xls("notes3", "A134"));
			} else if (StringTool.inArray(filename, "264. Aarti Jaya Group.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G149") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G150") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G152") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G153") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G151") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A155") //Catatan
						.xls("notes1", "A156")
						.xls("notes2", "A157")
						.xls("notes3", "A158"));
			}else if (StringTool.inArray(filename, "114. CV. Mega Jasa.xls", "114.I. CV. Mega Jasa.xls", "114.II CV. Mega Jasa.xls")) {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G150") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G151") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G153") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G154") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G152") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A156") //Catatan
						.xls("notes1", "A157")
						.xls("notes2", "A158")
						.xls("notes3", "A159"));
			} else {
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("is_bi_list", "G136") //Debitur tidak termasuk dalam Daftar Hitam BI (DHBI) / BI Blacklist
						.xls("bi_check_last_3mos", "G137") //BI Checking dengan kolektabilitas 1 dalam 3 bulan terakhir
						.xls("is_business_nonIndustry", "G139") //Usaha debitur tidak termasuk dalam Non Target Industri
						.xls("positive_check", "G140") //Hasil positif berdasarkan hasil checking
						.xls("is_business_min_2years", "G138") //Usaha debitur (termasuk didalamnnya pengalaman key person) telah berjalan minimum 2 tahun, kecuali dapat dibuktikan bahwa usaha debitur yang baru dibentuk adalah pengembangan dari usaha sebelumnya
						.xls("notes0", "A142") //Catatan
						.xls("notes1", "A143")
						.xls("notes2", "A144")
						.xls("notes3", "A145"));
			}
		}
	}
}
