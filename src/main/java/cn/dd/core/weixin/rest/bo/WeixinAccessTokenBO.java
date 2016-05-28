package cn.dd.core.weixin.rest.bo;

import java.util.*;

public class WeixinAccessTokenBO  {


	private String id;
	private String accessToken;
	private int expiresIn;
	private Date updateDate;

	public String getId() {
		return this.id;
	}
	
	public void setId(String value) {
		this.id = value;
	}

	public String getAccessToken() {
		return this.accessToken;
	}
	
	public void setAccessToken(String value) {
		this.accessToken = value;
	}

	public int getExpiresIn() {
		return this.expiresIn;
	}
	
	public void setExpiresIn(int value) {
		this.expiresIn = value;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}

}

