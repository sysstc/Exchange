package com.example.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.example.bean.GoodsInformation;
import com.example.bean.Rent;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SaleInfo1MainTabFragment extends Fragment
{
	private EditText saleInfo1_editText_goodsname;//名称
	private EditText saleInfo1_editText_price;//价格
	private EditText saleInfo1_editText_time;//时间
	private EditText saleInfo1_editText_place;//地点
	private EditText saleInfo1_editText_describe;//描述
	
	private String goodsname;
	private String price;
	private String time;
	private String place;
	private String describe;
	
	private ImageView saleInfo1_imageView_goods1;//商品图片1
	private ImageView saleInfo1_imageView_goods2;//商品图片2
	private ImageView saleInfo1_imageView_goods3;//商品图片3
	private ImageView saleInfo1_imageView_goods4;//商品图片4
	private ImageView saleInfo1_imageView_goods5;//商品图片5
	private ImageView saleInfo1_imageView_goods6;//商品图片6
	private Button saleInfo1_button_ensure;//确定键
	private int i;
	private View view;
	private Set<String> imgsSet;
	private Integer userid;
	public static SaleInfo1MainTabFragment saleInfo1MainTabFragment;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		view =  inflater.inflate(R.layout.create_sale_info1, container, false);
		saleInfo1MainTabFragment=this;
		imgsSet =new  TreeSet<String>();
		i = 0;
		initView();
		initEvent();
		return view;
	}
	private Set<String> String2Set(String imgsStr) {
		// TODO 自动生成的方法存根
		String tempstr=new String(imgsStr);
		Log.i("info", tempstr);
		Set<String> aSet = new HashSet<String>();
		Log.i("info", "tempstr.indexOf() = "+tempstr.indexOf("--------"));
		while(!tempstr.equals("")){
			String a;
			a = tempstr.substring(0, tempstr.indexOf("--------"));
			Log.i("info", "a = "+a);
			aSet.add(a);
			tempstr = tempstr.substring(tempstr.indexOf("--------")+8, tempstr.length());
			Log.i("info", "tempstr = "+tempstr);
		}
		return aSet;
	}
	@Override
	public void onResume() {
		// TODO 自动生成的方法存根
		super.onResume();
		i = 0;
		if(imgsSet!=null){
			for(String s:imgsSet){
				i++;
				setImageToView(s,i);
			}
			int size = imgsSet.size();
			imageDisplay(size);
		//	setImgsSet(null);
		}
	}
	private void imageDisplay(int size) {
		// TODO 自动生成的方法存根
		switch(size){
			case 1:
				saleInfo1_imageView_goods3.setVisibility(View.GONE);
			case 2:
				saleInfo1_imageView_goods4.setVisibility(View.GONE);
			case 3:
				saleInfo1_imageView_goods5.setVisibility(View.GONE);
			case 4:
				saleInfo1_imageView_goods6.setVisibility(View.GONE);
				break;
		}
	}
	private void setImageToView(String s,int i) {
		// TODO 自动生成的方法存根
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap pic = BitmapFactory.decodeFile(s,options);
		
		if(i==1){
			saleInfo1_imageView_goods2.setImageBitmap(pic);
			saleInfo1_imageView_goods2.setVisibility(View.VISIBLE);
		}
		if(i==2){	
			saleInfo1_imageView_goods3.setImageBitmap(pic);
			saleInfo1_imageView_goods3.setVisibility(View.VISIBLE);	
		}
		if(i==3){
			saleInfo1_imageView_goods4.setImageBitmap(pic);
			saleInfo1_imageView_goods4.setVisibility(View.VISIBLE);	
		}
		if(i==4){
			saleInfo1_imageView_goods5.setImageBitmap(pic);
			saleInfo1_imageView_goods5.setVisibility(View.VISIBLE);
		}
		if(i==5){
			saleInfo1_imageView_goods6.setImageBitmap(pic);
			saleInfo1_imageView_goods6.setVisibility(View.VISIBLE);
		}
	}
	private void initEvent() {
		// TODO 自动生成的方法存根
		saleInfo1_imageView_goods1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(getActivity(),PictureSelectorActivity.class);
				//startActivity(intent);
				intent.putExtra("contextActivity", "saleInfo1");
				startActivityForResult(intent, 1);//requestCode = 1
			}
		});
		saleInfo1_button_ensure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				goodsname = saleInfo1_editText_goodsname.getText().toString();
				price = saleInfo1_editText_price.getText().toString();
				time = saleInfo1_editText_time.getText().toString();
				place = saleInfo1_editText_place.getText().toString();
				describe = saleInfo1_editText_describe.getText().toString();
				if(goodsname.contains(" ")||goodsname==null)
					Toast.makeText(getActivity(), "请检查，不能出现空格或者商品名称不能空！", Toast.LENGTH_SHORT).show();
				if(price.contains(" ")||price==null)
					Toast.makeText(getActivity(), "请检查，不能出现空格或者价格名称不能空！", Toast.LENGTH_SHORT).show();
				if(time.contains(" ")||time==null)
					Toast.makeText(getActivity(), "请检查，不能出现空格或者时间名称不能空！", Toast.LENGTH_SHORT).show();
				if(place.contains(" ")||place==null)
					Toast.makeText(getActivity(), "请检查，不能出现空格或者地点名称不能空！", Toast.LENGTH_SHORT).show();
				if(describe.contains(" ")||describe==null)
					Toast.makeText(getActivity(), "请检查，不能出现空格或者描述名称不能空！", Toast.LENGTH_SHORT).show();
				if(imgsSet.size()==0)
					Toast.makeText(getActivity(), "请上传商品照片", Toast.LENGTH_SHORT).show();
				final String[] filePaths = new String[imgsSet.size()];
				int k = 0;
				for(String s:imgsSet){
					filePaths[k++] =s;
				}
				BmobFile.uploadBatch(getActivity(), filePaths, new UploadBatchListener() {
					
					@Override
					public void onSuccess(List<BmobFile> bmobfile, List<String> urls) {
						// TODO 自动生成的方法存根
					if(urls.size()==filePaths.length){
						
						Random random = new Random();
						String aString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
						int a = (int)(Math.random()*(9999-1000+1)+1000);
						
						String goodsid = aString+String.valueOf(a);
						int bmobfile_size = bmobfile.size();
						Log.d("info", "bmobfile_size = "+bmobfile_size);
						if(bmobfile_size==1)
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), null, null, null, null, 0, 0, userid,1));
						else if(bmobfile_size==2){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), null, null, null, 0, 0, userid,1));
						}
						else if(bmobfile_size==3){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), null, null, 0, 0, userid,1));
						}
						else if(bmobfile_size==4){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), bmobfile.get(3), null, 0, 0, userid,1));
						}
						else if(bmobfile_size==5){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), bmobfile.get(3), bmobfile.get(4), 0, 0, userid,1));
						}
						}
					}
					@Override
					public void onProgress(int arg0, int arg1, int arg2, int arg3) {
						// TODO 自动生成的方法存根
						
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						
					}
				});
			}
		});
	}
	private void insertObject(final GoodsInformation goods) {
		// TODO 自动生成的方法存根
		goods.save(getActivity(),new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO 自动生成的方法存根
				Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
				Rent rent = new Rent();
				rent.setUserid(goods.userid);
				rent.setRentdealtag(1);
				rent.setGoodsid(goods.goodsid);
				rent.setRentstart(time);
				rent.setRenttag(0);
				rent.setRentplace(place);
				rent.save(getActivity(), new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Log.i("info", "租消息发布成功");
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Log.i("info", arg0+arg1+"租消息发布失败");
					}
				});
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Toast.makeText(getActivity(), arg0+arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}


	private void initView() {
		// TODO 自动生成的方法存根
		saleInfo1_editText_goodsname = (EditText) view.findViewById(R.id.saleInfo1_editText_goodsname);
		saleInfo1_editText_price = (EditText) view.findViewById(R.id.saleInfo1_editText_price);
		saleInfo1_editText_time = (EditText) view.findViewById(R.id.saleInfo1_editText_time);
		saleInfo1_editText_place = (EditText) view.findViewById(R.id.saleInfo1_editText_place);
		saleInfo1_editText_describe = (EditText) view.findViewById(R.id.saleInfo1_editText_describe);
		saleInfo1_imageView_goods1 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods1);
		saleInfo1_imageView_goods2 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods2);
		saleInfo1_imageView_goods3 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods3);
		saleInfo1_imageView_goods4 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods4);
		saleInfo1_imageView_goods5 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods5);
		saleInfo1_imageView_goods6 = (ImageView) view.findViewById(R.id.saleInfo1_imageView_goods6);
		saleInfo1_button_ensure = (Button) view.findViewById(R.id.saleInfo1_button_ensure);
	}

	public void setPicture(Set<String> imgsSet,Integer userid) {
		// TODO 自动生成的方法存根
		this.imgsSet=imgsSet;
		this.userid = userid;
		Log.i("info", "userid = "+userid);
		/*for(String s:imgsSet){
			Log.d("info", s);
		}*/
	}
}
