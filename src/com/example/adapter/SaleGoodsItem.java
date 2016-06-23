package com.example.adapter;

public class SaleGoodsItem {
	String goodsName;
	String goodsPrice;
	String goodimg1URL;
	String guserid;
	String ggoodsid;
	String ggoodtype;

	public SaleGoodsItem(String goodsName, String goodsPrice,
			String goodimg1url, String guserid, String ggoodsid,String ggoodtype) {
		super();
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		goodimg1URL = goodimg1url;
		this.guserid = guserid;
		this.ggoodsid = ggoodsid;
		this.ggoodtype = ggoodtype;
	}
	
	public String getGgoodtype() {
		return ggoodtype;
	}

	public void setGgoodtype(String ggoodtype) {
		this.ggoodtype = ggoodtype;
	}

	public String getGuserid() {
		return guserid;
	}

	public void setGuserid(String guserid) {
		this.guserid = guserid;
	}

	public String getGgoodsid() {
		return ggoodsid;
	}

	public void setGgoodsid(String ggoodsid) {
		this.ggoodsid = ggoodsid;
	}

	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodimg1URL() {
		return goodimg1URL;
	}
	public void setGoodimg1URL(String goodimg1url) {
		goodimg1URL = goodimg1url;
	}
	
}
