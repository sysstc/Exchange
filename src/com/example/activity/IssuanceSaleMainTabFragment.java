package com.example.activity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.adapter.IssuanceAdapter;
import com.example.bean.GoodsInformation;
import com.example.bean.MySaleItem;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class IssuanceSaleMainTabFragment extends Fragment
{
	private View view;

	private Integer userid;
	private IssuanceAdapter issuanceAdapter;
	private ArrayList<MySaleItem> mySaleItems;
	private ArrayList<MySaleItem> mSaleList ;
	private ListView issuance_sale_listView;
	
	public IssuanceSaleMainTabFragment(Integer userid){
		Log.d("info", "Issuance userid = "+userid);
		this.userid = Integer.valueOf(userid);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		view = inflater.inflate(R.layout.my_issuance_sale, container, false);
		initView();
		initEvent();
		return view;
	}
	private void initEvent() {
		// TODO 自动生成的方法存根
		 getData();
		 issuance_sale_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
	//			Log.d("info", "64  mySaleItems = "+mySaleItems.get(position).goodsname);
				Intent intent = new Intent(getActivity(),GoodDetail.class);
				Log.i("info", "guserid = "+String.valueOf(mySaleItems.get(position).guserid)+"ggoodsid = "+mySaleItems.get(position).ggoodsid+" ggoodtype"+mySaleItems.get(position).type);
				intent.putExtra("guserid",String.valueOf(mySaleItems.get(position).guserid));
				intent.putExtra("ggoodsid",  mySaleItems.get(position).ggoodsid);
				intent.putExtra("ggoodtype", String.valueOf(mySaleItems.get(position).type));
				startActivity(intent);
			}
		});
	}
	private void getData() {
		// TODO 自动生成的方法存根
		BmobQuery<GoodsInformation> query_goods = new BmobQuery<GoodsInformation>();
		query_goods.addWhereEqualTo("userid", userid);
		query_goods.addWhereEqualTo("dealtype",1);
		mSaleList = new ArrayList<MySaleItem>();
		query_goods.findObjects(getActivity(), new FindListener<GoodsInformation>() {
			@Override
			public void onSuccess(List<GoodsInformation> goods) {
				// TODO 自动生成的方法存根
				for(GoodsInformation good:goods){
					MySaleItem mSaleItem = new MySaleItem();
					mSaleItem.type = good.getType();
					mSaleItem.goodsname = good.getGoodsname();
					mSaleItem.time = good.getCreatedAt();
					mSaleItem.guserid = good.getUserid();
					mSaleItem.ggoodsid = good.getGoodsid();
					mSaleList.add(mSaleItem);
				}
				Message msg = new Message();
				msg.what = 1;
				msg.obj = mSaleList;
				handler.sendMessage(msg);
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
			}
		});
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Object aObject;
			switch (msg.what) {
			case 1:
				aObject = msg.obj;
	
				mySaleItems = (ArrayList<MySaleItem>) aObject;
				if(mySaleItems!=null){
					issuanceAdapter = new IssuanceAdapter(mySaleItems,getActivity(),userid,issuance_sale_listView);
					issuance_sale_listView.setAdapter(issuanceAdapter);
					issuanceAdapter.notifyDataSetChanged();
				}
				break;

			default:
				break;
			}
		};
	};

	private void initView() {
		// TODO 自动生成的方法存根
		mySaleItems = new ArrayList<MySaleItem>();
		issuance_sale_listView = (ListView) view.findViewById(R.id.issuance_sale_listView);
		Log.d("info", "issuance_sale_listView = "+issuance_sale_listView);
	}
}
