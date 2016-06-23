package com.example.adapter;

import java.util.List;

import com.example.activity.R;
import com.example.bean.FolderBean;
import com.example.util.PictureSelectImageLoader;
import com.example.util.PictureSelectImageLoader.Type;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ListImagePopupWindow extends PopupWindow{
	private int mWidth;
	private int mHeight;
	private View mConvertView;
	private ListView mListView;
	
	private List<FolderBean> mDatas;
	/**
	 * 监听哪个文件夹被点击了
	 * */
	public interface OnDirSelectedListener{
		void onSelected(FolderBean folderBean);
	}
	
	public OnDirSelectedListener mListener;
	
	public void setOnDirSelectedListener(OnDirSelectedListener mListener) {
		this.mListener = mListener;
	}

	public ListImagePopupWindow(Context context,List<FolderBean> datas){
		calWidthAndHeight(context);
		mConvertView = LayoutInflater.from(context).inflate(R.layout.popup, null);
		mDatas = datas;
		//popupwindow要显示view
		setContentView(mConvertView);
		
		setWidth(mWidth);
		setHeight(mHeight);
		setFocusable(true);
		setTouchable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new BitmapDrawable());//点击外部消失
		setTouchInterceptor(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 自动生成的方法存根
				if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
					dismiss();
					return true;
				}
				return false;
			}
		});
		initViews(context);
		initEvents();
	}

	private void initViews(Context context) {
		// TODO 自动生成的方法存根
		mListView = (ListView) mConvertView.findViewById(R.id.id_list_dir);
		mListView.setAdapter(new ListDirAdapter(context, mDatas));
	}
	private void initEvents() {
		// TODO 自动生成的方法存根
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				if(mListener!=null){
					mListener.onSelected(mDatas.get(position));
				}
			}
			
		});
	}
	/**
	 * 计算popupWindow的高度和宽度
	 * */
	private void calWidthAndHeight(Context context) {
		// TODO 自动生成的方法存根
		WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wmManager.getDefaultDisplay().getMetrics(outMetrics);
		mWidth = (int)outMetrics.widthPixels;
		mHeight = (int)(outMetrics.heightPixels*0.7);
	}
	private class ListDirAdapter extends ArrayAdapter<FolderBean>{

		private LayoutInflater mInflater;//inflate布局文件
		private List<FolderBean> mDatas;
		
		public ListDirAdapter(Context context, 	List<FolderBean> objects) {
			super(context, 0, objects);
			// TODO 自动生成的构造函数存根
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根
			ViewHolder holder = null;
			if(convertView==null){
				holder = new ViewHolder();
				
				convertView = mInflater.inflate(R.layout.item_popup, parent,false);
				
				holder.mImg = (ImageView) convertView.findViewById(R.id.id_id_dir_item_image);

				holder.mDirCount = (TextView) convertView.findViewById(R.id.id_id_dir_item_count);
				
				holder.mDirName = (TextView) convertView.findViewById(R.id.id_id_dir_item_name);
				
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			FolderBean bean = getItem(position);
			Log.i("info", "bean.getCount() = "+bean.getCount());
			Log.i("info", "bean.getName() = "+bean.getName());
			Log.i("info", "R.drawable.pictures_no = "+R.drawable.pictures_no);
			//重置
			holder.mImg.setImageResource(R.drawable.pictures_no);
			
			PictureSelectImageLoader.getInstance().loadImage(bean.getFirstImgPath(), holder.mImg);
			holder.mDirCount.setText(bean.getCount()+"张");
			holder.mDirName.setText(bean.getName());
			
			return convertView;
		}
		class ViewHolder{
			public ImageView mImg;
			public TextView mDirName;
			public TextView mDirCount;
		}
	}
}
