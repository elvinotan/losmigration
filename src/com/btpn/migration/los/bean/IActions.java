package com.btpn.migration.los.bean;

public interface IActions {

    // Method ini di panggil untuk melakukan oprasional insert dgn data mapper yang sudah terdapat valuenya, dan store yang bersifat data global
	public String insert(Mapper mapper, Store store);
	
	// Method ini berfungsi untuk oper data yang telah dan insert dan passing data yang di generate pada saat insert (like : primary key)
	public void afterInsert(Mapper mapper, Store store, String primaryKey);
}
