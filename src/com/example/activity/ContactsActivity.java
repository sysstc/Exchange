package com.example.activity;

import android.support.v7.app.ActionBarActivity;
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

public class 	ContactsActivity extends FragmentActivity
{
	private LinearLayout layout;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mDatas;
	
	private int mScreen1_3;

	private int mCurrentPageIndex;


	private TextView mContactsMassege;
	private TextView mContactsFriend;
	private TextView mContactsTitle;
	private ImageButton mMassege;  
    private ImageButton mFriend;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contacts);
   
		initView();
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
    
	    private void initView()
		{
			mViewPager = (ViewPager) findViewById(R.id.contacts_viewpager);
			mContactsMassege = (TextView) findViewById(R.id.contacts_textView_massege);
			mContactsFriend = (TextView) findViewById(R.id.contacts_textView_friend);
			mContactsTitle = (TextView) findViewById(R.id.contacts_textView_title);
			mMassege = (ImageButton) findViewById(R.id.contacts_imageButton_massege);  
		    mFriend = (ImageButton) findViewById(R.id.contacts_imageButton_friend); 
			//mChatLinearLayout = (LinearLayout) findViewById(R.id.createAsk_ll_chat);

			mDatas = new ArrayList<Fragment>();

			ContactsInfoMainTabFragment tab01 = new ContactsInfoMainTabFragment();
			ContactsFriendsMainTabFragment tab02 = new ContactsFriendsMainTabFragment();

			mDatas.add(tab01);
			mDatas.add(tab02);

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
						mContactsMassege.setTextColor(Color.parseColor("#f67480"));
						mContactsTitle.setText("œ˚œ¢"); 
						resetImg(); 
						mMassege.setImageResource(R.drawable.contacts);  
						break;
					case 1:
						mContactsFriend.setTextColor(Color.parseColor("#f67480"));
						mContactsTitle.setText("∫√”—");
						resetImg(); 
						mFriend.setImageResource(R.drawable.friend); 
						break;
			

					}

				}

				@Override  
		           public void onPageScrolled(int arg0, float arg1, int arg2) {
					
		           }  
		
		         @Override  
		           public void onPageScrollStateChanged(int arg0) {
		        	
		           }
			});
			
		}
	    
	    public void submit(View v){
	    	
	    	switch(v.getId()){
    		case R.id.contacts_massege:  
    			 mViewPager.setCurrentItem(0);  
    		     resetImg();  
    		     mMassege.setImageResource(R.drawable.contacts); 
    		     break;
    		case R.id.contacts_friend:  
	            mViewPager.setCurrentItem(1);  
	            resetImg();  
	            mFriend.setImageResource(R.drawable.friend);  
	            break;  
    		case R.id.contacts_imageButton_back: 
    			finish();
    			break;
    		 default:  
 	            break; 
	    	}
	    }


		protected void resetTextView()
		{
			mContactsMassege.setTextColor(Color.BLACK);
			mContactsFriend.setTextColor(Color.BLACK);
			
		}
		private void resetImg() 
		{
			mMassege.setImageResource(R.drawable.contacts1);  
	        mFriend.setImageResource(R.drawable.friend1);
		}
		
	}


