package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;

import com.example.activity.R;
import com.example.adapter.ImagesUrl;
import com.example.adapter.ListViewAdapter;
import com.example.adapter.ListViewItem;
import com.example.layout.ListViewForScrollView;
//import com.example.layout.ListViewForScrollView;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnScrollListener{
	
	private ArrayAdapter<String> adapter;
	private ListViewForScrollView hotgoodlists;
	private Context mContext;
	private ArrayList<HashMap<String, String>> dataList;
	private ImageLoader mImageLoader;
	private MyScrollView myScrollView;
	private int searchLayoutTop;
	private LinearLayout home_menu3_2;
	private Button home_button_sale;
	private Button home_button_ask;
	private Button home_button_creat;
	private EditText home_editText_search;
	private Integer currentUserid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homepage);
		Bmob.initialize(this, "7c9413599ddc487cd746a1c23e509637");
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		String username = getPreferenceName();
		currentUserid = getPreferenceId();

		home_editText_search = (EditText) findViewById(R.id.home_editText_search);
		
		home_editText_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO 自动生成的方法存根
				if(actionId==EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
					Toast.makeText(MainActivity.this, "This is a toast", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});
		
		home_button_sale = (Button) findViewById(R.id.home_button_sale);
		home_button_sale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent =  new Intent(MainActivity.this,SaleActivity.class);
				startActivity(intent);
			}
		});
		home_button_ask = (Button) findViewById(R.id.home_button_ask);
		home_button_ask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent =  new Intent(MainActivity.this,AskActivity.class);
				startActivity(intent);
			}
		});
		home_button_creat = (Button) findViewById(R.id.home_button_creat);
		home_button_creat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent =  new Intent(MainActivity.this,CreateActivity.class);
				startActivity(intent);
			}
		});
		initView();
		initImageLoader(this);
		
	}
	public void initView(){
		mContext = this;
		dataList = new ArrayList<HashMap<String, String>>();
		mImageLoader = ImageLoader.getInstance();
		myScrollView = (MyScrollView) findViewById(R.id.myScrollView);
		myScrollView.setOnScrollListener(this);
		
		hotgoodlists = (ListViewForScrollView) findViewById(R.id.home_search_user_list);
		
		hotgoodlists.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(MainActivity.this,GoodDetail.class);
				intent.putExtra("position", String.valueOf(position));
				startActivity(intent);

				Toast.makeText(MainActivity.this, "Activity!!!!!!!", Toast.LENGTH_SHORT).show();
				Log.i("info", "position = "+position);
			}
		});
		
		ArrayList<ListViewItem>arrayList = new ArrayList<ListViewItem>();
		ImagesUrl imageUrls = new ImagesUrl(this, hotgoodlists,mImageLoader,currentUserid);
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
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_creat:
			Intent intent3 = new Intent(this,CreateActivity.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_myself:
			if(getPreferenceName().isEmpty()){
				Intent intent4 = new Intent(this,LoginActivity.class);
				startActivity(intent4);
				overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			}else{
				Intent intent4 = new Intent(this,MyselfActivity.class);
				overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
				startActivity(intent4);
				overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
				finish();
			}
			break;
			
		default:
			break;
		}
	}

}
