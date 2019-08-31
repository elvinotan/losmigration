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
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.db.DbConnection;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.tool.DateTool;


public class AbstractMain extends AbstractReference {
	final static Logger log = Logger.getLogger(AbstractMain.class);

	private void execInsert(String filename, String mapping, String process, String insertStms, SpecRow specRow, Store store) {
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		
		try {
			preStmt = DbConnection.get().getConnection().prepareStatement(insertStms);
			log.debug(insertStms);
			preStmt.execute();
		}catch(Exception e) {
			log.error("["+filename+">"+mapping+">"+process+"] "+e.getMessage(), e);
		}finally {
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { }
		}
		
		SpecCell pkCell = specRow.getPk();
		if (pkCell != null) {
			try {
				preStmt = DbConnection.get().getConnection().prepareStatement("SELECT LAST_INSERT_ID() as pk");
				rs = preStmt.executeQuery();
				if (rs.next()) {
					String pk = rs.getString("pk");
					store.put(pkCell.getVariable(), pk);
				}
				
				preStmt.execute();
			}catch(Exception e) {
				log.error(e.getMessage(), e);
			}finally {
				if (rs != null)  try { rs.close(); }catch(Exception e) { }
				if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
			}
		}
	}
	
	protected void loadFile(File file, List<Mapping> mapping) throws Exception {
		FileInputStream xlsFile = new FileInputStream(file);
		Workbook workbook = null;
		
		boolean isXls = file.getName().toLowerCase().endsWith(".xls") ? true : false;
		if (isXls) {
			workbook = new HSSFWorkbook(xlsFile);
		}else {
			try {
				workbook = new XSSFWorkbook(xlsFile);
			}catch(OLE2NotOfficeXmlFileException e) {
				workbook = new HSSFWorkbook(xlsFile);	
			}
		}

		// So far masih cuma bedakan SMEL dan Other, untuk MUR masih blm bisa bedakan
		String lobType = LobType.SMES;
		boolean correctMigrationFile = false;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			String sheetname = workbook.getSheetAt(i).getSheetName();
			if (com.btpn.migration.los.constant.Sheet.InformasiDebitur.equals(sheetname)) {
				correctMigrationFile = true;
			}
			if (com.btpn.migration.los.constant.Sheet.Spreading.equals(sheetname)) {
				lobType = LobType.SMEL;
				break;
			}
		}
		
		if (!correctMigrationFile) {
			log.error("Ignore file, "+file.getName()+", is not a migration file");
			workbook.close();
			xlsFile.close();
			return;
		}
		
		// Initilize cell
		for (Mapping m : mapping) {
			m.getSpecRows(lobType).clear();
			m.initMapping(file.getName(), lobType); 
		}

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
						for (SpecRow specRow : m.getSpecRows(lobType)) {
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
			for (SpecRow specRow : m.getSpecRows(lobType)) {
				mapper.className = m.getClass().getSimpleName();
				mapper.filename = file.getName();
				mapper.setSpecCells(specRow.getSpecCells());
				
				try {
					String[] arr = specRow.getAction().insert(mapper, getStore(), lobType);
					if (arr != null) { // arr null artinya datanya kosong,
						String process = arr[0];
						String sql = arr[1];
						
						sql = sql.replaceAll("'null'", "null"); // Hapus null string insert
						if (Main.EXECUTE_SQL_STATEMENT) {
							
							execInsert(file.getName(), m.getClass().getSimpleName(), process, sql, specRow, getStore());
						}else {
							sql = sql.replaceAll("\n\r", "");
							writeSql(sql);
						}
					}
				}catch(Exception e) {
					log.error("["+mapper.filename+">"+mapper.className+"]"+e.getMessage(), e);
				}
			}
		}
		
		for (Mapping m : mapping) {
			for (SpecRow r : m.getSpecRows(lobType)) { r.clear(); }
		}
		
		workbook.close();
		xlsFile.close();
	}
}
