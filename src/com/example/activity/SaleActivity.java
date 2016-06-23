package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;

import com.example.adapter.ImageList;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.GoodsInformation;
import com.example.layout.ItemGridLayoutManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class SaleActivity extends BaseActivity{
	private Button sale_button_create;
	private ImageLoader mImageLoader;//com.nostra13.universalimageloader.core.ImageLoader
	private RecyclerView mRecyclerView;
    private List<String> lists;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sale);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		sale_button_create = (Button) findViewById(R.id.sale_button_create);
		sale_button_create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(SaleActivity.this,CreateSaleActivity.class);
				startActivity(intent);
			}
		});
   	    initData();
	   
		
	}
	private void initData() {
/*        lists = new ArrayList();

        for (int i = 0; i < 13; i++) {
            lists.add("" + i);
        }*/
		mImageLoader = ImageLoader.getInstance();
		mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        ImageList imageList = new ImageList(this,0,mRecyclerView,mImageLoader);
        mRecyclerView.setLayoutManager(new ItemGridLayoutManager(this, 2));//设置RecyclerView布局管理器为2列垂直排布        
        
    }
	
	public void submit(View v){
		switch (v.getId()) {
		case R.id.sale_imageButton_ask:
			Intent intent1 = new Intent(this,AskActivity.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.sale_imageButton_create:
			Intent intent2 = new Intent(this,CreateActivity.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.sale_imageButton_home:
			Intent intent3 = new Intent(this,MainActivity.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.sale_imageButton_myself:
			if(getPreferenceName().isEmpty()){
				Intent intent4 = new Intent(this,LoginActivity.class);
				startActivity(intent4);
				overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			}else{
				Intent intent4 = new Intent(this,MyselfActivity.class);
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
