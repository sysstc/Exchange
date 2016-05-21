package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;

import com.example.activity.R;
import com.example.adapter.ImagesUrl;
import com.example.adapter.ListViewItem;
import com.example.layout.ListViewForScrollView;
import com.example.layout.MyScrollView;
import com.example.layout.MyScrollView.OnScrollListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnScrollListener{
	
	private ArrayAdapter<String> adapter;
	private ListView hotgoodlists;
	private Context mContext;
	private ArrayList<HashMap<String, String>> dataList;
	private ImageLoader mImageLoader;
	private MyScrollView myScrollView;
	private int searchLayoutTop;
	private LinearLayout home_menu3_2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		Bmob.initialize(this, "7c9413599ddc487cd746a1c23e509637");
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		String username = getPreferenceName();
		initView();
		initImageLoader(this);
	}
	public void initView(){
		mContext = this;
		dataList = new ArrayList<HashMap<String, String>>();
		mImageLoader = ImageLoader.getInstance();
		myScrollView = (MyScrollView) findViewById(R.id.myScrollView);
		myScrollView.setOnScrollListener(this);
		hotgoodlists = (ListView) findViewById(R.id.home_search_user_list);
		ArrayList<ListViewItem>arrayList = new ArrayList<ListViewItem>();
		ImagesUrl imageUrls = new ImagesUrl(this, hotgoodlists,mImageLoader);
		
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO 自动生成的方法存根
	/*	super.onWindowFocusChanged(hasFocus);
		if(hasFocus){  
		      searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置
		    }*/
	}
	@Override
	public void onScroll(int scrollY) {
		// TODO 自动生成的方法存根
	/*	 if(scrollY >= searchLayoutTop){  
		      if (search_edit.getParent()!=search01) {
		        search02.removeView(search_edit);
		        search01.addView(search_edit);
		      }
		    }else{
		      if (search_edit.getParent()!=search02) {
		        search01.removeView(search_edit);
		        search02.addView(search_edit);
		      }
		    }*/
	}
	/*
	 * This configuration tuning is custom
	 * */
    public static void initImageLoader(Context context) {
    ImageLoaderConfiguration config = new ImageLoaderConfiguration
                      .Builder(context)
                          .threadPriority(Thread.NORM_PRIORITY - 2)
                      .denyCacheImageMultipleSizesInMemory()
                      .discCacheFileNameGenerator(new Md5FileNameGenerator())
                      .tasksProcessingOrder(QueueProcessingType.LIFO)
                      .build();
    ImageLoader.getInstance().init(config);
  }
  
  
  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mImageLoader!=null) {
      mImageLoader.clearMemoryCache();
      mImageLoader.clearDiscCache();
    }
  }
	public void submit(View v){
		switch (v.getId()) {
		case R.id.home_imageButton_ask:
			Intent intent1 = new Intent(this,AskActivity.class);
			startActivity(intent1);
			finish();
			break;
		case R.id.home_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			finish();
			break;
		case R.id.home_imageButton_creat:
			Intent intent3 = new Intent(this,CreateActivity.class);
			startActivity(intent3);
			finish();
			break;
		case R.id.home_imageButton_myself:
			if(getPreferenceName().isEmpty()){
				Intent intent4 = new Intent(this,LoginActivity.class);
				startActivity(intent4);
			}else{
				Intent intent4 = new Intent(this,MyselfActivity.class);
				startActivity(intent4);
			}
			finish();
			break;
			
		default:
			break;
		}
	}

}
