package com.example.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.activity.ContactsActivity;
import com.example.bean.Collections;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.example.activity.R;
import com.example.bean.GoodsInformation;
import com.example.bean.Likeit;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter{
	
	private ArrayList<ListViewItem> mArrayList;
	private Context mContext;
	private ImageLoader mImageLoader;
	private DisplayImageOptions mDisplayImageOptions;
	private ImageLoadingListenerImpl mImageLoadingListenerImpl;
	private ListViewItem listViewItem;
	private Integer currentUserid;
	private ViewHolder holder = null;
	private int sum = 0;
	private int flag = 0;
	
	public ListViewAdapter(ArrayList<ListViewItem> arrayList,Context context, ImageLoader imageLoader,Integer currentUserid){
		super();
		this.mArrayList = arrayList;
		this.mContext = context;
		this.mImageLoader = imageLoader;
		int deafualtImageId = R.drawable.ic_launcher;
		this.currentUserid = currentUserid;
		mDisplayImageOptions = new DisplayImageOptions.Builder()
												.showStubImage(deafualtImageId)//在图片加载的时候调用到
												.showImageForEmptyUri(deafualtImageId)
												.showImageOnFail(deafualtImageId)
												.cacheInMemory(true)
												.cacheOnDisc(true)
												.resetViewBeforeLoading()
												.build();
		mImageLoadingListenerImpl = new ImageLoadingListenerImpl();
		
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		if(mArrayList==null)
			return 0;
		else {
			return mArrayList.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		if(mArrayList==null)
			return null;
		else
			return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_goods_info_all, null,false);
			
			holder.item_imageView_userPhoto = (ImageView) convertView.findViewById(R.id.item_imageView_userPhoto);
			holder.item_textView_userName = (TextView) convertView.findViewById(R.id.item_textView_userName);
			holder.item_textView_time = (TextView) convertView.findViewById(R.id.item_textView_time);
			holder.item_imageButton_massege = (ImageButton) convertView.findViewById(R.id.item_imageButton_massege);
			/*holder.item_textView_goodsName = (TextView) convertView.findViewById(R.id.item_textView_goodsName);
			holder.item_textView_price = (TextView) convertView.findViewById(R.id.item_textView_price);*/
			holder.item_imageView_goods1 = (ImageView) convertView.findViewById(R.id.item_imageView_goods1);
			holder.item_imageView_goods2 = (ImageView) convertView.findViewById(R.id.item_imageView_goods2);
			holder.item_imageView_goods3 = (ImageView) convertView.findViewById(R.id.item_imageView_goods3);
			holder.item_imageButton_like = (ImageButton) convertView.findViewById(R.id.item_imageButton_like);
			holder.item_imageButton_collect = (ImageButton) convertView.findViewById(R.id.item_imageButton_collect);
			convertView.setTag(holder);
			
			final ViewHolder vHolder = (ViewHolder)convertView.getTag();
			
			BmobQuery<Collections> query_collect = new BmobQuery<Collections>();
			query_collect.addWhereEqualTo("userid", currentUserid);
			query_collect.addWhereEqualTo("goodsid", mArrayList.get(position).goodsid);
			query_collect.findObjects(mContext,new FindListener<Collections>() {
				@Override
				public void onError(int arg0, String arg1) {
					// TODO 自动生成的方法存根
					Log.e("info", "error arg0 = "+arg0+"error = "+arg1);
					vHolder.item_imageButton_collect.setTag(R.drawable.collect);
				}

				@Override
				public void onSuccess(List<Collections> arg0) {
					// TODO 自动生成的方法存根
					if(arg0.size()==1){
						vHolder.item_imageButton_collect.setImageResource(R.drawable.collectok);
						vHolder.item_imageButton_collect.setTag(R.drawable.collectok);
					}
					else{
						vHolder.item_imageButton_collect.setImageResource(R.drawable.collect);
						vHolder.item_imageButton_collect.setTag(R.drawable.collect);
						Log.i("info", "R.drawable.collect = "+R.drawable.collect);
					}
				}
			});
			
			BmobQuery<Likeit> query_likeit = new BmobQuery<Likeit>();
			query_likeit.addWhereEqualTo("userid", currentUserid);
			
			query_likeit.addWhereEqualTo("goodsid", mArrayList.get(position).goodsid);
			
			query_likeit.findObjects(mContext, new FindListener<Likeit>() {
				@Override
				public void onError(int arg0, String arg1) {
					// TODO 自动生成的方法存根
					Log.e("info", "error arg0 = "+arg0+"error = "+arg1);
					vHolder.item_imageButton_like.setTag(R.drawable.like);
				}
				@Override
				public void onSuccess(List<Likeit> arg0) {
					// TODO 自动生成的方法存根
					if(arg0.size()==1){
						vHolder.item_imageButton_like.setImageResource(R.drawable.likeok);
						vHolder.item_imageButton_like.setTag(R.drawable.likeok);
						Log.i("info", "R.drawable.likeok = "+R.drawable.likeok);
					}
					else{
						vHolder.item_imageButton_like.setImageResource(R.drawable.like);
						vHolder.item_imageButton_like.setTag(R.drawable.like);
						Log.i("info", "R.drawable.like = "+R.drawable.like);
					}
				}
			});
			
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		
		if(this.mArrayList!=null){
			listViewItem = this.mArrayList.get(position);
			
			if(holder.item_imageView_userPhoto!=null){
				if(listViewItem.getUserPhotoURL()==0)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.rent);
				if(listViewItem.getUserPhotoURL()==1)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.sell);
				if(listViewItem.getUserPhotoURL()==2)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.exchange);
				if(listViewItem.getUserPhotoURL()==3)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.buy); 
			}
			if(holder.item_textView_userName!=null){
				holder.item_textView_userName.setText(listViewItem.getUserNameStr());
			}
			if(holder.item_textView_time!=null){
				holder.item_textView_time.setText(listViewItem.getTimeStr());
			}
			if(holder.item_imageView_goods1!=null){
				holder.item_imageView_goods1.setVisibility(View.VISIBLE);
				String urString = mArrayList.get(position).getGoods1URL();
				if (urString==null) {
					holder.item_imageView_goods1.setVisibility(View.GONE);
				}
				else{
					try{
					mImageLoader.displayImage(urString,
							holder.item_imageView_goods1,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			if(holder.item_imageView_goods2!=null){
				holder.item_imageView_goods2.setVisibility(View.VISIBLE);
				String urString = mArrayList.get(position).getGoods2URL();
				if (urString==null) {
					holder.item_imageView_goods2.setVisibility(View.GONE);
				}else{
				try{
					mImageLoader.displayImage(urString,
							holder.item_imageView_goods2,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			if(holder.item_imageView_goods3!=null){
				holder.item_imageView_goods3.setVisibility(View.VISIBLE);
				String urString = mArrayList.get(position).getGoods3URL();
				if (urString==null) {
					holder.item_imageView_goods3.setVisibility(View.GONE);
				}else{
				try{
					mImageLoader.displayImage(urString,
							holder.item_imageView_goods3,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else{
				holder.item_imageView_goods3.setVisibility(View.GONE);
				Log.i("error", "error-------------------------2---");
			}
			
			holder.item_imageButton_massege.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					Intent intent = new Intent(mContext,ContactsActivity.class);
					mContext.startActivity(intent);
				}
			});
		if(currentUserid!=0){
			holder.item_imageButton_like.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(final View v) {
					// TODO 自动生成的方法存根\
					if((int)((ImageButton)v).getTag()==R.drawable.like){	
		
					((ImageButton)v).setImageResource(R.drawable.likeok);
					((ImageButton)v).setTag(R.drawable.likeok);
					Log.e("info", "goodsid = "+mArrayList.get(position).getGoodsid());
					Log.e("info", "type = "+mArrayList.get(position).getType());
					Log.e("info", "currentUserid = "+currentUserid);
					Likeit likeit = new Likeit();
					likeit.setUserid(currentUserid);
					likeit.setGoodsid(mArrayList.get(position).getGoodsid());
					likeit.setType(mArrayList.get(position).getType());
					likeit.save(mContext, new SaveListener() {
						@Override
						public void onSuccess() {
							// TODO 自动生成的方法存根
							Toast.makeText(mContext, "在Likeit表中添加的数据", Toast.LENGTH_SHORT).show();
							
							BmobQuery<GoodsInformation> query_goods = new BmobQuery<GoodsInformation>();
							query_goods.addWhereEqualTo("goodsid", mArrayList.get(position).getGoodsid());
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
											Toast.makeText(mContext, "添加了我喜欢！！", Toast.LENGTH_SHORT).show();
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
				}else if((int)((ImageButton)v).getTag()==R.drawable.likeok){//已经是已经点赞了
					((ImageButton)v).setImageResource(R.drawable.like);
					((ImageButton)v).setTag(R.drawable.like);
					Likeit likeit = new Likeit();
					likeit.setUserid(currentUserid);
					likeit.setGoodsid(mArrayList.get(position).getGoodsid());
					likeit.setType(mArrayList.get(position).getType());
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
									Log.i("info", "删除成功！success!!");									
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
		if(currentUserid!=0){
			holder.item_imageButton_collect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(final View v) {
					// TODO 自动生成的方法存根
					
					if((int)((ImageButton)v).getTag()==R.drawable.collect){	
						((ImageButton)v).setImageResource(R.drawable.collectok);
						((ImageButton)v).setTag(R.drawable.collectok);
						Log.e("info", "goodsid = "+mArrayList.get(position).getGoodsid());
						Log.e("info", "type = "+mArrayList.get(position).getType());
						Log.e("info", "currentUserid = "+currentUserid);
						Collections collection = new Collections();
						collection.setUserid(currentUserid);
						collection.setGoodsid(mArrayList.get(position).getGoodsid());
						collection.setTypeid(mArrayList.get(position).getType());
						collection.save(mContext, new SaveListener() {
						@Override
						public void onSuccess() {
							// TODO 自动生成的方法存根
							Toast.makeText(mContext, "在Collection表中添加的数据", Toast.LENGTH_SHORT).show();
						}
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							
						}
					});
				}else if((int)((ImageButton)v).getTag()==R.drawable.collectok){//已经是已经点赞了
					((ImageButton)v).setImageResource(R.drawable.collect);
					((ImageButton)v).setTag(R.drawable.collect);
					Collections collection = new Collections();
					collection.setUserid(currentUserid);
					collection.setGoodsid(mArrayList.get(position).getGoodsid());
					collection.setTypeid(mArrayList.get(position).getType());
					BmobQuery<Collections> query_collect = new BmobQuery<Collections>();
					query_collect.findObjects(mContext,new FindListener<Collections>() {
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
									Log.i("info", "删除成功！success!!");									
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
		}
		return convertView;
	}
	//异步加载
	public static class ImageLoadingListenerImpl extends SimpleImageLoadingListener{
		public static final List<String> displayedImages = java.util.Collections.synchronizedList(new LinkedList<String>());
		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap bitmap) {//将下载下来的bitmap弄到view上
			// TODO 自动生成的方法存根
			if(bitmap!=null){
				ImageView imageView = (ImageView)view;
				boolean isFirstDisplay = !displayedImages.contains(imageUri);
				if(isFirstDisplay){
					//图片的淡入效果
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
					System.out.println("===>loading"+imageUri);
				}
			}
		}
	}
	
	private class ViewHolder{

		
		ImageView item_imageView_userPhoto;
		TextView item_textView_userName;
		TextView item_textView_time;
		ImageButton item_imageButton_massege;
/*		TextView item_textView_goodsName;*/
/*		TextView item_textView_price;*/
		ImageView item_imageView_goods1;
		ImageView item_imageView_goods2;
		ImageView item_imageView_goods3;
		ImageButton item_imageButton_like;
		ImageButton item_imageButton_collect;
	}
}
