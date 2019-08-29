package com.btpn.migration.los;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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

import com.btpn.migration.los.bean.CommonService;
import com.btpn.migration.los.bean.CommonServiceGroup;
import com.btpn.migration.los.bean.Lookup;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.Region;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;
import com.btpn.migration.los.db.DbConnection;
import com.btpn.migration.los.mapping.Mapping;
import com.btpn.migration.los.tool.DateTool;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class AbstractMain {
	final static Logger log = Logger.getLogger(AbstractMain.class);
	
	private Gson gson = new Gson();
	private Store store = new Store();
	
	private void clearTables(List<Mapping> mapping) {
		log.debug("clearTables");
		
		PreparedStatement preStmt = null;
		try {
			Connection conn = DbConnection.get().getConnection();
			List<Mapping> newMapping = new ArrayList<Mapping>();
			newMapping.addAll(mapping);
			
			Collections.reverse(newMapping);
			for (Mapping m : newMapping) {
				for (String sql : m.clearTable()) {
					log.debug(sql);
					preStmt = conn.prepareStatement(sql);
					preStmt.execute();
				}
			}
			newMapping.clear();
			newMapping = null;
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
		}finally {
			if (preStmt != null)  try { preStmt.close(); }catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	private void loadCommonService(Store store) {
		log.debug("Fetching CommonService from file");
		
		File folder = new File("C:/Users/19057559/workspaces/java/losmigration/json");
		for (File f : folder.listFiles()) {
			try {
				JsonReader reader = new JsonReader(new FileReader(f));
				CommonServiceGroup group = gson.fromJson(reader, CommonServiceGroup.class);
				if (group != null) {
					for (CommonService s : group.getResult()) {
						s.setGroup(f.getName().replaceAll(".json", ""));
						store.add(s);
					}
				}
			}catch(Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	private void loadLookup(Store store) {
		log.debug("Fetching lookup from database");
		
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
	
	private void loadRegion(Store store) {
		log.debug("Fetching Region from cache");
		
		for (Region r : Region.getRegions()) {
			store.add(r);
		}
	}
	
	protected void initilize(List<Mapping> mapping) {
		clearTables(mapping);
		loadCommonService(store);
		loadLookup(store);
		loadRegion(store);
	}	
	
	private void execInsert(String filename, String mapping, String process, String insertStms, SpecRow specRow, Store store) {
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		
		try {
			Connection connection = DbConnection.get().getConnection();
			preStmt = connection.prepareStatement(insertStms);
			log.debug(insertStms);
			boolean execute = preStmt.execute();
			preStmt.close();
		}catch(Exception e) {
			log.error("["+filename+">"+mapping+">"+process+"] "+e.getMessage());
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
		
		boolean isXls = file.getName().toLowerCase().endsWith(".xls") ? true : false;		
		FileInputStream xlsFile = new FileInputStream(file);
		Workbook workbook = null;
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
		boolean correctXls = false;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			String sheetname = workbook.getSheetAt(i).getSheetName();
			if (com.btpn.migration.los.constant.Sheet.InformasiDebitur.equals(sheetname)) {
				correctXls = true;
			}
			if (com.btpn.migration.los.constant.Sheet.Spreading.equals(sheetname)) {
				lobType = LobType.SMEL;
				break;
			}
		}
		
		if (!correctXls) {
			log.error("Ignore file "+file.getName()+", not a migration file");
		}
		
		// Initilize cell
		for (Mapping m : mapping) {
			m.getSpecRows(lobType).clear();
			m.initMapping(lobType); 
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
					String[] arr = specRow.getAction().insert(mapper, store, lobType);
					if (Main.EXECUTE_INSERT &&  arr != null) { // arr null artinya datanya kosong,  
						String process = arr[0];
						String sql = arr[1];
						
						sql = sql.replaceAll("'null'", "null"); // Hapus null string insert
						execInsert(file.getName(), m.getClass().getSimpleName(), process, sql, specRow, store);
					}
				}catch(Exception e) {
					log.error("["+mapper.filename+">"+mapper.className+"]"+e.getMessage(), e);
				}
			}
		}
		
		for (Mapping m : mapping) {
			for (SpecRow r : m.getSpecRows(lobType)) { r.clear(); }
		}
		
		DbConnection.get().close();
		mapping = null;
		workbook.close();
		xlsFile.close();
	}
}
