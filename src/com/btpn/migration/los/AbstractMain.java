package com.btpn.migration.los;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.CommonServices;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.db.DbConnection;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.tool.DateTool;

public class AbstractMain {
	final static Logger log = Logger.getLogger(AbstractMain.class);
	
	private void clearTables(List<Mapping> mapping) {
		log.debug("clearTables");
		
		PreparedStatement preStmt = null;
		try {
			Connection conn = DbConnection.get().getConnection();
			for (Mapping m : mapping) {
				for (String sql : m.clearTable()) {
					log.debug(sql);
					preStmt = conn.prepareStatement(sql);
					preStmt.execute();
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
		}finally {
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	private void loadCommonService(Store store) {
		log.debug("loadCommonService");
		
		for (CommonService cs : CommonServices.get().getCommonServices()) {
			store.add(cs);
		}
	}
	
	private void loadLookup(Store store) {
		log.debug("loadLookup");
		
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from dlos_lookup_detail";
			Connection connection = DbConnection.get().getConnection();
			preStmt = connection.prepareStatement(query);
			log.debug(query);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				String lookupId = rs.getString("lookupId");
				String key = rs.getString("key");
				String group = rs.getString("group");
				String description = rs.getString("description");
				Boolean isActive = rs.getBoolean("isActive");
				store.add(new Lookup(Long.valueOf(lookupId), key, group, description, isActive));
			}
		}catch(Exception e) {
			log.error(e.getMessage(), e);
		}finally {
			if (rs != null)  try { rs.close(); }catch(Exception e) { e.printStackTrace(); }
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	private void execInsert(String insertStms, SpecRow specRow, Store store) {
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		
		try {
			Connection connection = DbConnection.get().getConnection();
			preStmt = connection.prepareStatement(insertStms);
			log.debug(insertStms);
			boolean execute = preStmt.execute();
			preStmt.close();
		}catch(Exception e) {
			log.error(e.getMessage(), e);
		}finally {
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
		}
		
		SpecCell pkCell = specRow.getPk();
		if (pkCell != null) {
			
			try {
				Connection connection = DbConnection.get().getConnection();
				preStmt = connection.prepareStatement("SELECT LAST_INSERT_ID() as pk");
				rs = preStmt.executeQuery();
				if (rs.next()) {
					String pk = rs.getString("pk");
					store.put(pkCell.getVariable(), pk);
				}
				
				boolean execute = preStmt.execute();
				preStmt.close();
				rs.close();
			}catch(Exception e) {
				log.error(e.getMessage(), e);
			}finally {
				if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
			}
		}
	}

	protected void loadFile(File file, List<Mapping> mapping) throws Exception {
		log.debug("loadFile "+file.getName());
		
		Store store = new Store();
		clearTables(mapping);
		loadCommonService(store);
		loadLookup(store);
		
		// Initilize cell
		for (Mapping m : mapping) {
			m.getSpecRows().clear();
			m.initMapping(); 
		}

		FileInputStream xlsFile = new FileInputStream(file);
		Workbook workbook = new HSSFWorkbook(xlsFile);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheetname = sheet.getSheetName();
			
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();
				Iterator<Cell> cellItr = row.iterator();

				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					String address = cell.getAddress().toString();
					String value = "";

					if (cell.getCellType() == CellType.BOOLEAN) {
						value = null;
					} else if (cell.getCellType() == CellType.BLANK) {
						value = "";
					} else if (cell.getCellType() == CellType.NUMERIC) {
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							value = DateTool.format(date);
						}else {
							value = String.valueOf(cell.getNumericCellValue());
						}
					} else if (cell.getCellType() == CellType.STRING) {
						value = String.valueOf(cell.getStringCellValue());
					} else if (cell.getCellType() == CellType.FORMULA) {
						if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								value = DateTool.format(date);
							}else {
								value = String.valueOf(cell.getNumericCellValue());
							}
						}else if (cell.getCachedFormulaResultType() == CellType.STRING) {
							value = String.valueOf(cell.getStringCellValue());
						}
					}
					for (Mapping m : mapping) {
						for (SpecRow specRow : m.getSpecRows()) {
							for (SpecCell speCell : specRow.getSpecCells()) {
								if (speCell.isFix()) continue;
								if (sheetname.equals(speCell.getSheet()) && address.equals(speCell.getAddress())) { speCell.setValue(value); }	
							}
						}
					}					
				}
			}
		}
		
		Mapper mapper = new Mapper();
		for (Mapping m : mapping) {
			for (SpecRow specRow : m.getSpecRows()) {
				mapper.setSpecCells(specRow.getSpecCells());
				String sql = specRow.getAction().insert(mapper, store);
				sql = sql.replaceAll("'null'", "null"); // Hapus null string insert
				
				execInsert(sql, specRow, store);
			}
		}
		
		for (Mapping m : mapping) {
			for (SpecRow r : m.getSpecRows()) { r.clear(); }
		}
		
		DbConnection.get().close();
		mapping = null;
		workbook.close();
		xlsFile.close();
	}
}
