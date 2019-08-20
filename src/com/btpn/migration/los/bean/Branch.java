package com.btpn.migration.los.bean;

public class Branch {
	private Long branchId;
	private String branchCode;
	private String branchName;
	private Long lobId;
	private Long areaId;
	private Long appIdSequence;
	private Boolean isActive;
	
	public Branch(Long branchId, String branchCode, String branchName, Long lobId, Long areaId, Long appIdSequence, Boolean isActive) {
		super();
		this.branchId = branchId;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.lobId = lobId;
		this.areaId = areaId;
		this.appIdSequence = appIdSequence;
		this.isActive = isActive;
	}
	public Long getBranchId() { return branchId; }
	public void setBranchId(Long branchId) { this.branchId = branchId; }
	
	public String getBranchCode() { return branchCode; }
	public void setBranchCode(String branchCode) { this.branchCode = branchCode; }
	
	public String getBranchName() { return branchName; }
	public void setBranchName(String branchName) { this.branchName = branchName; }
	
	public Long getLobId() { return lobId; }
	public void setLobId(Long lobId) { this.lobId = lobId; }
	
	public Long getAreaId() { return areaId; }
	public void setAreaId(Long areaId) { this.areaId = areaId; }
	
	public Long getAppIdSequence() { return appIdSequence; }
	public void setAppIdSequence(Long appIdSequence) { this.appIdSequence = appIdSequence; }
	
	public Boolean getIsActive() { return isActive; }
	public void setIsActive(Boolean isActive) { this.isActive = isActive; }
	
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchCode=" + branchCode + ", branchName=" + branchName + ", LOBId="
				+ lobId + ", areaId=" + areaId + ", appIdSequence=" + appIdSequence + ", isActive=" + isActive + "]";
	}
}
