package com.btpn.migration.los;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.mapping.Mapping;

public class AbstractMain {

	protected void loadFile(File file, List<Mapping> mapping) throws Exception {
		System.out.println("loadFile " + file.getName());
		
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
						value = String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType() == CellType.STRING) {
						value = String.valueOf(cell.getStringCellValue());
					} else if (cell.getCellType() == CellType.FORMULA) {
						if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
							value = String.valueOf(cell.getNumericCellValue());
						}else if (cell.getCachedFormulaResultType() == CellType.STRING) {
							value = String.valueOf(cell.getStringCellValue());
						}
					}
					for (Mapping m : mapping) {
						for (SpecRow specRow : m.getSpecRows()) {
							for (SpecCell speCell : specRow.getSpecCells()) {
								if (speCell.isFix()) continue;
								if (speCell.isMatch(sheetname, address)) { speCell.value(value); }	
							}
						}
					}					
				}
			}
		}
		
		Store store = new Store();
		Mapper mapper = new Mapper();
		for (Mapping m : mapping) {
			for (SpecRow specRow : m.getSpecRows()) {
				mapper.setSpecCells(specRow.getSpecCells());
				String sql = specRow.getAction().insert(mapper, store);
				System.out.println("- "+sql);
				
				// Lakukan oprasional insert lalu jalankan SELECT LAST_INSERT_ID(); untuk mendapatkan primarykey
				String primaryKey = String.valueOf(123456L);
				specRow.getAction().afterInsert(mapper, store, primaryKey);
			}
		}
		
		for (Mapping m : mapping) {
			for (SpecRow r : m.getSpecRows()) { r.clear(); }
		}
		
		workbook.close();
		xlsFile.close();
		
	}
}
