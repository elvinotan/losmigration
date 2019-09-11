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
import com.btpn.migration.los.tool.NumberTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppGroupDebitur {
	
	//ALTER TABLE dlos_core.dlos_app_groupdebitur MODIFY COLUMN `groupDebiturName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_groupdebitur MODIFY COLUMN `contactPersonName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
	//ALTER TABLE dlos_core.dlos_app_groupdebitur MODIFY COLUMN groupDebiturAddress TEXT NULL;
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				
				String groupDebiturName = mapper.getString("groupDebiturName");
				if (StringTool.isEmptyTag(groupDebiturName)) return null; // Artinya datanya tidak di isi maka return null menandakan query tidak di execute
				
				String groupOwnershipPercentage = mapper.getString("groupOwnershipPercentage"); // data yang masukan berupa range 0 s/d 1, sehingga harus di kali 100
				if ("100% (Debitur belum berkenan memberikan Akta PT)".equals(groupOwnershipPercentage)) groupOwnershipPercentage = "100";
				if ("100% dimiliki oleh Diky".equals(groupOwnershipPercentage)) groupOwnershipPercentage = "100";
				if ("2 Tahun".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null;
				if ("nill".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null;
				if ("Bp. Norman sebagai pengurus pemilik 10% saham".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("Bp. Sudjono Janto sebagai Persero Pengurus (Direktur)".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // tidak menjelaskan percentage kepemilikan
				if ("Edie Tjandra (pemegang 54% saham PT MAS) merupakan pemilik PT EAL".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("30%  ( atas nama Sudiardjo Hardi )".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("40% a.n Safiah (isteri Suriadi)".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("David kumala memiliki 10% di PT Ragam Baja Citrajaya".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("40%, Njo Fuk Liang sebagai Direktur Utama di PT Sterindo Medika".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("Bong Lina (Istri Cadeb) & Porsi sesuai anggaran dasar kepemilikan dalam group 29%".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("Bpk. Protasius Siboro dan Bpk. Irzan Kesumaputra Thaib memiliki 35% saham PT Tirtanusa Persada".equals(groupOwnershipPercentage)) groupOwnershipPercentage = null; // nama debitur dan %saham tidak sama sehingga aku set null
				if ("0% (keterkaitan dengan agunan saja)".equals(groupOwnershipPercentage)) groupOwnershipPercentage = "0"; 
				groupOwnershipPercentage = NumberTool.handlePercentage(groupOwnershipPercentage);
				 
				String industrySectorCode = mapper.getString("industrySectorCode"); // data di ambil dari lookup.IndustrialSector
				if (StringTool.isEmptyTag(industrySectorCode)) { industrySectorCode = null;
				}else {
					if (industrySectorCode.toLowerCase().indexOf("manufacturing") >=0 ) industrySectorCode = "Manufacture";
					if (industrySectorCode.toLowerCase().indexOf("manufacture") >=0 ) industrySectorCode = "Manufacture";
					if (industrySectorCode.toLowerCase().indexOf("manufaktur") >=0 ) industrySectorCode = "Manufacture";
					if (industrySectorCode.toLowerCase().indexOf("industri") >=0 ) industrySectorCode = "Manufacture";
					if (industrySectorCode.toLowerCase().indexOf("pabrik") >=0 ) industrySectorCode = "Manufacture";
					if (industrySectorCode.toLowerCase().indexOf("produksi") >=0 ) industrySectorCode = "Manufacture";
					
					
					if (industrySectorCode.toLowerCase().indexOf("trading") >=0 ) industrySectorCode = "Trading";
					if (industrySectorCode.toLowerCase().indexOf("perdagangan") >=0 ) industrySectorCode = "Trading";
					if (industrySectorCode.toLowerCase().indexOf("pedagang") >=0 ) industrySectorCode = "Trading";
					
					if (industrySectorCode.toLowerCase().indexOf("jasa") >=0 ) industrySectorCode = "Service";
					if (industrySectorCode.toLowerCase().indexOf("kontraktor") >=0 ) industrySectorCode = "Service";
					if (industrySectorCode.toLowerCase().indexOf("transpotasi") >=0 ) industrySectorCode = "Service";
					if (industrySectorCode.toLowerCase().indexOf("transportasi") >=0 ) industrySectorCode = "Service";
					if (industrySectorCode.toLowerCase().indexOf("transportation") >=0 ) industrySectorCode = "Service";
					
					Lookup lindustrySectorCode = store.getLookupByDescription(Lookup.IndustrialSector, industrySectorCode);
					industrySectorCode = (lindustrySectorCode == null) ? mapper.logMapperProblem("migrasiDlosAppGroupDebitur") : lindustrySectorCode.getKey();
				}
				
				String yearsOfExperience = mapper.getString("yearsOfExperience"); // Data yang di input seperti '32 tahun' sehingga kita harus mengextract number saja
				yearsOfExperience = NumberTool.extractNumberOnly(yearsOfExperience);
				
				String contactPersonName = mapper.getString("contactPersonName");
				if (StringTool.isEmptyTag(contactPersonName)) contactPersonName = null;
						
				String groupDebiturAddress = mapper.getString("groupDebiturAddress");
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
				String createdBy = MIGRATION;

				return new String[] {
						"migrasiDlosAppGroupDebitur",
				
						String.format( 
						"INSERT INTO dlos_core.dlos_app_groupdebitur (groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                     '%s',              '%s',                     '%s',               '%s',              '%s',              '%s',                '%s',   %s,     '%s',         '%s',       '%s',        '%s');", 
						groupDebiturName, groupOwnershipPercentage, industrySectorCode, yearsOfExperience, contactPersonName, groupDebiturAddress, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
			}
		};
		
		if (LobType.isSmes(lobType)) {
			int c258 = 258; int c259 = 259; int c260 = 260;
			
			if (StringTool.inArray(filename, "template file name")) {
				
			}
			
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("createdDate", "J4")
					.xls("appId", "J7")
					.xls("groupDebiturName", "B"+c258)
					.xls("groupOwnershipPercentage", "H"+c258)
					.xls("industrySectorCode", "B"+c259)
					.xls("yearsOfExperience", "H"+c259)
					.xls("contactPersonName", "B"+c260)
					.xls("groupDebiturAddress", "H"+c260));
			
		}
		
		if (LobType.isSmel(lobType)) {
			int c256 = 256; int c257 = 257; int c258 = 258;
			
			if (StringTool.inArray(filename, "113. PT. Bintang Nusantara Linda.xls", "113.I. PT. Bintang Nusantara Linda.xls", "113.II. PT. Bintang Nusantara Linda - Regularisasi 2.xls")) {
				c256 = 296; c257 = 297; c258 = 298;
			}
				
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("createdDate", "J4")
					.xls("appId", "J7")
					.xls("groupDebiturName", "B"+c256)
					.xls("groupOwnershipPercentage", "H"+c256)
					.xls("industrySectorCode", "B"+c257)
					.xls("yearsOfExperience", "H"+c257)
					.xls("contactPersonName", "B"+c258)
					.xls("groupDebiturAddress", "H"+c258));
		}
	}
}
