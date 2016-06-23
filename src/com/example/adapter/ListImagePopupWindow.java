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
	 * �����ĸ��ļ��б������
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
		//popupwindowҪ��ʾview
		setContentView(mConvertView);
		
		setWidth(mWidth);
		setHeight(mHeight);
		setFocusable(true);
		setTouchable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new BitmapDrawable());//����ⲿ��ʧ
		setTouchInterceptor(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		mListView = (ListView) mConvertView.findViewById(R.id.id_list_dir);
		mListView.setAdapter(new ListDirAdapter(context, mDatas));
	}
	private void initEvents() {
		// TODO �Զ����ɵķ������
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				if(mListener!=null){
					mListener.onSelected(mDatas.get(position));
				}
			}
			
		});
	}
	/**
	 * ����popupWindow�ĸ߶ȺͿ��
	 * */
	private void calWidthAndHeight(Context context) {
		// TODO �Զ����ɵķ������
		WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wmManager.getDefaultDisplay().getMetrics(outMetrics);
		mWidth = (int)outMetrics.widthPixels;
		mHeight = (int)(outMetrics.heightPixels*0.7);
	}
	private class ListDirAdapter extends ArrayAdapter<FolderBean>{

		private LayoutInflater mInflater;//inflate�����ļ�
		private List<FolderBean> mDatas;
		
		public ListDirAdapter(Context context, 	List<FolderBean> objects) {
			super(context, 0, objects);
			// TODO �Զ����ɵĹ��캯�����
			mInflater = LayoutInflater.from(context);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO �Զ����ɵķ������
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
			//����
			holder.mImg.setImageResource(R.drawable.pictures_no);
			
			PictureSelectImageLoader.getInstance().loadImage(bean.getFirstImgPath(), holder.mImg);
			holder.mDirCount.setText(bean.getCount()+"��");
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
