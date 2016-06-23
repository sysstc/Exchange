package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Exchange extends BmobObject{
	public Integer exchangeid;
	public Integer userid;
	public Integer exchangedealtag;//0:Çó£¬1£º»»
	public String exchangeaddress;
	public Integer exchangetag;
	public String goodsid;
	
	public Exchange(){
		super();
	}
	public Exchange(Integer exchangeid, Integer userid,
			Integer exchangedealtag, String exchangeaddress,
			Integer exchangetag, String goodsid) {
		super();
		this.exchangeid = exchangeid;
		this.userid = userid;
		this.exchangedealtag = exchangedealtag;
		this.exchangeaddress = exchangeaddress;
		this.exchangetag = exchangetag;
		this.goodsid = goodsid;
	}
	public Integer getExchangeid() {
		return exchangeid;
	}
	public void setExchangeid(Integer exchangeid) {
		this.exchangeid = exchangeid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getExchangedealtag() {
		return exchangedealtag;
	}
	public void setExchangedealtag(Integer exchangedealtag) {
		this.exchangedealtag = exchangedealtag;
	}
	public String getExchangeaddress() {
		return exchangeaddress;
	}
	public void setExchangeaddress(String exchangeaddress) {
		this.exchangeaddress = exchangeaddress;
	}
	public Integer getExchangetag() {
		return exchangetag;
	}
	public void setExchangetag(Integer exchangetag) {
		this.exchangetag = exchangetag;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	
	
}
