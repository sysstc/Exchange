package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Certification extends BmobObject {
	public Integer userid;
	public String schoolname;
	public String username;
	public String usernum;
	public Certification(){
		super();
	}
	public Certification(String schoolname, String username, String usernum) {
		super();
		this.schoolname = schoolname;
		this.username = username;
		this.usernum = usernum;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public String getUsername() {
		return username;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

}
