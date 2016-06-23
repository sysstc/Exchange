package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Likeit extends BmobObject{
	public Integer userid;
	public String goodsid;
	public Integer type;
	
	public Likeit(){
		super();
	}
	public Likeit(Integer userid, String goodsid, Integer type) {
		super();
		this.userid = userid;
		this.goodsid = goodsid;
		this.type = type;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
