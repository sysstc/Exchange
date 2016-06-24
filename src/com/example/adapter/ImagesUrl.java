package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bean.GoodsInformation;
import com.example.bean.User;
import com.nostra13.universalimageloader.core.ImageLoader;

import c.well;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

public class ImagesUrl {
	private ArrayList<HashMap<String, String>> dataList;
	HashMap<String, String> map = new HashMap<String,String>();

	public String[] urls = null;
	public static List<Map<String, String>> GOODSMAP = new ArrayList<Map<String, String>>();
	private Map<String, String> good_map_temp = new HashMap<String,String>();
	
	private int i = 0;
	private ArrayList<ListViewItem>arrayList;
	
	ListView listView;
	Context context;
	ImageLoader imageLoader;
	ListViewAdapter adapter;
	
	private ListViewItem listViewItem;
	private HashMap<String, String> maps;
	
	String goodnameStr;
	String goodsdateStr;
	String goodimg1Str;
	String goodimg2Str;
	String goodimg3Str;
	
	String goodsid;
	Integer userid;
	Integer currentUserid;
	
	
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
/**
 * 最后一位表示的是请求的数据是求的(0)还是卖的(1)或者是求和卖都要的(2)
 * */
	public ImagesUrl(Context mContext, ListView mListView,ImageLoader mImageLoader,Integer currentUserid,int dealType,String findWords){

		listView = mListView;
		context = mContext;
		imageLoader = mImageLoader;
		GOODSMAP = new ArrayList<Map<String, String>>();
		arrayList  = new ArrayList<ListViewItem>();
		this.currentUserid = currentUserid;
		Log.i("info", "ImagesUrl userid = "+currentUserid);
		adapter = new ListViewAdapter(arrayList,context,imageLoader,currentUserid);
		listView.setAdapter(adapter);
		dataList = new ArrayList<HashMap<String,String>>();
		map = new HashMap<String,String>();
		
		List<BmobQuery<GoodsInformation>> queries = new ArrayList<BmobQuery<GoodsInformation>>();
		
		BmobQuery<GoodsInformation> bmobQuery1 = new BmobQuery<GoodsInformation>();
		//根据点击率排序且控制显示50个商品 与外部and
		bmobQuery1.order("-goodsclick");
		bmobQuery1.setLimit(50);
		queries.add(bmobQuery1);
		//判断需要的是求信息还是卖信息 与外部and
		if(dealType==1||dealType==0){
			BmobQuery<GoodsInformation> bmobQuery2 = new BmobQuery<GoodsInformation>();
			if(dealType==1)
				bmobQuery2.addWhereEqualTo("dealtype", 1);
			else if(dealType == 0)
				bmobQuery2.addWhereEqualTo("dealtype", 0);
			queries.add(bmobQuery2);	
		}
		Log.d("info", "dealType = "+dealType);
		//找包含查询词的内容 与外部and 与内部or
		if(findWords!=null){
			List<BmobQuery<GoodsInformation>> word_query = new ArrayList<BmobQuery<GoodsInformation>>();
			BmobQuery<GoodsInformation> bmobQuery3 = new BmobQuery<GoodsInformation>();
			bmobQuery3.addWhereContains("goodsname", findWords);
			BmobQuery<GoodsInformation> bmobQuery4 = new BmobQuery<GoodsInformation>();
			bmobQuery4.addWhereContains("goodsdescribe", findWords);
			word_query.add(bmobQuery3);
			word_query.add(bmobQuery4);
			BmobQuery<GoodsInformation> mainQuery = new BmobQuery<GoodsInformation>();
			BmobQuery<GoodsInformation> or = mainQuery.or(word_query);
			queries.add(or);
		}
		BmobQuery<GoodsInformation> bmobQuery = new BmobQuery<GoodsInformation>();
		bmobQuery.and(queries);
		bmobQuery.findObjects(context, new FindListener<GoodsInformation>() {
			
			@Override
			public void onSuccess(List<GoodsInformation> goodsInformations) {
				GOODSMAP = new ArrayList<Map<String, String>>();
				Log.i("info", "goodsInformations size = "+goodsInformations.size());
				// TODO 自动生成的方法存根
				for(GoodsInformation goodsInformation:goodsInformations){
			
					good_map_temp = new HashMap<String,String>();
					good_map_temp.put("guserid", String.valueOf(goodsInformation.getUserid()));
					good_map_temp.put("ggoodsid", goodsInformation.getGoodsid());
					good_map_temp.put("ggoodtype", String.valueOf(goodsInformation.getType()));
					GOODSMAP.add(good_map_temp);
					
					
					dataList = new ArrayList<HashMap<String,String>>();
					map = new HashMap<String,String>();
					BmobFile goodimg1 = null;
					BmobFile goodimg2 = null;
					BmobFile goodimg3 = null;
					String goodsname;
					String goodsdate;
					Integer guserid;
					String goodsid = goodsInformation.getGoodsid();
					map.put("goodsid", goodsid);
					
					Integer userid = goodsInformation.getUserid();
					map.put("userid", String.valueOf(userid));
					
					map.put("goodsid", goodsInformation.getGoodsid());
					
					
					map.put("typeid", String.valueOf(goodsInformation.getType()));
					goodsdate = goodsInformation.getCreatedAt();
					
					if(goodsInformation.goodsimg1!=null){
						goodimg1 = goodsInformation.goodsimg1;
						
						map.put("goodimg1", goodimg1.getUrl());
						
						Log.i("ImagesUrl", "goodimg1.getUrl() = "+goodimg1.getUrl()+" map size = "+map.size());
					}
					if(goodsInformation.goodsimg2!=null){
						goodimg2 = goodsInformation.goodsimg2;
						map.put("goodimg2", goodimg2.getUrl());
						Log.i("ImagesUrl", "goodimg2.getUrl() = "+goodimg2.getUrl()+" map size = "+map.size());
					}
					if(goodsInformation.goodsimg3!=null){
						goodimg3 = goodsInformation.goodsimg3;
						map.put("goodimg3", goodimg3.getUrl());
						Log.i("ImagesUrl", "goodimg3.getUrl() = "+goodimg3.getUrl()+" map size = "+map.size());
					}
					
					goodsname = goodsInformation.goodsname;
					map.put("goodname", goodsname);
					map.put("goodsdate", goodsdate);
					
					Message msg = new Message();
					msg.what = 1;
					msg.obj = map;
					handler.sendMessage(msg);
				}
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Log.i("error", arg0+" "+arg1);
			}
		});
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Object aObject;
			Integer userid;

			
			switch (msg.what) {
			case 1:
				aObject = msg.obj;
				maps = (HashMap<String,String>) aObject;
				Log.i("ImagesUrl", "maps 1111size = "+maps.size());
				listViewItem = null;
				Log.i("info", "goodnameStr = "+maps.get("goodname"));
				setGoodsid(maps.get("goodsid"));
				setUserid(Integer.valueOf(maps.get("userid")));
				Log.i("info","goodsid = "+maps.get("goodsid")+"userid = "+maps.get("userid"));
				
				if(maps.size()==5){
							listViewItem = new ListViewItem(Integer.valueOf(maps.get("typeid")),
									maps.get("goodname"),maps.get("goodsdate"),null
									,null,null,null,null,maps.get("goodsid"),
									Integer.valueOf(maps.get("typeid")));
							arrayList.add(listViewItem);
							adapter.notifyDataSetChanged();
							
				}else if(maps.size()==6){
							listViewItem = new ListViewItem(Integer.valueOf(maps.get("typeid")),
									maps.get("goodname"),maps.get("goodsdate"),null
									,null,maps.get("goodimg1"),null,null,maps.get("goodsid"),
									Integer.valueOf(maps.get("typeid")));
							arrayList.add(listViewItem);
							adapter.notifyDataSetChanged();
				}else if(maps.size()==7){
							listViewItem = new ListViewItem(Integer.valueOf(maps.get("typeid")),
									maps.get("goodname"),maps.get("goodsdate"),null
									,null,maps.get("goodimg1"),maps.get("goodimg2"),null,
									maps.get("goodsid"),Integer.valueOf(maps.get("typeid")));
							arrayList.add(listViewItem);
							adapter.notifyDataSetChanged();
				}else if(maps.size()==8){
							listViewItem = new ListViewItem(Integer.valueOf(maps.get("typeid")),
									maps.get("goodname"),maps.get("goodsdate"),null
									,null,maps.get("goodimg1"),maps.get("goodimg2"),maps.get("goodimg3"),
									maps.get("goodsid"),	Integer.valueOf(maps.get("typeid")));
							arrayList.add(listViewItem);
							adapter.notifyDataSetChanged();
				}				
				break;

			default:
				break;
			}
		};
	};
	public ArrayList<HashMap<String, String>> getDataList() {
		Log.i("ImagesUrl", dataList.size()+" =========");
		return dataList;
	}

	public void setDataList(ArrayList<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}
	
	
}
