package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;

public class AnalisaRekeningKoran implements Mapping{
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	@Override
	public String[] clearTable() {
		return new String[] {
			"Delete from tbl a where createdBy = 'MIGRASI'"	
		};
	}

	@Override
	public void initMapping() {
		SpecRow row1 = new SpecRow(new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) {
				return "insert into dlos_analisa_rekening_koran(Plafon, Rataratasaldo) values("+mapper.getString("Plafon")+","+mapper.getNumber("Rataratasaldo")+");";
			}
			
			@Override
			public void afterInsert(Mapper mapper, Store store, String pk) {
				// TODO Auto-generated method stub
			}
		});
		row1.getSpecCells().add(SpecCell.get().sheet(Sheet.Analisa_Rek_Koran).address("M27").variable("Plafon"));
		row1.getSpecCells().add(SpecCell.get().sheet(Sheet.Analisa_Rek_Koran).address("M28").variable("Rataratasaldo"));
		specRows.add(row1);
	}
	
	@Override
	public List<SpecRow> getSpecRows() {
		return this.specRows;
	}
}