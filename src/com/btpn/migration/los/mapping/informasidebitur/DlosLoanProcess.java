package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;
import com.btpn.migration.los.tool.DateTool;

public class DlosLoanProcess {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				String APPID = mapper.getString("appId");
				String dataId = store.getString("dataId");
				String processStatus = mapper.getString("processStatus");
				String processCode = null;
				String stateCode = mapper.getString("stateCode");
				String isActive = "1";
				String modifiedDate = null;
				String modifiedBy = mapper.getString("appId");
				
				String createdDate = mapper.getString("createdDate");
				if ("26 Juni 2018".equals(createdDate)) createdDate = "2018-05-26 00:00:00";
				createdDate = DateTool.getYMD(createdDate);
				
				String createdBy = MIGRATION;

				return new String[] {
						"migrasiDlosLoanProcess",				
						
						String.format(
						"INSERT INTO dlos_core.dlos_loan_process (`APPID`, `dataId`, `processStatus`, `processCode`, `stateCode`, `isActive`, `modifiedDate`, `modifiedBy`, `createdDate`, `createdBy`) "
								+ "VALUES(						  '%s',     %s,      '%s',            '%s',          '%s',        %s,         '%s',           '%s',         '%s',            '%s');",
						APPID, dataId, processStatus, processCode, stateCode, isActive, modifiedDate, modifiedBy, createdDate, createdBy)
				};
			}
		};
		specRows.add(SpecRow.get(insert).setSheet(Sheet.InformasiDebitur)
				.xls("appId", "J7")
				.fix("processStatus", "1")
				.xls("createdDate", "J4"));
	}
}
