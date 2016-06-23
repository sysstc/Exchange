package com.example.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.activity.R;
import com.example.util.PictureSelectImageLoader;
import com.example.util.PictureSelectImageLoader.Type;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

		private static Set<String> mSelectedImg = new HashSet<String>();
		
		
		private String mDirPath;
		private List<String> mImgPaths;
		private LayoutInflater mInflater;
		
		//不把名称和文件夹路径存在一起可以节省内存
		public ImageAdapter(Context context,List<String> mDatas,String dirPath){
			this.mDirPath = dirPath;
			this.mImgPaths = mDatas;
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return mImgPaths.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return mImgPaths.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			final ViewHolder viewHolder;
			if(convertView==null){
				convertView = mInflater.inflate(R.layout.item_gridview, parent,false);
				
				viewHolder = new ViewHolder();
				viewHolder.mImg = (ImageView) convertView.findViewById(R.id.id_item_image);
				viewHolder.mSelect = (ImageButton) convertView.findViewById(R.id.id_item_select);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			//重置状态
			viewHolder.mImg.setImageResource(R.drawable.pictures_no);
			viewHolder.mSelect.setImageResource(R.drawable.picture_unselected);
			PictureSelectImageLoader.getInstance(3,Type.LIFO).loadImage(mDirPath+"/"+mImgPaths.get(position), viewHolder.mImg);
			viewHolder.mImg.setColorFilter(null);
			
			final String filePath = mDirPath+"/"+mImgPaths.get(position);
			viewHolder.mImg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					
					if(mSelectedImg.contains(filePath)){//防止同名
						mSelectedImg.remove(filePath);
						viewHolder.mImg.setColorFilter(null);
						viewHolder.mSelect.setImageResource(R.drawable.picture_unselected);
					}else{//未被选择
						mSelectedImg.add(filePath);
						viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
						viewHolder.mSelect.setImageResource(R.drawable.pictures_selected);
					}
			//		notifyDataSetChanged();
				}
			});
			if(mSelectedImg.contains(filePath)){
				viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
				viewHolder.mSelect.setImageResource(R.drawable.pictures_selected);
			}
			return convertView;
		}
		private class ViewHolder{
			ImageView mImg;
			ImageButton mSelect;
		}	
		public Set<String> getmSelectedImg() {
			return mSelectedImg;
		}
	}