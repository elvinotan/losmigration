package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.CommonService;
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

public class DlosAppManagement {
	
	//ALTER TABLE dlos_core.dlos_app_management MODIFY COLUMN `managementName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_management MODIFY COLUMN `idNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;

	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		IActions insert = new IActions() {

			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String managementName = mapper.getString("managementName");
				if (StringTool.isEmptyTag(managementName)) return null; // ini artinya  datanya kosong dan jgn di process querynya
				
				String managementPosition = mapper.getString("managementPosition");
				if (StringTool.isEmptyTag(managementPosition)) { return null; 
				}else {
					System.out.println("oooo["+managementPosition+"]");
					if ("70 - Lainnya (Bukan Pemilik)".equals(managementPosition)) managementPosition = "69 - Lainnya (Bukan Pemilik)";
					if ("\\".equals(managementPosition)) managementPosition = null;
					Lookup lmanagementPosition = store.getLookupByDescription(Lookup.Position, managementPosition);
					managementPosition = (lmanagementPosition == null) ? mapper.logMapperProblem("migrasiDlosAppManagement") : lmanagementPosition.getKey();
				}
				
				String idCode = mapper.getString("idCode");
				if (StringTool.isEmptyTag(idCode)) { idCode = null;
				}else {
					if ("Paspor".equals(idCode)) idCode = "Passport"; //078. PT. Multi Karya Engineering.xls, 202. PT. Jaya Mega Mandiri Bangunan.xls
					if ("PASPORT Republik Federal German".equals(idCode)) idCode = "Passport"; //091. PT Woodexindo.xls
					
					Lookup lIdCode = store.getLookupByDescription(Lookup.IDCard, idCode); 
					idCode = (lIdCode == null) ? mapper.logMapperProblem("migrasiDlosAppManagement") : lIdCode.getKey();
				}
						
				String idNumber =  mapper.getString("idNumber");
				
				String sharePercentage = mapper.getString("sharePercentage");
				if (sharePercentage != null) {
					if ("96% Pemilik di PT Mitra Maju Perkasa".equals(sharePercentage)) { sharePercentage = "96"; }
					if ("Jl. Babarsari TB XI/20-A Tambakbayan Rt/Rw. 013/004".equals(sharePercentage)) { sharePercentage = null; }
					if ("Tidak tercantum di akta".equals(sharePercentage)) { sharePercentage = null; }
					if ("10\\%".equals(sharePercentage)) { sharePercentage = "10"; }
					
					sharePercentage = NumberTool.handlePercentage(sharePercentage);	
				}				
				
				String managementAddress = mapper.getString("managementAddress");
				
				String datiII = store.getDati2Mapping(mapper.getString("datiII"));
				CommonService csDati2 = store.getCommonByDescription(CommonService.TYPE_CITY, datiII);
				datiII = (csDati2 == null) ? mapper.logMapperProblem("migrasiDlosAppManagement") : csDati2.getCode();
				
				String NPWPNumber = mapper.getString("NPWPNumber");
				
				String age = mapper.getString("age");
				if (StringTool.isEmptyTag(age)) { age = null; 
				}else {
					if ("37 Tahun".equals(age)) age = "37";
					if ("33 Tahun".equals(age)) age = "33";
				}
				
				String experienceInYears = mapper.getString("experienceInYears");
				if (StringTool.isEmptyTag(experienceInYears)) { 
					experienceInYears = null; 
				}else {
					if (">5".equals(experienceInYears)) experienceInYears = "5";
					if ("> 10 thn".equals(experienceInYears)) experienceInYears = "10";
					if ("28 thn".equals(experienceInYears)) experienceInYears = "28";
					if ("26 thn".equals(experienceInYears)) experienceInYears = "26";
				}
				
				String joinedSinceYears = mapper.getString("joinedSinceYears");
				if (StringTool.isEmptyTag(joinedSinceYears)) { 
					joinedSinceYears = null; 
				}else {
					joinedSinceYears = joinedSinceYears.replaceAll("-an", "");
					if ("2011-07-14 00:00:00:0000".equals(joinedSinceYears)) { joinedSinceYears = "2011"; }
					if ("1980 (CV Kutawaringin dan CV Fitaloka)".equals(joinedSinceYears)) { joinedSinceYears = "1980"; }
					if ("2004 (CV Kutawaringin)".equals(joinedSinceYears)) { joinedSinceYears = "2004"; }
				}
				
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
				String createdBy = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppManagement",
				
						String.format( 	
						"INSERT INTO dlos_core.dlos_app_management (managementName, managementPosition, idCode, idNumber, sharePercentage, managementAddress, datiII, NPWPNumber, age, experienceInYears, joinedSinceYears, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                    '%s',           '%s',               '%s',   '%s',     '%s',            '%s',              '%s',   '%s',       '%s','%s',              '%s',             '%s',   %s,     '%s',         '%s',       '%s',        '%s');", 
						managementName, managementPosition, idCode, idNumber, sharePercentage, managementAddress, datiII, NPWPNumber, age, experienceInYears, joinedSinceYears, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
			}			
		};
		
