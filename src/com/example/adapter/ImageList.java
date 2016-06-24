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
	/**
	 * ��������ǰactivity��Context��label��ʾ����ҳ��(0)������ҳ��(1)
	 * 
	*/
	public ImageList(Context mContext,int label,RecyclerView mRecyclerView,ImageLoader mImageLoader,String findwords) {
		// TODO �Զ����ɵĹ��캯�����
		this.context = mContext;
		this.recyclerView = mRecyclerView;
		this.imageLoader = mImageLoader;
		
		dataList = new ArrayList<SaleGoodsItem>();
		adapter = new MyRecyclerAdapter(mContext, dataList,imageLoader);
		mRecyclerView.setAdapter(adapter);
		adapter.setOnClickListener(new MyRecyclerAdapter.OnItemClickListener() {
			
			@Override
			public void ItemLongClickListener(View view, int position) {
				// TODO �Զ����ɵķ������
				dataList.remove(position);
				adapter.notifyItemRemoved(position);
			}
			
			@Override
			public void ItemClickListener(View view, int position) {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(context,GoodDetail.class);
				intent.putExtra("guserid", dataList.get(position).getGuserid());
				intent.putExtra("ggoodsid", dataList.get(position).getGgoodsid());
				intent.putExtra("ggoodtype", dataList.get(position).getGgoodtype());
				context.startActivity(intent);
				Toast.makeText(context, "����ˣ�"+dataList.get(position).getGgoodtype(), Toast.LENGTH_SHORT).show();
			}
		});
		List<BmobQuery<GoodsInformation>> main_query = new ArrayList<BmobQuery<GoodsInformation>>();
		//�����Լ��ж���/��  ���ⲿand��ϵ
		BmobQuery<GoodsInformation> query_goods1 = new BmobQuery<GoodsInformation>();
		query_goods1.order("-goodsclick");
		if(label==1)
			query_goods1.addWhereEqualTo("dealtype", 1);
		else if(label==0)
			query_goods1.addWhereEqualTo("dealtype", 0);
		main_query.add(query_goods1);
		
		if(findwords!=null){//�ڲ����ⲿ��
			List<BmobQuery<GoodsInformation>> word_query = new ArrayList<BmobQuery<GoodsInformation>>();
			BmobQuery<GoodsInformation> bmobQuery3 = new BmobQuery<GoodsInformation>();
			bmobQuery3.addWhereContains("goodsname", findwords);
			BmobQuery<GoodsInformation> bmobQuery4 = new BmobQuery<GoodsInformation>();
			bmobQuery4.addWhereContains("goodsdescribe", findwords);
			word_query.add(bmobQuery3);
			word_query.add(bmobQuery4);
			BmobQuery<GoodsInformation> mainQuery = new BmobQuery<GoodsInformation>();
			BmobQuery<GoodsInformation> or = mainQuery.or(word_query);
			main_query.add(or);
		}
		BmobQuery<GoodsInformation> goodQuery = new BmobQuery<GoodsInformation>();
		goodQuery.and(main_query);
		goodQuery.findObjects(context, new FindListener<GoodsInformation>() {
			@Override
			public void onSuccess(List<GoodsInformation> goodsInformations) {
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
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
