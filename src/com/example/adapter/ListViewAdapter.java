package com.example.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;




import com.example.activity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
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

public class ListViewAdapter extends BaseAdapter{
	
	private ArrayList<ListViewItem> mArrayList;
	private Context mContext;
	private ImageLoader mImageLoader;
	private DisplayImageOptions mDisplayImageOptions;
	private ImageLoadingListenerImpl mImageLoadingListenerImpl;
	
	
	
	public ListViewAdapter(ArrayList<ListViewItem> arrayList,Context context, ImageLoader imageLoader){
		super();
		this.mArrayList = arrayList;
		this.mContext = context;
		this.mImageLoader = imageLoader;
		int deafualtImageId = R.drawable.ic_launcher;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder holder = null;
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
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(this.mArrayList!=null){
			ListViewItem listViewItem = this.mArrayList.get(position);
			if(holder.item_imageView_userPhoto!=null){
				if(listViewItem.getUserPhotoURL()==0)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.rent);
				if(listViewItem.getUserPhotoURL()==1)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.buy);
				if(listViewItem.getUserPhotoURL()==2)
					holder.item_imageView_userPhoto.setImageResource(R.drawable.exchange);
				
			}
		/*	if(holder.item_imageView_userPhoto!=null){
				try{
					mImageLoader.displayImage(listViewItem.getUserPhotoURL(),
							holder.item_imageView_userPhoto,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
			}*/
			if(holder.item_textView_userName!=null){
				holder.item_textView_userName.setText(listViewItem.getUserNameStr());
			}
			if(holder.item_textView_time!=null){
				holder.item_textView_time.setText(listViewItem.getTimeStr());
			}
/*			if(holder.item_textView_goodsName!=null){
				holder.item_textView_goodsName.setText(listViewItem.getGoodsNameStr());
			}
			if(holder.item_textView_price!=null){
				holder.item_textView_price.setText(listViewItem.getPriceString());
			}
	*/		
			if(holder.item_imageView_goods1!=null){
				try{
					mImageLoader.displayImage(listViewItem.getGoods1URL(),
							holder.item_imageView_goods1,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
			}else{
				holder.item_imageView_goods1.setVisibility(View.GONE);
				Log.i("error", "error---------------------------1-");
			}
			if(holder.item_imageView_goods2!=null){
				try{
					mImageLoader.displayImage(listViewItem.getGoods2URL(),
							holder.item_imageView_goods2,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
			}else{
				holder.item_imageView_goods2.setVisibility(View.GONE);
				Log.i("error", "error-------------------------2---");
			}
			if(holder.item_imageView_goods3!=null){
				try{
					mImageLoader.displayImage(listViewItem.getGoods3URL(),
							holder.item_imageView_goods3,
							mDisplayImageOptions, 
							mImageLoadingListenerImpl);
					}catch(Exception e){
						e.printStackTrace();
					}
			}else{
				holder.item_imageView_goods3.setVisibility(View.GONE);
				Log.i("error", "error---------------------------3-");
			}
			
			holder.item_imageButton_massege.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					((ImageButton)v).setImageResource(R.drawable.create);
				}
			});
			
			holder.item_imageButton_like.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					((ImageButton)v).setImageResource(R.drawable.create);
				}
			});
			
			holder.item_imageButton_collect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					((ImageButton)v).setImageResource(R.drawable.create);
				}
			});
			
		}
		return convertView;
	}
	//异步加载
	public static class ImageLoadingListenerImpl extends SimpleImageLoadingListener{
		public static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
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