		if (LobType.isSmes(lobType)) {
			for (int i = 0; i < 11; i++) {
				int inc = -1;

				if (StringTool.inArray(filename, "033.I. Edy Laudy - Perpanjangan & Tambahan.xls", "033. Edy Laudy - Perpanjangan & Tambahan.xls", "098. PT. Palapa Energi Indonesia.xls", "136. PT Sumber Es Makmur.xls")) {
					inc = 127;
				}else if (StringTool.inArray(filename, "119. PT Karya Bukit Mandiri.xls", "234. Winyoto.xls", "069. I Wayan Jana.xls", "245. I Wayan Jana.xls")) {
					inc = 132;
				}else if (StringTool.inArray(filename, "132. PT. Mitra Mulia Bangun Putera.xls", "263. CV. Mulia Sejahtera - BG.xls", "263. Nathan Agus Soegiarto Group.xls", "040. Nathan Agus Soegiarto.xls", "165. CV Central UV.xls", "165. PT. Bintang Citra Kasih.xls")) {
					inc = 123;
				}else if (StringTool.inArray(filename, "037. Benyamin Sirapanji - Perpanjangan & Tambahan.xls")) {
					inc = 125;
				}else if (StringTool.inArray(filename, "053. PT. Neoplast Packaging - 2018.xls")) {
					inc = 128;	
				}else {
					inc = 122;
				}
				
				inc = inc + i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("age", "L"+inc)
						.xls("datiII", "H"+inc)
						.xls("experienceInYears", "M"+inc)
						.xls("idCode", "B"+inc)
						.xls("idNumber", "C"+inc)
						.xls("joinedSinceYears", "N"+inc)
						.xls("managementAddress", "F"+inc)
						.xls("managementName", "A"+inc)
						.xls("managementPosition", "J"+inc)
						.xls("NPWPNumber", "K"+inc)
						.xls("sharePercentage", "E"+inc));
			}
		}
		
		if (LobType.isSmel(lobType)) {
			for (int i = 0; i < 11; i++) {
				int inc = -1;
				
				if (StringTool.inArray(filename, "099. PT. BPR Utomo Manunggal Sejahtera.xls", "199 PT. BPR Nusamba Singaparna.xls")) {
					inc = 110;		
				}else if (StringTool.inArray(filename, "264. Aarti Jaya Group.xls")) {
					inc = 133;				
				}else if (StringTool.inArray(filename, "114. CV. Mega Jasa.xls", "114.I. CV. Mega Jasa.xls", "114.II CV. Mega Jasa.xls")) {
					inc = 134;	
				}else if (StringTool.inArray(filename, "271. Suwandi Group - 2.xls", "271.II. Suwandi Group.xls", "271.I. Suwandi Group.xls", "271. Suwandi Group.xls", "271. CV. Wijaya Bersaudara.xls", "271. CV. Trinity Karya Mandiri.xls", "271. CV. Tri Ratu Tekstil.xls", "271. CV. Tri Mega Jaya.xls", "271. CV. Subur Triratutex.xls", "271. CV. Andalan Wijaya.xls", "206.II. PT Lematang.xls", "206.III PT Lematang.xls", "206.IV PT Lematang.xls", "098. PT. Palapa Energi Indonesia.xls")) {
					inc = 127;	
				}else if (StringTool.inArray(filename, "262. Antonius Yogipranata Group - 2.xls", "113.II. PT. Bintang Nusantara Linda - Regularisasi 2.xls", "113.I. PT. Bintang Nusantara Linda.xls", "113. PT. Bintang Nusantara Linda.xls", "280.I. PT Cipta Aneka Pangan Prima.xls", "280. PT Cipta Aneka Pangan Prima.xls", "262. CV. Sinar Sejahtera.xls", "262. CV Sinar Gemilang.xls", "262. Antonius Yogipranata Group – 2.xls", "262. Antonius Yogipranata Group.xls", "0547-BDG Kopo 2- Ricky Group - Renewal Konsolidasi RAC.xls", "0547-BDG Kopo 2- Ricky Group - Konsolidasi (MKK).xls", "012. PT. Mewah Niagajaya.xls", "012. PT. Mewah Niagajaya - 2.xls", "0547-BDG Kopo 2- CV Bintang Terang - New.xls", "0547-BDG Kopo 2- CV Golden Indo Plastic - NEW.xls", "0547-BDG Kopo 2- PT Bintang Mas Indoplast - Renewal.xls", "0547-BDG Kopo 2- Ricky  - Renewal.xls")) {
					inc = 125;	
				}else if (StringTool.inArray(filename, "081. CV. Mega Jasa - Tukar Jaminan.xls", "054. CV. Mega Jasa.xls")) {
					inc = 124;
				}else if (StringTool.inArray(filename, "129. Go Ronny - Tambahan.xls", "227. Go Ronny - Perpanjangan.xls", "227. Go Ronny Group.xls")) {
					inc = 122;
				}else {
					inc = 120;
				}
				
				inc = inc + i;
				specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
						.xls("appId", "J7")
						.xls("createdDate", "J4")
						.xls("age", "L"+inc)
						.xls("datiII", "H"+inc)
						.xls("experienceInYears", "M"+inc)
						.xls("idCode", "B"+inc)
						.xls("idNumber", "C"+inc)
						.xls("joinedSinceYears", "N"+inc)
						.xls("managementAddress", "E"+inc)
						.xls("managementName", "A"+inc)
						.xls("managementPosition", "J"+inc)
						.xls("NPWPNumber", "K"+inc)
						.xls("sharePercentage", "D"+inc));
			}
		}
	}
}
