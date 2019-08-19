package com.btpn.migration.los.bean;

import java.util.ArrayList;
import java.util.List;

public class CommonServiceGroup {
	private int totalRecord;
	private List<CommonService> result = new ArrayList<CommonService>();
	
	public int getTotalRecord() { return totalRecord; }
	public void setTotalRecord(int totalRecord) { this.totalRecord = totalRecord; }
	
	public List<CommonService> getResult() { return result; }
	public void setResult(List<CommonService> result) { this.result = result; }
}