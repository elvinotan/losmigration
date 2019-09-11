package com.btpn.migration.los.mapping.informasidebitur;

import java.util.List;

import com.btpn.migration.los.bean.IActions;
import com.btpn.migration.los.bean.Mapper;
import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.bean.Store;
import com.btpn.migration.los.constant.LobType;

public class DlosAppSocialMedia {
	
	public static void migrate(String filename, String lobType, String MIGRATION, List<SpecRow> specRows) {
		
		IActions insert = new IActions() {
			
			@Override
			public String[] insert(Mapper mapper, Store store, String lobType) throws Exception {
				return null;
			}
		};
		
		if (LobType.isSmes(lobType)) {}
		
		if (LobType.isSmel(lobType)) {}
	}
}
