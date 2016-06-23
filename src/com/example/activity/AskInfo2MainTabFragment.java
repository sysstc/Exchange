package com.example.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.bmob.v3.listener.SaveListener;

import com.example.bean.Deal;
import com.example.bean.GoodsInformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskInfo2MainTabFragment extends Fragment{

	private EditText askInfo2_editText_goodsname;//名称
	private EditText askInfo2_editText_price;//价格
	private EditText askInfo2_editText_place;//位置
	private EditText askInfo2_editText_describe;//描述
	private Button askInfo2_button_ensure;//确认
	
	private String username;
	private Integer userid;
	private String goodsid;
	private String goodsname;
	private String price;
	private String place;
	private String describe;
	private Context context;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view = inflater.inflate(R.layout.create_ask_info2, container, false);
		context = getActivity().getApplicationContext();
		askInfo2_editText_goodsname = (EditText) view.findViewById(R.id.askInfo2_editText_goodsname);
		askInfo2_editText_price = (EditText) view.findViewById(R.id.askInfo2_editText_price);
		askInfo2_editText_place = (EditText) view.findViewById(R.id.askInfo2_editText_place);
		askInfo2_editText_describe = (EditText) view.findViewById(R.id.askInfo2_editText_describe);
		askInfo2_button_ensure = (Button) view.findViewById(R.id.askInfo2_button_ensure);
		
		username = getUsername();
		userid = getUserId();
		Log.i("info", "AskInfo2Main username = "+username+" userid = "+userid);
		
		Random random = new Random();
		String aString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		int a = (int)(Math.random()*(9999-1000+1)+1000);
		goodsid = aString+String.valueOf(a);
		
		askInfo2_button_ensure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				goodsname = askInfo2_editText_goodsname.getText().toString();
				price = askInfo2_editText_price.getText().toString();
				place = askInfo2_editText_place.getText().toString();
				describe = askInfo2_editText_describe.getText().toString();
				Deal deal = new Deal();
				deal.setUserid(userid);
				deal.setDealbuytag(0);
				deal.setDealaddress(place);
				deal.setDealtag(0);
				deal.setGoodsid(goodsid);
				deal.save(context, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(context, "在Deal表中添加数据", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						
					}
				});
				GoodsInformation goodsInformation = new GoodsInformation();
				goodsInformation.setGoodsname(goodsname);
				goodsInformation.setGoodsid(goodsid);
				goodsInformation.setType(3);
				goodsInformation.setGoodsclick(0);
				goodsInformation.setGoodprice(Float.valueOf(price));
				goodsInformation.setGoodsdescribe(describe);
				goodsInformation.save(context, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(context, "在GoodsInformation表中添加数据", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
					}
				});
				
			}
		});
		
		return view;
	}
	public void  setUsername(String uname){
		this.username = uname;
	}
	public String getUsername(){
		return username;
	}
	public void setUserId(Integer userid){
		this.userid = userid;
	}
	public Integer getUserId(){
		return userid;
	}
}
