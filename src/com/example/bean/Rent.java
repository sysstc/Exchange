package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Rent extends BmobObject{
	public Integer rentid;//��id
	public String goodsid;//��Ʒid
	public Integer userid;//�û�id
	public Integer rentdealtag;//��ǩ
	public String rentplace;//���׵ص�
	public String rentstart;//��ʼʱ��
	public Integer renttag;//��ǩ(�����δ���)
	
	public Rent() {
		// TODO �Զ����ɵĹ��캯�����
		super();
	}
	public Rent(Integer rentid, String goodsid, Integer userid,
			Integer rentdealtag, String rentplace, String rentstart,
			Integer renttag) {
		super();
		this.rentid = rentid;
		this.goodsid = goodsid;
		this.userid = userid;
		this.rentdealtag = rentdealtag;
		this.rentplace = rentplace;
		this.rentstart = rentstart;
		this.renttag = renttag;
	}
	public Integer getRentid() {
		return rentid;
	}
	public void setRentid(Integer rentid) {
		this.rentid = rentid;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRentdealtag() {
		return rentdealtag;
	}
	public void setRentdealtag(Integer rentdealtag) {
		this.rentdealtag = rentdealtag;
	}
	public String getRentplace() {
		return rentplace;
	}
	public void setRentplace(String rentplace) {
		this.rentplace = rentplace;
	}
	public String getRentstart() {
		return rentstart;
	}
	public void setRentstart(String rentstart) {
		this.rentstart = rentstart;
	}
	public Integer getRenttag() {
		return renttag;
	}
	public void setRenttag(Integer renttag) {
		this.renttag = renttag;
	}
	
}
