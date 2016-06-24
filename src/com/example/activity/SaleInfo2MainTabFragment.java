package com.example.activity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

import com.example.bean.Deal;
import com.example.bean.GoodsInformation;
import com.example.bean.Rent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SaleInfo2MainTabFragment extends Fragment
{
	private EditText saleInfo2_editText_goodsname;//��Ʒ����
	private EditText saleInfo2_editText_price;//��Ʒ�۸�
	private EditText saleInfo2_editText_place;//��Ʒ�ص�
	private EditText saleInfo2_editText_describe;//��Ʒ����
	private ImageView saleInfo2_imageView_goods1;//��ƷͼƬ1
	private ImageView saleInfo2_imageView_goods2;//��ƷͼƬ2
	private ImageView saleInfo2_imageView_goods3;//��ƷͼƬ3
	private ImageView saleInfo2_imageView_goods4;//��ƷͼƬ4
	private ImageView saleInfo2_imageView_goods5;//��ƷͼƬ5
	private ImageView saleInfo2_imageView_goods6;//��ƷͼƬ6
	private Button saleInfo2_button_ensure;//�ύ��Ʒ
	
	private String goodsname;
	private String price;
	private String place;
	private String describe;
	
	private View view;
	private Set<String> imgsSet;
	private int i = 0;
	private Integer userid;
	public static SaleInfo2MainTabFragment saleInfo2MainTabFragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view =  inflater.inflate(R.layout.create_sale_info2, container, false);
		saleInfo2MainTabFragment = this;
		imgsSet =new  TreeSet<String>();
		i = 0;
		initView();
		initEvent();
		return view;
	}
	@Override
	public void onResume() {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		switch(size){
			case 1:
				saleInfo2_imageView_goods3.setVisibility(View.GONE);
			case 2:
				saleInfo2_imageView_goods4.setVisibility(View.GONE);
			case 3:
				saleInfo2_imageView_goods5.setVisibility(View.GONE);
			case 4:
				saleInfo2_imageView_goods6.setVisibility(View.GONE);
				break;
		}
	}
	private void setImageToView(String s,int i) {
		// TODO �Զ����ɵķ������
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap pic = BitmapFactory.decodeFile(s,options);
		
		if(i==1){
			saleInfo2_imageView_goods2.setImageBitmap(pic);
			saleInfo2_imageView_goods2.setVisibility(View.VISIBLE);
		}
		if(i==2){	
			saleInfo2_imageView_goods3.setImageBitmap(pic);
			saleInfo2_imageView_goods3.setVisibility(View.VISIBLE);	
		}
		if(i==3){
			saleInfo2_imageView_goods4.setImageBitmap(pic);
			saleInfo2_imageView_goods4.setVisibility(View.VISIBLE);	
		}
		if(i==4){
			saleInfo2_imageView_goods5.setImageBitmap(pic);
			saleInfo2_imageView_goods5.setVisibility(View.VISIBLE);
		}
		if(i==5){
			saleInfo2_imageView_goods6.setImageBitmap(pic);
			saleInfo2_imageView_goods6.setVisibility(View.VISIBLE);
		}
	}
	private void initEvent() {
		// TODO �Զ����ɵķ������
		saleInfo2_imageView_goods1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(getActivity(),PictureSelectorActivity.class);
				//startActivity(intent);
				intent.putExtra("contextActivity", "saleInfo2");
				startActivityForResult(intent, 1);//requestCode = 1
			}
		});
		saleInfo2_button_ensure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				goodsname = saleInfo2_editText_goodsname.getText().toString();
				price = saleInfo2_editText_price.getText().toString();
				place = saleInfo2_editText_place.getText().toString();
				describe = saleInfo2_editText_describe.getText().toString();
				if(goodsname.contains(" ")||goodsname==null)
					Toast.makeText(getActivity(), "���飬���ܳ��ֿո������Ʒ���Ʋ��ܿգ�", Toast.LENGTH_SHORT).show();
				if(price.contains(" ")||price==null)
					Toast.makeText(getActivity(), "���飬���ܳ��ֿո���߼۸����Ʋ��ܿգ�", Toast.LENGTH_SHORT).show();
				if(place.contains(" ")||place==null)
					Toast.makeText(getActivity(), "���飬���ܳ��ֿո���ߵص����Ʋ��ܿգ�", Toast.LENGTH_SHORT).show();
				if(describe.contains(" ")||describe==null)
					Toast.makeText(getActivity(), "���飬���ܳ��ֿո�����������Ʋ��ܿգ�", Toast.LENGTH_SHORT).show();
				if(imgsSet.size()==0)
					Toast.makeText(getActivity(), "���ϴ���Ʒ��Ƭ", Toast.LENGTH_SHORT).show();
				final String[] filePaths = new String[imgsSet.size()];
				int k = 0;
				for(String s:imgsSet){
					filePaths[k++] =s;
				}
				BmobFile.uploadBatch(getActivity(), filePaths, new UploadBatchListener() {
					
					@Override
					public void onSuccess(List<BmobFile> bmobfile, List<String> urls) {
						// TODO �Զ����ɵķ������
					if(urls.size()==filePaths.length){
						
						Random random = new Random();
						String aString = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
						int a = (int)(Math.random()*(9999-1000+1)+1000);
						
						String goodsid = aString+String.valueOf(a);
						int bmobfile_size = bmobfile.size();
						Log.d("info", "bmobfile_size = "+bmobfile_size);
						if(bmobfile_size==1)
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), null, null, null, null, 1, 0, userid,1));
						else if(bmobfile_size==2){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), null, null, null, 1, 0, userid,1));
						}
						else if(bmobfile_size==3){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), null, null, 1, 0, userid,1));
						}
						else if(bmobfile_size==4){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), bmobfile.get(3), null, 1, 0, userid,1));
						}
						else if(bmobfile_size==5){
							insertObject(new GoodsInformation(goodsid, goodsname, Float.parseFloat(price), describe, bmobfile.get(0), bmobfile.get(1), bmobfile.get(2), bmobfile.get(3), bmobfile.get(4), 1, 0, userid,1));
						}
						}
					}
					@Override
					public void onProgress(int arg0, int arg1, int arg2, int arg3) {
						// TODO �Զ����ɵķ������
						
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO �Զ����ɵķ������
						
					}
				});
			}
		});
		
		
	}
	private void insertObject(final GoodsInformation goods) {
		// TODO �Զ����ɵķ������
		goods.save(getActivity(),new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO �Զ����ɵķ������
				Toast.makeText(getActivity(), "�ϴ��ɹ�", Toast.LENGTH_SHORT).show();
				Deal deal = new Deal();
				deal.setUserid(goods.userid);
				deal.setGoodsid(goods.goodsid);
				deal.setDealbuytag(1);
				deal.setDealtag(0);
				deal.setDealaddress(place);
				deal.save(getActivity(), new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO �Զ����ɵķ������
						Log.i("info", "����Ϣ�����ɹ�");
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO �Զ����ɵķ������
						Log.i("info", arg0+arg1+"����Ϣ����ʧ��");
					}
				});
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO �Զ����ɵķ������
				Toast.makeText(getActivity(), arg0+arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void initView() {
		// TODO �Զ����ɵķ������
		saleInfo2_editText_goodsname = (EditText) view.findViewById(R.id.saleInfo2_editText_goodsname);
		saleInfo2_editText_price = (EditText) view.findViewById(R.id.saleInfo2_editText_price);
		saleInfo2_editText_place = (EditText) view.findViewById(R.id.saleInfo2_editText_place);
		saleInfo2_editText_describe = (EditText) view.findViewById(R.id.saleInfo2_editText_describe);
		saleInfo2_imageView_goods1 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods1);
		saleInfo2_imageView_goods2 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods2);
		saleInfo2_imageView_goods3 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods3);
		saleInfo2_imageView_goods4 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods4);
		saleInfo2_imageView_goods5 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods5);
		saleInfo2_imageView_goods6 = (ImageView) view.findViewById(R.id.saleInfo2_imageView_goods6);
		
		saleInfo2_button_ensure = (Button) view.findViewById(R.id.saleInfo2_button_ensure);
	}
	public void setPicture(Set<String> imgsSet,Integer userid) {
		// TODO �Զ����ɵķ������
		this.imgsSet=imgsSet;
		this.userid = userid;
		Log.i("info", "userid = "+userid);
		/*for(String s:imgsSet){
			Log.d("info", s);
		}*/
	}
}
