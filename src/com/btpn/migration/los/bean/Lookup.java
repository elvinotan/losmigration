package com.btpn.migration.los.bean;

public class Lookup {
	private Long lookupId;
	private String key;
	private String group;
	private String description;
	private Boolean active;
	
	public Lookup(Long lookupId, String key, String group, String description, Boolean active) {
		super();
		this.lookupId = lookupId;
		this.key = key;
		this.group = group;
		this.description = description;
		this.active = active;
	}
	
	public Long getLookupId() { return lookupId;	}
	public void setLookupId(Long lookupId) { this.lookupId = lookupId; }
	
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	
	public String getGroup() { return group; }
	public void setGroup(String group) { this.group = group; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public Boolean isActive() { return active; }
	public void setActive(Boolean active) { this.active = active; }

	@Override
	public String toString() {
		return "Lookup [lookupId=" + lookupId + ", key=" + key + ", group=" + group + ", description=" + description + ", active=" + active + "]";
	}
}
