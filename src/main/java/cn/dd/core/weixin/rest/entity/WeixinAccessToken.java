package cn.dd.core.weixin.rest.entity;

import java.util.*;

public class WeixinAccessToken  implements java.io.Serializable{

	private static final long serialVersionUID = 8868155888220151225L;

	private String id;
	private String accessToken;
	private int expiresIn;
	private Date updateDate;

	public WeixinAccessToken(){
	}

	public WeixinAccessToken(String id){
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}

	public void setAccessToken(String value) {
		this.accessToken = value;
	}
	
	public String getAccessToken() {
		return this.accessToken;
	}

	public void setExpiresIn(int value) {
		this.expiresIn = value;
	}
	
	public int getExpiresIn() {
		return this.expiresIn;
	}

	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}

}

