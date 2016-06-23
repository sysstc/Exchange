package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Collections extends BmobObject{
	public Integer userid;
	public Integer typeid;
	public String goodsid;
	
	public Collections(){
		super();
	}
	
	
	public Collections(Integer userid, Integer typeid, String goodsid) {
		super();
		this.userid = userid;
		this.typeid = typeid;
		this.goodsid = goodsid;
	}


	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
	
	
}
