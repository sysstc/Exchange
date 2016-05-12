package com.example.activity;

import java.util.List;

import cn.bmob.v3.Bmob;

import com.example.activity.R;
import com.example.layout.ListViewForScrollView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity{
	
	private ArrayAdapter<String> adapter;
	private ListViewForScrollView listView;
	private ImageButton home_imageButton_myself;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		Bmob.initialize(this, "7c9413599ddc487cd746a1c23e509637");
		listView = (ListViewForScrollView) findViewById(R.id.home_search_user_list);
		home_imageButton_myself = (ImageButton) findViewById(R.id.home_imageButton_myself);
		home_imageButton_myself.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
		String username = getPreferenceName();
		if(!username.isEmpty())
			Toast.makeText(MainActivity.this, "username = "+username, Toast.LENGTH_SHORT).show();
	}

}
