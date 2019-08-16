package com.btpn.migration.los.bean;

public interface IActions {

	public String insert(Mapper mapper, Store store);
	
	public void afterInsert(Mapper mapper, Store store, String primaryKey);
}
