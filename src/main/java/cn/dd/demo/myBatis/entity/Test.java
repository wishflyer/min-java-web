package cn.dd.demo.myBatis.entity;

import java.util.*;

public class Test  implements java.io.Serializable{

	private static final long serialVersionUID = 8868155888220151225L;

	private String id;
	private String msg;

	public Test(){
	}

	public Test(String id){
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}

	public void setMsg(String value) {
		this.msg = value;
	}
	
	public String getMsg() {
		return this.msg;
	}

}

