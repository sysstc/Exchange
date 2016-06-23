package com.example.adapter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.example.activity.R;
import com.example.adapter.ListViewAdapter.ImageLoadingListenerImpl;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

	private LayoutInflater mInflater;
	private Context mContext;
	private ArrayList<SaleGoodsItem> mDatas;
	private ImageLoader mImageLoader;
	private DisplayImageOptions mDisplayImageOptions;
	private ImageLoadingListenerImpl mImageLoadingListenerImpl;
	private OnItemClickListener mListener;
	public MyRecyclerAdapter(Context context,ArrayList<SaleGoodsItem> datas,ImageLoader imageLoader){
		this.mDatas = datas;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		mImageLoader = imageLoader;
		
		int deafualtImageId = R.drawable.imagedown;
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
	public interface OnItemClickListener{
		void ItemClickListener(View view,int position);
		void ItemLongClickListener(View view,int position);
	}
	public void setOnClickListener(OnItemClickListener listener){
		this.mListener = listener;
	}
	@Override
	public int getItemCount() {
		// TODO 自动生成的方法存根
		
		return mDatas.size();
	}
//创建
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO 自动生成的方法存根
		//R.layout.item_simple_view--->view
		View view = mInflater.inflate(R.layout.item, arg0,false);
		MyViewHolder viewHolder = new MyViewHolder(view);
		return viewHolder;
	}
//
	@Override
	public void onBindViewHolder(final MyViewHolder holder, int pos) {
		// TODO 自动生成的方法存根
		holder.tv_gooddescribe.setText(mDatas.get(pos).getGoodsName());
		holder.tv_goodprice.setText("$ "+(mDatas.get(pos).getGoodsPrice()));
		if(holder.imageView!=null){
			try{
				mImageLoader.displayImage(mDatas.get(pos).getGoodimg1URL(),
						holder.imageView,
						mDisplayImageOptions, 
						mImageLoadingListenerImpl);
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		if(holder.goodtype!=null){
			if(Integer.valueOf(mDatas.get(pos).getGgoodtype())==1){//卖
				holder.goodtype.setImageResource(R.drawable.saleicon);
			}else if(Integer.valueOf(mDatas.get(pos).getGgoodtype())==2){//换
				holder.goodtype.setImageResource(R.drawable.exchangeicon);
			}else if(Integer.valueOf(mDatas.get(pos).getGgoodtype())==0){//租
				holder.goodtype.setImageResource(R.drawable.renticon);
			}
		}
		if(mListener!=null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					int pos = holder.getPosition();//得到当前点击item的位置pos
					mListener.ItemClickListener(holder.itemView, pos);
				}
			});
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					// TODO 自动生成的方法存根
					int pos = holder.getPosition();
					mListener.ItemLongClickListener(holder.itemView, pos);
					return true;
				}
			});
		}
	}
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
}
//viewHolder中会有R.layout.item_simple_view中所有的控件
class MyViewHolder extends ViewHolder{
	TextView tv_gooddescribe;
	TextView tv_goodprice;
	ImageView imageView;
	ImageView goodtype;
	public MyViewHolder(View itemView) {
		super(itemView);
		tv_gooddescribe = (TextView) itemView.findViewById(R.id.gooddescribe);
		tv_goodprice = (TextView) itemView.findViewById(R.id.goodprice);
		imageView  = (ImageView) itemView.findViewById(R.id.goodimage);
		goodtype = (ImageView) itemView.findViewById(R.id.goodtype);
		// TODO 自动生成的构造函数存根
	}
	
}
