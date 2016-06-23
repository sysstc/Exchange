package com.example.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobObject{
	private Integer userid;
	private String username;
	private String usernick;
	private String usermail;
	private String usercontact;
	private String userpassword;
	private BmobFile userhead;  
	private String userschool;
	private String usernum;
	private int usertag;

	public User(){
		super();
	}
	public User( String username, String usernick,
			String usermail, String usercontact, String userpassword,
			BmobFile userhead, String userschool, String usernum, int usertag) {
		super();

		this.username = username;
		this.usernick = usernick;
		this.usermail = usermail;
		this.usercontact = usercontact;
		this.userpassword = userpassword;
		this.userhead = userhead;
		this.userschool = userschool;
		this.usernum = usernum;
		this.usertag = usertag;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernick() {
		return usernick;
	}
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}
	public String getUsermail() {
		return usermail;
	}
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	public String getUsercontact() {
		return usercontact;
	}
	public void setUsercontact(String usercontact) {
		this.usercontact = usercontact;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public BmobFile getUserhead() {
		return userhead;
	}
	public void setUserhead(BmobFile userhead) {
		this.userhead = userhead;
	}
	public String getUserschool() {
		return userschool;
	}
	public void setUserschool(String userschool) {
		this.userschool = userschool;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public int getUsertag() {
		return usertag;
	}
	public void setUsertag(int usertag) {
		this.usertag = usertag;
	}
	
	
}
