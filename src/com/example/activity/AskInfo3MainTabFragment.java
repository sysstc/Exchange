package com.example.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.bmob.v3.listener.SaveListener;

import com.example.bean.Exchange;
import com.example.bean.GoodsInformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskInfo3MainTabFragment extends Fragment{
	private String username;
	private Integer userid;
	private Context context;
	private String goodsid;
	
	private EditText askInfo3_editText_goodsname;
	private EditText askInfo3_editText_price;
	private EditText askInfo3_editText_place;
	private EditText askInfo3_editText_describe;
	private Button askInfo3_button_ensure;
	
	private String goodsname;
	private String price;
	private String place;
	private String describe;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View view =  inflater.inflate(R.layout.create_ask_info3, container,false);
		context = getActivity().getApplicationContext();
		Random random = new Random();
		String aString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		int a = (int)(Math.random()*(9999-1000+1)+1000);
		goodsid = aString+String.valueOf(a);
		
		username = getUsername();
		userid = getUserId();
		
		askInfo3_editText_goodsname = (EditText) view.findViewById(R.id.askInfo3_editText_goodsname);
		askInfo3_editText_price = (EditText) view.findViewById(R.id.askInfo3_editText_price);
		askInfo3_editText_place = (EditText) view.findViewById(R.id.askInfo3_editText_place);
		askInfo3_editText_describe = (EditText) view.findViewById(R.id.askInfo3_editText_describe);
		askInfo3_button_ensure = (Button) view.findViewById(R.id.askInfo3_button_ensure);
		askInfo3_button_ensure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				place = askInfo3_editText_place.getText().toString();
				goodsname = askInfo3_editText_goodsname.getText().toString();
				price = askInfo3_editText_price.getText().toString();
				describe = askInfo3_editText_describe.getText().toString();
				
				Exchange exchange = new Exchange();
				exchange.setUserid(userid);
				exchange.setExchangedealtag(0);
				exchange.setExchangeaddress(place);
				exchange.setExchangetag(0);
				exchange.setGoodsid(goodsid);
				exchange.save(context,new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(context, "在Exchange表中添加数据成功",Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
					}
				});
				GoodsInformation goodsInformation = new GoodsInformation();
				goodsInformation.setGoodsname(goodsname);
				goodsInformation.setGoodsid(goodsid);
				goodsInformation.setType(2);
				goodsInformation.setGoodsclick(0);
				goodsInformation.setUserid(userid);
				goodsInformation.setGoodsdescribe(describe);
				goodsInformation.save(context, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(context, "在GoodInformation表中添加数据成功",Toast.LENGTH_SHORT).show();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userid;
	}

	public void setUserId(Integer userid) {
		this.userid = userid;
	}
	
}
