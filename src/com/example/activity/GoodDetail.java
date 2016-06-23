package com.example.activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.example.adapter.ImagesUrl;
import com.example.adapter.ListViewItem;
import com.example.bean.Certification;
import com.example.bean.Collections;
import com.example.bean.GoodsInformation;
import com.example.bean.Likeit;
import com.example.bean.User;
import com.example.util.SlideShowView;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GoodDetail extends BaseActivity{
	private String username;
	private SlideShowView mSlideShowView;
	private TextView goods_textView_price;//商品价格
	private TextView goods_textView_goodsName;//商品名称
	private TextView goods_textView_time1;//商品发布时间
	private ImageView goods_imageView_userPhoto;//用户头像
	private TextView goods_textView_userName;//用户名称
	private TextView goods_textView_infodetail;//商品详情
	private String guserid;//发出信息的用户
	private String ggoodsid;//商品的id
	private Integer ggoodtype;//商品的交易类型
	private int position;
	private Map<String, String> maps;
	private RelativeLayout relativelayout;
	private ImageButton goods_imageButton_like;
	private ImageButton goods_imageButton_collect;
	private ImageButton goods_imageButton_back;
	private Integer currentUserid;
	private Context mContext;
	private ImageButton goods_imageButton_massege;
	
	
	GestureDetector mGestureDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_info_detail);

		mContext=GoodDetail.this;
		username = getPreferenceName();
		relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
		goods_textView_price = (TextView) findViewById(R.id.goods_textView_price);
		goods_textView_goodsName = (TextView) findViewById(R.id.goods_textView_goodsName);
		goods_imageView_userPhoto = (ImageView) findViewById(R.id.goods_imageView_userPhoto);
		goods_textView_userName = (TextView) findViewById(R.id.goods_textView_userName);
		goods_textView_infodetail = (TextView) findViewById(R.id.goods_textView_infodetail);
		goods_textView_time1 = (TextView) findViewById(R.id.goods_textView_time1);
		goods_imageButton_like=(ImageButton) findViewById(R.id.goods_imageButton_like);
		goods_imageButton_collect=(ImageButton) findViewById(R.id.goods_imageButton_collect);
		goods_imageButton_massege=(ImageButton) findViewById(R.id.goods_imageButton_massege);
		goods_imageButton_back=(ImageButton) findViewById(R.id.goods_imageButton_back);
		currentUserid = getPreferenceId();

		
		goods_imageButton_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//BmobQuery<GoodsInformation> goodsInformation
		Intent intent = getIntent();
		if(intent.getStringExtra("position")!=null){
			position = Integer.valueOf(intent.getStringExtra("position"));
			guserid = ImagesUrl.GOODSMAP.get(position).get("guserid");
			ggoodsid = ImagesUrl.GOODSMAP.get(position).get("ggoodsid");
			ggoodtype = Integer.valueOf(ImagesUrl.GOODSMAP.get(position).get("ggoodtype"));
		}else{
			guserid = intent.getStringExtra("guserid");
			ggoodsid = intent.getStringExtra("ggoodsid");
			ggoodtype = Integer.valueOf(intent.getStringExtra("ggoodtype"));
		}
		
		BmobQuery<GoodsInformation> query_good = new BmobQuery<GoodsInformation>();
		query_good.addWhereEqualTo("goodsid", ggoodsid);
		query_good.findObjects(this, new FindListener<GoodsInformation>() {
			
			@Override
			public void onSuccess(List<GoodsInformation> goodsInformations) {
				// TODO 自动生成的方法存根
				GoodsInformation goodsInformation = goodsInformations.get(0);
				maps = new HashMap<String,String>();
				maps.put("goodsname", goodsInformation.getGoodsname());
				maps.put("goodprice",String.valueOf(goodsInformation.getGoodprice()));
				if(goodsInformation.getGoodsimg1()!=null)
					maps.put("goodsimg1", goodsInformation.getGoodsimg1().getUrl());
				if(goodsInformation.getGoodsimg2()!=null)
					maps.put("goodsimg2", goodsInformation.getGoodsimg2().getUrl());
				if(goodsInformation.getGoodsimg3()!=null)
					maps.put("goodsimg3", goodsInformation.getGoodsimg3().getUrl());
				if(goodsInformation.getGoodsimg4()!=null)
					maps.put("goodsimg4", goodsInformation.getGoodsimg4().getUrl());
				if(goodsInformation.getGoodsimg5()!=null)
					maps.put("goodsimg5", goodsInformation.getGoodsimg5().getUrl());
				maps.put("goodsdescribe", goodsInformation.getGoodsdescribe());
				maps.put("pushtime", goodsInformation.getCreatedAt());
				Message msg = new Message();
				msg.what = 1;
				msg.obj = maps;
				handler.sendMessage(msg);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Log.i("info", "error!!!!!!!!!!!!!!!");
			}
		});
		
		BmobQuery<User> query_user = new BmobQuery<User>();
		query_user.addWhereEqualTo("userid", Integer.valueOf(guserid));
		Log.i("info", "guserid = "+guserid);
		query_user.findObjects(this, new FindListener<User>() {
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				
			}
			@Override
			public void onSuccess(List<User> users) {
				// TODO 自动生成的方法存根
				//Log.i("info", "users list = "+users.size());
				User user = users.get(0);
				goods_textView_userName.setText(user.getUsernick());
				final BmobFile bmobFile = user.getUserhead();
				bmobFile.download(GoodDetail.this, new DownloadFileListener() {
					@Override
					public void onSuccess(String arg0) {
					
						System.out.println("file  = "+arg0);
						Bitmap bitmap = BitmapFactory.decodeFile(arg0);
						goods_imageView_userPhoto.setImageBitmap(bitmap);	
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
					}
				});
			}
		});
		mGestureDetector = new GestureDetector(this,new mSimpleGestureDetector());
		relativelayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 自动生成的方法存根
				Log.i("info","屏幕滑动");
				mGestureDetector.onTouchEvent(event);				
				return false;
			}
		});
	
		// 查询收藏
		BmobQuery<Collections> query_collect = new BmobQuery<Collections>();
		BmobQuery<Collections> c1 = new BmobQuery<Collections>();
		BmobQuery<Collections> c2 = new BmobQuery<Collections>();
		c1.addWhereEqualTo("userid", currentUserid);
		c2.addWhereEqualTo("goodsid",ggoodsid );
		List<BmobQuery<Collections>> andQuerys=new ArrayList<BmobQuery<Collections>>();
		andQuerys.add(c1);
		andQuerys.add(c2);
		query_collect.and(andQuerys);
		query_collect.findObjects(mContext, new FindListener<Collections>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.e("info", "error arg0 = "+arg0+"error = "+arg1);
				goods_imageButton_collect.setTag(R.drawable.collect);
			}

			@Override
			public void onSuccess(List<Collections> arg0) {
				// TODO Auto-generated method stub
				if(arg0.size()==1){
					goods_imageButton_collect.setImageResource(R.drawable.collectok);
					goods_imageButton_collect.setTag(R.drawable.collectok);
				}
				else{ 
					goods_imageButton_collect.setImageResource(R.drawable.collect);
					goods_imageButton_collect.setTag(R.drawable.collect);
					Log.i("info", "R.drawable.collect = "+R.drawable.collect);
				}
			}
			
		});
		BmobQuery<Likeit> query_likeit = new BmobQuery<Likeit>();
		query_likeit.addWhereEqualTo("userid", currentUserid);
		query_likeit.addWhereEqualTo("goodsid",ggoodsid);
		query_likeit.findObjects(mContext, new FindListener<Likeit>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.e("info", "error arg0 = "+arg0+"error = "+arg1);
				goods_imageButton_like.setTag(R.drawable.like);
				
			}

			@Override
			public void onSuccess(List<Likeit> arg0) {
				// TODO Auto-generated method stub
				if(arg0.size()==1){
					goods_imageButton_like.setImageResource(R.drawable.likeok);
					goods_imageButton_like.setTag(R.drawable.likeok);
					Log.i("info", "R.drawable.likeok = "+R.drawable.likeok);
				}
				else{
					goods_imageButton_like.setImageResource(R.drawable.like);
					goods_imageButton_like.setTag(R.drawable.like);
					Log.i("info", "R.drawable.like = "+R.drawable.like);
				}
			}
		});
		
		
		//联系我
		goods_imageButton_massege.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext,ContactsActivity.class);
				mContext.startActivity(intent);
			}
		});
		
		//点赞
		goods_imageButton_like.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO 自动生成的方法存根\
				if((Integer)((ImageButton)v).getTag()==R.drawable.like){	
	
				((ImageButton)v).setImageResource(R.drawable.likeok);
				((ImageButton)v).setTag(R.drawable.likeok);
				Log.e("info", "currentUserid = "+currentUserid);
				Likeit likeit = new Likeit();
				likeit.setUserid(currentUserid);
				likeit.setGoodsid(ggoodsid);
				likeit.setType(ggoodtype);
				likeit.save(mContext, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(mContext, "谢客官点赞~", Toast.LENGTH_SHORT).show();
						
						BmobQuery<GoodsInformation> query_goods = new BmobQuery<GoodsInformation>();
						query_goods.addWhereEqualTo("goodsid",ggoodsid);
						query_goods.findObjects(mContext, new FindListener<GoodsInformation>() {
							@Override
							public void onSuccess(List<GoodsInformation> goodsInformations) {
								// TODO 自动生成的方法存根
								GoodsInformation goodsInformation = goodsInformations.get(0);
								goodsInformation.setGoodsclick(goodsInformation.goodsclick+1);
								goodsInformation.update(mContext,goodsInformation.getObjectId(),new UpdateListener() {
									@Override
									public void onSuccess() {
										// TODO 自动生成的方法存根
									}
									
									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO 自动生成的方法存根
										((ImageButton)v).setImageResource(R.drawable.like);
									}
								});				
							}
							@Override
							public void onError(int arg0, String arg1) {
								// TODO 自动生成的方法存根
								Log.i("info", "error = "+arg0+" "+arg1);
							}
						});
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						
					}
				});
			}else if((Integer)((ImageButton)v).getTag()==R.drawable.likeok){//已经是已经点赞了   要取消点赞
				((ImageButton)v).setImageResource(R.drawable.like);
				((ImageButton)v).setTag(R.drawable.like);
				Likeit likeit = new Likeit();
				likeit.setUserid(currentUserid);
				likeit.setGoodsid(ggoodsid);
				likeit.setType(ggoodtype);
				BmobQuery<Likeit> query_like = new BmobQuery<Likeit>();
				query_like.findObjects(mContext,new FindListener<Likeit>() {
					@Override
					public void onError(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Log.i("info", "error = "+arg0+" code = "+arg1);
					}
					@Override
					public void onSuccess(List<Likeit> likeits) {
						// TODO 自动生成的方法存根
						Likeit likeit = new Likeit();
						likeit.setObjectId(likeits.get(0).getObjectId());
						likeit.delete(mContext, new DeleteListener() {
							@Override
							public void onSuccess() {
								// TODO 自动生成的方法存根
								Toast.makeText(mContext, "呜呜~给人家一个赞嘛", Toast.LENGTH_SHORT).show();									
							}
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO 自动生成的方法存根
								Log.i("info", "error = "+arg0+" code = "+arg1);
							}
						});
					}
				});
			}
			}
		});
		
		//收藏
		goods_imageButton_collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO 自动生成的方法存根
				
				//没点收藏的情况
				if((Integer)((ImageButton)v).getTag()==R.drawable.collect){	
					((ImageButton)v).setImageResource(R.drawable.collectok);
					((ImageButton)v).setTag(R.drawable.collectok);
					Collections collection = new Collections();
					collection.setUserid(currentUserid);
					collection.setGoodsid(ggoodsid);
					collection.setTypeid(ggoodtype);
					
					collection.save(mContext, new SaveListener() {
					@Override
					public void onSuccess() {
						// TODO 自动生成的方法存根
						Toast.makeText(mContext, "收藏成功了哟~", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						
					}
				});
			}else if((Integer)((ImageButton)v).getTag()==R.drawable.collectok){//已经是收藏了的
				((ImageButton)v).setImageResource(R.drawable.collect);
				((ImageButton)v).setTag(R.drawable.collect);
				
//				Collections collection = new Collections();
//				collection.setUserid(currentUserid  );
//				collection.setGoodsid(ggoodsid);
//				collection.setTypeid(ggoodtype);
				BmobQuery<Collections> query_collect2 = new BmobQuery<Collections>();
				BmobQuery<Collections> cc1 = new BmobQuery<Collections>();
				BmobQuery<Collections> cc2 = new BmobQuery<Collections>();
				cc1.addWhereEqualTo("userid", currentUserid);
				cc2.addWhereEqualTo("goodsid",ggoodsid );
				List<BmobQuery<Collections>> andQuerys=new ArrayList<BmobQuery<Collections>>();
				andQuerys.add(cc1);
				andQuerys.add(cc2);
				query_collect2.and(andQuerys);
				
				query_collect2.findObjects(mContext,new FindListener<Collections>() {
					@Override
					public void onError(int arg0, String arg1) {
						// TODO 自动生成的方法存根
						Log.i("info", "error = "+arg0+" code = "+arg1);
					}
					@Override
					public void onSuccess(List<Collections> collections) {
						// TODO 自动生成的方法存根
						Collections collection = new Collections();
						collection.setObjectId(collections.get(0).getObjectId());
						collection.delete(mContext, new DeleteListener() {
							@Override
							public void onSuccess() {
								// TODO 自动生成的方法存根
								Toast.makeText(GoodDetail.this, "客官，怎么就不喜欢了呢", Toast.LENGTH_SHORT).show();									
							}
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO 自动生成的方法存根
								Log.i("info", "error = "+arg0+" code = "+arg1);
							}
						});
					}
				});
			}
			}
		});
	
	}
	class mSimpleGestureDetector extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO 自动生成的方法存根
			if(e1.getY()-e2.getY()>50){
				toast("向下滑动");
			}else{//向上滑
				toast("向上滑动");
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
	
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			Object aObject;
			switch (msg.what) {
			case 1:
				List<String> imageUris = new ArrayList<String>();
				aObject = msg.obj;
				goods_textView_goodsName.setText(((HashMap<String, String>)aObject).get("goodsname"));
				goods_textView_price.setText(((HashMap<String, String>)aObject).get("goodprice"));
				if(((HashMap<String, String>)aObject).get("goodsimg1")!=null){
					String a = ((HashMap<String, String>)aObject).get("goodsimg1"); 
					System.out.println("goodsimg1 = "+a);
					imageUris.add(a);
				}
				if(((HashMap<String, String>)aObject).get("goodsimg2")!=null){
					String a = ((HashMap<String, String>)aObject).get("goodsimg2"); 
					System.out.println("goodsimg2 = "+a);
					imageUris.add(a);
				}
				if(((HashMap<String, String>)aObject).get("goodsimg3")!=null){
					String a = ((HashMap<String, String>)aObject).get("goodsimg3"); 
					System.out.println("goodsimg3 = "+a);
					imageUris.add(a);
				}
				if(((HashMap<String, String>)aObject).get("goodsimg4")!=null){
					String a = ((HashMap<String, String>)aObject).get("goodsimg4"); 
					System.out.println("goodsimg4 = "+a);
					imageUris.add(a);
				}
				if(((HashMap<String, String>)aObject).get("goodsimg5")!=null){
					String a = ((HashMap<String, String>)aObject).get("goodsimg5"); 
					System.out.println("goodsimg5 = "+a);
					imageUris.add(a);
				}
				
				mSlideShowView = (SlideShowView) findViewById(R.id.slideshowView);
				
				mSlideShowView.setImageUris(imageUris);
				goods_textView_infodetail.setText(((HashMap<String, String>)aObject).get("goodsdescribe"));
				goods_textView_time1.setText(((HashMap<String, String>)aObject).get("pushtime"));
				break;

			default:
				break;
			}
		};
	};
}
