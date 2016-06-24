package com.example.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.bmob.v3.listener.SaveListener;

import com.example.bean.GoodsInformation;
import com.example.bean.Rent;

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

public class AskInfo1MainTabFragment extends Fragment{
	private EditText askInfo1_editText_goodsname;//����
	private EditText askInfo1_editText_price;//�۸�
	private EditText askInfo1_editText_time;//ʱ��
	private EditText askInfo1_editText_describe;//����
	private EditText askInfo1_editText_place;//�ص�
	private Button askInfo1_button_ensure;//ȷ��
	private Context context;
	
	private Rent rent;
	private String goodsname;
	private String goodsid;
	private String price;
	private String time;
	private String describe;
	private String place;
	private String username;
	private Integer userid;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
	//	getArguments().getString("");
		View view =  inflater.inflate(R.layout.create_ask_info1, container, false);
		askInfo1_editText_goodsname = (EditText) view.findViewById(R.id.askInfo1_editText_goodsname);
		askInfo1_editText_price = (EditText) view.findViewById(R.id.askInfo1_editText_price);
		askInfo1_editText_time = (EditText) view.findViewById(R.id.askInfo1_editText_time);
		askInfo1_editText_place = (EditText) view.findViewById(R.id.askInfo1_editText_place);
		askInfo1_editText_describe = (EditText) view.findViewById(R.id.askInfo1_editText_describe);
		askInfo1_button_ensure = (Button) view.findViewById(R.id.askInfo1_button_ensure);
		
		Log.i("info", "price = "+price);

		username = getUsername();
		userid = getUserId();
		Log.i("info", "Ask---username = "+username+"  userid = "+userid);
		
		Random random = new Random();
		String aString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		int a = (int)(Math.random()*(9999-1000+1)+1000);
		goodsid = aString+String.valueOf(a);
		
		rent = new Rent();
		rent.setGoodsid(goodsid);
		rent.setUserid(userid);
		rent.setRentdealtag(0);
		rent.setRentplace(place);
		rent.setRentstart(time);
		rent.setRenttag(0);
		context = getActivity().getApplicationContext();
		//��������Ϣ���뵽����
		askInfo1_button_ensure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				time = askInfo1_editText_time.getText().toString();
				describe = askInfo1_editText_describe.getText().toString();
				place = askInfo1_editText_place.getText().toString();
				goodsname = askInfo1_editText_goodsname.getText().toString();
				price = askInfo1_editText_price.getText().toString();
				
				rent.save(context, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO �Զ����ɵķ������
						Toast.makeText(context, "��Rent����������ݳɹ�",Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO �Զ����ɵķ������
						
					}
				});
				GoodsInformation goodsInformation = new GoodsInformation();
				goodsInformation.setGoodsname(goodsname);
				goodsInformation.setGoodsid(goodsid);
				goodsInformation.setType(0);
				goodsInformation.setUserid(userid);
				goodsInformation.setGoodprice(Float.valueOf(price));
				goodsInformation.setGoodsdescribe(describe);
				goodsInformation.setGoodsclick(0);
				goodsInformation.setDealtype(0);
				goodsInformation.save(context, new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO �Զ����ɵķ������
						Toast.makeText(context, "��GoodsInformation����������ݳɹ�", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO �Զ����ɵķ������
						
					}
				});
				
			}
		});
		//����Ʒ��Ϣ���뵽����
		
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
