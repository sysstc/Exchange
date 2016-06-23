package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.activity.GoodDetail;
import com.example.activity.MainActivity;
import com.example.bean.GoodsInformation;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.a.mine;
import cn.bmob.v3.listener.FindListener;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ImageList {
	private ArrayList<SaleGoodsItem> dataList;
	private Context context;
	private Map<String, String> map;
	private RecyclerView recyclerView;
	private MyRecyclerAdapter adapter;
	private ImageLoader imageLoader;
	private HashMap<String, String> maps;
	//参数：当前activity的Context，label表示是求页面还是卖页面
	public ImageList(Context mContext,int label,RecyclerView mRecyclerView,ImageLoader mImageLoader) {
		// TODO 自动生成的构造函数存根
		this.context = mContext;
		this.recyclerView = mRecyclerView;
		this.imageLoader = mImageLoader;
		
		dataList = new ArrayList<SaleGoodsItem>();
		adapter = new MyRecyclerAdapter(mContext, dataList,imageLoader);
		mRecyclerView.setAdapter(adapter);
		adapter.setOnClickListener(new MyRecyclerAdapter.OnItemClickListener() {
			
			@Override
			public void ItemLongClickListener(View view, int position) {
				// TODO 自动生成的方法存根
				dataList.remove(position);
				adapter.notifyItemRemoved(position);
			}
			
			@Override
			public void ItemClickListener(View view, int position) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(context,GoodDetail.class);
				intent.putExtra("guserid", dataList.get(position).getGuserid());
				intent.putExtra("ggoodsid", dataList.get(position).getGgoodsid());
				intent.putExtra("ggoodtype", dataList.get(position).getGgoodtype());
				context.startActivity(intent);
				Toast.makeText(context, "点击了："+dataList.get(position).getGgoodtype(), Toast.LENGTH_SHORT).show();
			}
		});
		
		BmobQuery<GoodsInformation> query_goods = new BmobQuery<GoodsInformation>();
		query_goods.order("-goodsclick");
		query_goods.findObjects(context, new FindListener<GoodsInformation>() {
			@Override
			public void onSuccess(List<GoodsInformation> goodsInformations) {
				// TODO 自动生成的方法存根
				for (GoodsInformation goodsInformation:goodsInformations) {
					map = new HashMap<String,String>();
					if(goodsInformation.getGoodsname()!=null)
						map.put("goodname", goodsInformation.getGoodsname());
					map.put("goodprice", String.valueOf(goodsInformation.getGoodprice()));
					if(goodsInformation.getGoodsimg1()!=null)
						map.put("goodimg1Url",goodsInformation.getGoodsimg1().getUrl());
					
					Log.i("info", "72 getUserid = "+goodsInformation.getUserid()+" goodname = "+goodsInformation.getGoodsname());
					map.put("guserid", String.valueOf(goodsInformation.getUserid()));
					map.put("ggoodsid", goodsInformation.getGoodsid());
					map.put("ggoodtype", String.valueOf(goodsInformation.getType()));
					Message msg = new Message();
					msg.what=1;
					msg.obj = map;
					handler.sendMessage(msg);
				}
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Log.i("info", "error 34 = "+arg0+"  "+arg1);
			}
		});
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Object aObject;
			switch (msg.what) {
			case 1:
				aObject = msg.obj;
				maps = (HashMap<String,String>) aObject;
				Log.i("info", "goodname = "+maps.get("goodname"));
				SaleGoodsItem saleGoodsItem = new SaleGoodsItem(maps.get("goodname"),
						maps.get("goodprice"), maps.get("goodimg1Url"), maps.get("guserid"), maps.get("ggoodsid"),maps.get("ggoodtype"));
						//new SaleGoodsItem(,
						//, maps.get("goodimg1Url"));
				dataList.add(saleGoodsItem);
				adapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};
}
