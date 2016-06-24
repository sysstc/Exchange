package com.example.bean;


public class MySaleItem{
	public int type;//交易类型
	public String goodsname;//商品名称
	public String time;//发布时间
	public Integer guserid;//商品发布者id
	public String ggoodsid;//商品id
	
	public Integer getGuserid() {
		return guserid;
	}
	public void setGuserid(Integer guserid) {
		this.guserid = guserid;
	}
	public String getGgoodsid() {
		return ggoodsid;
	}
	public void setGgoodsid(String ggoodsid) {
		this.ggoodsid = ggoodsid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}