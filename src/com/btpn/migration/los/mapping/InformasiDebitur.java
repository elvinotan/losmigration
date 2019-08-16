package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecCell;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.Sheet;

public class InformasiDebitur implements Mapping{
	private List<SpecRow> specRows = new ArrayList<SpecRow>();
	
	@Override
	public String[] clearTable() {
		return new String[] {
			"Delete from tbl a where createdBy = 'MIGRASI'"	
		};
	}
	
	@Override
	public void initMapping() {
		SpecRow informasiDebitur = new SpecRow(new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) {
				return String.format("insert into dlos_informasi_debitur(nameRM, kodeRM, Bank) values(%s,%s,%s);",
					mapper.getString("NamaRM"),
					mapper.getString("KodeRm"),
					mapper.getString("bank")
				);
			}
			
			@Override
			public void afterInsert(Mapper mapper, Store store, String pk) {
				store.put("primary_key_debitur", pk);
			}
		});
		informasiDebitur.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).variable("bank").value("BTPN").fix(true));
		informasiDebitur.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C4").variable("NamaRM"));
		informasiDebitur.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C5").variable("KodeRm"));
		specRows.add(informasiDebitur);
		
		IActions informasiDebiturDetailAction = new IActions() {
			@Override
			public String insert(Mapper mapper, Store store) {
				return String.format("insert into dlos_informasi_debitur_detail(Region, Cabang, debiturPk) values(%s,%s, %s);",
						mapper.getString("Region"),
						mapper.getString("Cabang"),
						store.getString("primary_key_debitur")
						);
			}

			@Override
			public void afterInsert(Mapper mapper, Store store, String primaryKey) {
			
			}
		};
		
		SpecRow informasiDebiturDet1 = new SpecRow(informasiDebiturDetailAction);		
		informasiDebiturDet1.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C8").variable("Region"));
		informasiDebiturDet1.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C9").variable("Cabang"));
		specRows.add(informasiDebiturDet1);
		
		SpecRow informasiDebiturDet2 = new SpecRow(informasiDebiturDetailAction);		
		informasiDebiturDet2.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C10").variable("Region"));
		informasiDebiturDet2.getSpecCells().add(SpecCell.get().sheet(Sheet.Informasi_Debitur).address("C11").variable("Cabang"));
		specRows.add(informasiDebiturDet2);
	}
	
	@Override
	public List<SpecRow> getSpecRows() {
		return this.specRows;
	}
}
