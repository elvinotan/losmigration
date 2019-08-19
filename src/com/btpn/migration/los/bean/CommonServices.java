package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class CommonServices {
	
	private static CommonServices cs = null;
	
	public static CommonServices get() {
		if (cs == null) { cs = new CommonServices(); }
		return cs;
	}

	public List<CommonService> getCommonServices() {
		List<CommonService> list = new ArrayList<CommonService>();
		
		
		return list;
	}
}
