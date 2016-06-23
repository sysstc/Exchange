package com.example.activity;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.R.integer;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;

import com.example.adapter.ImageAdapter;
import com.example.adapter.ListImagePopupWindow;
import com.example.adapter.ListImagePopupWindow.OnDirSelectedListener;
import com.example.bean.FolderBean;
import com.example.util.PictureSelectImageLoader;

public class PictureSelectorActivity extends BaseActivity{
	
	
	private GridView mGridView;
	private List<String> mImgs;
	private ImageAdapter mImageAdapter;
	
	private  Set<String> imgsSet;
	
	private RelativeLayout mBottomLy;
	private TextView mDirName;
	private TextView mDirCount;
	
	private File mCurrentDir;
	private int mMaxCount;
	
	private Set<String> mDirPaths = new HashSet<String>();//防止重复遍历
	
	private List<FolderBean> mFolderBeans = new ArrayList<FolderBean>();
	
	private ProgressDialog mProgressDialog;
	private static int DATA_LOADED = 0x110;
	private Set<String> SelectionImage;
	private ListImagePopupWindow mDirPopupWindow;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what ==DATA_LOADED){
				mProgressDialog.dismiss();
				//绑定数据到View中
				data2View();
				
				initDirPopupWindow();
			}
		}

	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_main);
		
		initView();
		initDatas();
		initEvent();
		Log.d("info","PictureSelectorActivity username = "+getPreferenceName()+" userid = "+getPreferenceId());
	}
	protected void initDirPopupWindow() {
		// TODO 自动生成的方法存根
		Log.i("info", "mFolderBeans = "+mFolderBeans.size()+" name = "+mFolderBeans.get(1).getName());
		mDirPopupWindow = new ListImagePopupWindow(this, mFolderBeans);
		mDirPopupWindow.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO 自动生成的方法存根
				lightOn();
			}
		});
		mDirPopupWindow.setOnDirSelectedListener(new OnDirSelectedListener() {
			
			@Override
			public void onSelected(FolderBean folderBean) {
				// TODO 自动生成的方法存根
				mCurrentDir = new File(folderBean.getDir());
				mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String filename) {
						// TODO 自动生成的方法存根
						if(filename.endsWith(".jpg")||
								filename.endsWith(".jpeg")||
								filename.endsWith(".png"))
							return true;
						return false;
					}
				}));
				mImageAdapter = new ImageAdapter(PictureSelectorActivity.this, mImgs, mCurrentDir.getAbsolutePath());
				mGridView.setAdapter(mImageAdapter);
				mDirCount.setText(mImgs.size()+"张");
				mDirName.setText(folderBean.getName());
				mDirPopupWindow.dismiss();
			}
		});
	}
	/**
	 * 内容区域变亮
	 * */
	protected void lightOn() {
		// TODO 自动生成的方法存根
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);
	}
	/**
	 * 内容区域变暗 
	 * */
	protected void lightOff() {
		// TODO 自动生成的方法存根
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.3f;
		getWindow().setAttributes(lp);		
	}
	
	protected void data2View() {
		// TODO 自动生成的方法存根
		if(mCurrentDir==null){
			Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
			return;
		}
		mImgs = Arrays.asList(mCurrentDir.list());
		mImageAdapter = new ImageAdapter(PictureSelectorActivity.this,mImgs, mCurrentDir.getAbsolutePath());
		mGridView.setAdapter(mImageAdapter);
		mDirCount.setText(mMaxCount+"张");
		mDirName.setText(mCurrentDir.getName());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
		if((keyCode==KeyEvent.KEYCODE_BACK)){
				
    			imgsSet  = mImageAdapter.getmSelectedImg();
				Log.i("info", "imgsSet = "+imgsSet);
				Log.i("info", "class = "+getIntent().getStringExtra("contextActivity").toString());
				if(getIntent().getStringExtra("contextActivity").toString().equals("saleInfo1"))
					SaleInfo1MainTabFragment.saleInfo1MainTabFragment.setPicture(imgsSet,getPreferenceId());
				else if(getIntent().getStringExtra("contextActivity").toString().equals("saleInfo2"))
					SaleInfo2MainTabFragment.saleInfo2MainTabFragment.setPicture(imgsSet, getPreferenceId());
				else if(getIntent().getStringExtra("contextActivity").toString().equals("saleInfo3"))
					SaleInfo3MainTabFragment.saleInfo3MainTabFragment.setPicture(imgsSet, getPreferenceId());
			}
		return super.onKeyDown(keyCode, event);
	}
	

	/**
	 * 初始化UI控件
	 * */
	private void initView(){
		mGridView = (GridView) findViewById(R.id.id_gridView);
		mDirName = (TextView) findViewById(R.id.id_choose_dir);
		mDirCount = (TextView) findViewById(R.id.id_total_count);
		mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
		
	}
	/**
	 * 利用ContentProvider扫描手机中的图片
	 * 开启单独的线程去遍历存储卡中的图片
	 * */
	private void initDatas() {
		// TODO 自动生成的方法存根
		
	}
	/**
	 * 点击事件popwindow
	 * */
	private void initEvent(){
		
		mBottomLy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				mDirPopupWindow.setAnimationStyle(R.style.dir_popupwindow_anim);
				mDirPopupWindow.showAsDropDown(mBottomLy,0,0);
				
				lightOff();
			}
		});
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
		{
			Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = PictureSelectorActivity.this
						.getContentResolver();

				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext())
				{
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					Log.e("TAG", path);
					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = path;
					// 获取该图片的父路径名
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					FolderBean imageFloder = null;
					// 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
					if (mDirPaths.contains(dirPath))
					{
						continue;
					} else
					{
						mDirPaths.add(dirPath);
						// 初始化imageFloder
						imageFloder = new FolderBean();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImgPath(path);
					}

					int picSize = parentFile.list(new FilenameFilter()
					{
						@Override
						public boolean accept(File dir, String filename)
						{
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;

					imageFloder.setCount(picSize);
					mFolderBeans.add(imageFloder);

					if (picSize > mMaxCount)
					{
						mMaxCount = picSize;
						mCurrentDir = parentFile;
					}
				}
				mCursor.close();

				// 扫描完成，辅助的HashSet也就可以释放内存了
				mDirPaths = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();
	}
}
