package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Deal extends BmobObject{
	public Integer dealid;
	public Integer userid;
	public Integer dealbuytag;
	public String dealaddress;
	public Integer dealtag;
	public String goodsid;
	public Deal(){
		super();
	}
	public Deal(Integer dealid, Integer userid, Integer dealbuytag,
			String dealaddress, Integer dealtag, String goodsid) {
		super();
		this.dealid = dealid;
		this.userid = userid;
		this.dealbuytag = dealbuytag;
		this.dealaddress = dealaddress;
		this.dealtag = dealtag;
		this.goodsid = goodsid;
	}
	public Integer getDealid() {
		return dealid;
	}
	public void setDealid(Integer dealid) {
		this.dealid = dealid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getDealbuytag() {
		return dealbuytag;
	}
	public void setDealbuytag(Integer dealbuytag) {
		this.dealbuytag = dealbuytag;
	}
	public String getDealaddress() {
		return dealaddress;
	}
	public void setDealaddress(String dealaddress) {
		this.dealaddress = dealaddress;
	}
	public Integer getDealtag() {
		return dealtag;
	}
	public void setDealtag(Integer dealtag) {
		this.dealtag = dealtag;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
}
