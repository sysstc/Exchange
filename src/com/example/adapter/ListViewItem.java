package com.example.adapter;


public class ListViewItem{
	Integer userPhotoURL;
	String userNameStr;
	String timeStr;
	String goodsNameStr;
	String goodsid;
	Integer type;
	String priceString;
	String goods1URL;
	String goods2URL;
	String goods3URL;

	public ListViewItem(Integer userPhotoURL, String userNameStr,
			String timeStr, String goodsNameStr, String priceString,
			String goods1url, String goods2url, String goods3url,String goodsid,Integer type) {
		super();
		this.userPhotoURL = userPhotoURL;
		this.userNameStr = userNameStr;
		this.timeStr = timeStr;
		this.goodsNameStr = goodsNameStr;
		this.priceString = priceString;
		goods1URL = goods1url;
		goods2URL = goods2url;
		goods3URL = goods3url;
		this.goodsid = goodsid;
		this.type = type;
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

	public Integer getUserPhotoURL() {
		return userPhotoURL;
	}
	public void setUserPhotoURL(Integer userPhotoURL) {
		this.userPhotoURL = userPhotoURL;
	}
	public String getUserNameStr() {
		return userNameStr;
	}
	public void setUserNameStr(String userNameStr) {
		this.userNameStr = userNameStr;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getGoodsNameStr() {
		return goodsNameStr;
	}
	public void setGoodsNameStr(String goodsNameStr) {
		this.goodsNameStr = goodsNameStr;
	}
	public String getPriceString() {
		return priceString;
	}
	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}
	public String getGoods1URL() {
		return goods1URL;
	}
	public void setGoods1URL(String goods1url) {
		goods1URL = goods1url;
	}
	public String getGoods2URL() {
		return goods2URL;
	}
	public void setGoods2URL(String goods2url) {
		goods2URL = goods2url;
	}
	public String getGoods3URL() {
		return goods3URL;
	}
	public void setGoods3URL(String goods3url) {
		goods3URL = goods3url;
	}
	
}
