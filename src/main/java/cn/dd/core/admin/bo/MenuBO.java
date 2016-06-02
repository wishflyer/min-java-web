package cn.dd.core.admin.bo;

import java.util.*;

public class MenuBO  {


	private String id;
	private String name;
	private String url;
	private String config;
	private String icon;
	private String description;
	private String other;
	private String groupId;
	private String parentId;
	private int status;

	public String getId() {
		return this.id;
	}
	
	public void setId(String value) {
		this.id = value;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}

	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String value) {
		this.url = value;
	}

	public String getConfig() {
		return this.config;
	}
	
	public void setConfig(String value) {
		this.config = value;
	}

	public String getIcon() {
		return this.icon;
	}
	
	public void setIcon(String value) {
		this.icon = value;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}

	public String getOther() {
		return this.other;
	}
	
	public void setOther(String value) {
		this.other = value;
	}

	public String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(String value) {
		this.groupId = value;
	}

	public String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(String value) {
		this.parentId = value;
	}

	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int value) {
		this.status = value;
	}

}

