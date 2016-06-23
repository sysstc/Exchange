package com.example.activity;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;  
import java.util.List;  
import java.util.zip.Inflater;  

import android.support.v4.view.PagerAdapter;  
import android.support.v4.view.ViewPager;  
import android.view.LayoutInflater;  
import android.view.ViewGroup;  

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateAskActivity extends FragmentActivity {
	
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mDatas;

	private TextView mAskTextView1;
	private TextView mAskTextView2;
	private TextView mAskTextView3;
	private LinearLayout mChatLinearLayout;


	private ImageView mTabline;
	private int mScreen1_3;

	private int mCurrentPageIndex;
	private String username;
	private Integer userid;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_ask);
       
    	initTabLine();
		initView();
    }
        
    public void submit(View v){
    	switch(v.getId()){
    		case R.id.createAsk_imageButton_back:    				
    			{	
    				Intent intent1 = new Intent(CreateAskActivity.this,CreateActivity.class);
    				startActivity(intent1);
    				break;
    			}
    		default:
    			break;
    		}
    	}
    public void submit_ask(View v){
    	switch (v.getId()) {
    		case R.id.createAsk_ll_chat:
    			mViewPager.setCurrentItem(0);
    			break;
    		case R.id.createAsk_text2:
    			mViewPager.setCurrentItem(1);
    			break;
    		case R.id.createAsk_text3:
    			mViewPager.setCurrentItem(2);
			break;
		default:
			break;
		}
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
   private void initTabLine()	//
	{
		mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_3 = outMetrics.widthPixels / 3;
		LayoutParams lp = mTabline.getLayoutParams();
		lp.width = mScreen1_3;
		mTabline.setLayoutParams(lp);
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.createAsk_viewpager);
		mAskTextView1 = (TextView) findViewById(R.id.createAsk_bottomMenu_text1);
		mAskTextView2 = (TextView) findViewById(R.id.createAsk_bottomMenu_text2);
		mAskTextView3 = (TextView) findViewById(R.id.createAsk_bottomMenu_text3);
		//mChatLinearLayout = (LinearLayout) findViewById(R.id.createAsk_ll_chat);

		mDatas = new ArrayList<Fragment>();

		AskInfo1MainTabFragment tab01 = new AskInfo1MainTabFragment();
		AskInfo2MainTabFragment tab02 = new AskInfo2MainTabFragment();
		AskInfo3MainTabFragment tab03 = new AskInfo3MainTabFragment();

		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		userid = Integer.valueOf(intent.getStringExtra("userid"));
		
		mDatas.add(tab01);
		mDatas.add(tab02);
		mDatas.add(tab03);
		Log.i("info", "133---Cre"+username);
		tab01.setUsername(username);
		tab01.setUserId(userid);
		tab02.setUsername(username);
		tab02.setUserId(userid);
		tab03.setUsername(username);
		tab03.setUserId(userid);
		
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mDatas.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mDatas.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				resetTextView();
				switch (position)
				{
				case 0:
					mAskTextView1.setTextColor(Color.parseColor("#f67480"));
					break;
				case 1:
					mAskTextView2.setTextColor(Color.parseColor("#f67480"));
					break;
				case 2:
					mAskTextView3.setTextColor(Color.parseColor("#f67480"));
					break;

				}

				mCurrentPageIndex = position;

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPx)
			{
				Log.e("TAG", position + " , " + positionOffset + " , "
						+ positionOffsetPx);

				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
						.getLayoutParams();

				if (mCurrentPageIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
							* mScreen1_3);
				} else if (mCurrentPageIndex == 1 && position == 0)// 1->0
				{
					lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
							* mScreen1_3);
				} else if (mCurrentPageIndex == 1 && position == 1) // 1->2
				{
					lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset
							* mScreen1_3);
				} else if (mCurrentPageIndex == 2 && position == 1) // 2->1
				{
					lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + ( positionOffset-1)
							* mScreen1_3);
				}
				mTabline.setLayoutParams(lp);

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// TODO Auto-generated method stub

			}
		});

	}

	protected void resetTextView()
	{
		mAskTextView1.setTextColor(Color.BLACK);
		mAskTextView2.setTextColor(Color.BLACK);
		mAskTextView3.setTextColor(Color.BLACK);
	}

}


