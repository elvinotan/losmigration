package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;
import com.btpn.migration.los.tool.StringTool;

public class DlosAppProperty {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {

			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String homeOwnershipStatus = mapper.getString("homeOwnershipStatus");
				Lookup lhomeOwnershipStatus = store.getLookupByDescription(new String[] {Lookup.HomeOwnership, Lookup.BusinessOwnership}, homeOwnershipStatus);
				homeOwnershipStatus = (lhomeOwnershipStatus == null) ? mapper.logMapperProblem("migrasiDlosAppProperty") :  lhomeOwnershipStatus.getKey();
				
				String businessOwnershipStatus = null;
				
				String ownershipDate = mapper.getString("ownershipDate");				
				if ("2013.0".equals(ownershipDate)) ownershipDate = "2013-01-01 00:00:00";
				if ("2003.0".equals(ownershipDate)) ownershipDate = "2003-01-01 00:00:00";
				if ("2012.0".equals(ownershipDate)) ownershipDate = "2012-01-01 00:00:00";
				if ("2012".equals(ownershipDate)) ownershipDate = "2012-01-01 00:00:00";
				if ("2010.0".equals(ownershipDate)) ownershipDate = "2010-01-01 00:00:00";
				if ("2010".equals(ownershipDate)) ownershipDate = "2010-01-01 00:00:00";
				if ("2000.0".equals(ownershipDate)) ownershipDate = "2000-01-01 00:00:00";
				if ("11 Januari 2011".equals(ownershipDate)) ownershipDate = "2011-01-11 00:00:00";
				ownershipDate = DateTool.getYMD(ownershipDate);
				
				String leaseDate = DateTool.getYMD(mapper.getString("leaseDate"));
				String businessEntityType = null;
				String businessName = null;
				String dataId = store.getString("dataId");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
				String createdBy = MIGRATION;
				
				return new String[] {
						"migrasiDlosAppProperty",
				
						String.format(
						"INSERT INTO dlos_core.dlos_app_property (homeOwnershipStatus, businessOwnershipStatus, ownershipDate, leaseDate, businessEntityType, businessName, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy) " + 
						"VALUES(                                  '%s',                '%s',                    '%s',          '%s',      '%s',               '%s',         '%s',   %s,       '%s',         '%s',       '%s',        '%s'); ", 
						homeOwnershipStatus, businessOwnershipStatus, ownershipDate, leaseDate, businessEntityType, businessName, dataId, isActive, modifiedDate, modifiedBy, createdDate, createdBy
					)
				};
			}
		};
		
		if (StringTool.inArray(filename, "099. PT. BPR Utomo Manunggal Sejahtera.xls", "199 PT. BPR Nusamba Singaparna.xls")) {
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("homeOwnershipStatus", "D30")
					.xls("leaseDate", "H32")
					.xls("ownershipDate", "H31"));
		}else {		
			specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
					.xls("appId", "J7")
					.xls("createdDate", "J4")
					.xls("homeOwnershipStatus", "D27")
					.xls("leaseDate", "H29")
					.xls("ownershipDate", "H28"));
		}
	}
}