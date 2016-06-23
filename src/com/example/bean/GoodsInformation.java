package com.example.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class GoodsInformation extends BmobObject{
	public String goodsid;
	public String goodsname;
	
	public BmobFile goodsimg1;
	public BmobFile goodsimg2;
	public BmobFile goodsimg3;
	public BmobFile goodsimg4;
	public BmobFile goodsimg5;
	public float goodprice;
	public String goodsdescribe;
	
	public Integer type;
	public Integer goodsclick;
	public Integer userid;
	
	public GoodsInformation(){
		super();
	}
	public GoodsInformation(String goodsid, String goodsname,float goodprice,String goodsdescribe,
			BmobFile goodsimg1, BmobFile goodsimg2, BmobFile goodsimg3,
			BmobFile goodsimg4, BmobFile goodsimg5,Integer type, Integer goodsclick,Integer userid) {
		super();
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.goodsimg1 = goodsimg1;
	
		this.goodsimg2 = goodsimg2;
		this.goodsimg3 = goodsimg3;
		this.goodsimg4 = goodsimg4;
		this.goodsimg5 = goodsimg5;
		this.type = type;
		this.goodprice = goodprice;
		this.goodsclick = goodsclick;
		this.userid = userid;
		this.goodsdescribe = goodsdescribe;
	}
	
	
	public String getGoodsdescribe() {
		return goodsdescribe;
	}


	public void setGoodsdescribe(String goodsdescribe) {
		this.goodsdescribe = goodsdescribe;
	}


	public float getGoodprice() {
		return goodprice;
	}


	public void setGoodprice(float goodprice) {
		this.goodprice = goodprice;
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
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public BmobFile getGoodsimg1() {
		return goodsimg1;
	}
	public void setGoodsimg1(BmobFile goodsimg1) {
		this.goodsimg1 = goodsimg1;
	}
	public BmobFile getGoodsimg2() {
		return goodsimg2;
	}
	public void setGoodsimg2(BmobFile goodsimg2) {
		this.goodsimg2 = goodsimg2;
	}
	public BmobFile getGoodsimg3() {
		return goodsimg3;
	}
	public void setGoodsimg3(BmobFile goodsimg3) {
		this.goodsimg3 = goodsimg3;
	}
	public BmobFile getGoodsimg4() {
		return goodsimg4;
	}
	public void setGoodsimg4(BmobFile goodsimg4) {
		this.goodsimg4 = goodsimg4;
	}
	public BmobFile getGoodsimg5() {
		return goodsimg5;
	}
	public void setGoodsimg5(BmobFile goodsimg5) {
		this.goodsimg5 = goodsimg5;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGoodsclick() {
		return goodsclick;
	}
	public void setGoodsclick(Integer goodsclick) {
		this.goodsclick = goodsclick;
	}
}