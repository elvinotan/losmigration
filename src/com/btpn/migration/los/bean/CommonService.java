package com.btpn.migration.los.bean;

public class CommonService {
	private Long id;
	private String group;
	private String code;
	private String description;
	
	public CommonService(Long id, String group, String code, String description) {
		super();
		this.id = id;
		this.group = group;
		this.code = code;
		this.description = description;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getGroup() { return group; }
	public void setGroup(String group) { this.group = group; }

	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() {
		return "CommonService [id=" + id + ", group=" + group + ", code=" + code + ", description=" + description + "]";
	}
}