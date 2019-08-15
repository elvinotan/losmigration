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

import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.mapping.Mapping;

public class AbstractMain {

	protected void loadFile(File file, List<Mapping> mapping) throws Exception {
		System.out.println("loadFile " + file.getName());
		
		// Initilize cell
		for (Mapping m : mapping) { m.initMapping(); }

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
						for(SpecCell c : m.getSpecTable().getSpecCells()) {
							if (c.is(sheetname, address)) {
								c.value(value);
							}
						}
					}					
				}
			}
		}
		
		for (Mapping m : mapping) {
			for (SpecCell c : m.getSpecTable().getSpecCells()) {
				System.out.println(c);
			}
		}
		
		for (Mapping m : mapping) {
			m.clear();
		}
		workbook.close();
		xlsFile.close();
		
	}
}
