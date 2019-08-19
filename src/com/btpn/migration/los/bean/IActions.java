package com.btpn.migration.los.bean;

public interface IActions {

    // Method ini di panggil untuk melakukan oprasional insert dgn data mapper yang sudah terdapat valuenya, dan store yang bersifat data global
	public String insert(Mapper mapper, Store store) throws Exception;
}
