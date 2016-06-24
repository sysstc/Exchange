package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class IssuanceActivity extends FragmentActivity
{
	private LinearLayout layout;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mDatas;
	
	private int mScreen1_3;

	private int mCurrentPageIndex;

	private TextView mIssuanceSale;
	private TextView mIssuanceAsk;
	private TextView mIssuanceTitle;
	private ImageButton mAsk;  
    private ImageButton mSale;
    private Integer userid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_issuance);
		userid = Integer.parseInt(getIntent().getStringExtra("userid"));//userid
		Log.i("info", "IssuanceActivity userid = "+userid);
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
			mViewPager = (ViewPager) findViewById(R.id.my_issuance_viewpage);
			mIssuanceSale = (TextView) findViewById(R.id.issuance_textView_sale);
			mIssuanceAsk = (TextView) findViewById(R.id.issuance_textView_ask);
			mIssuanceTitle = (TextView) findViewById(R.id.issuance_textView_title);
			mAsk = (ImageButton) findViewById(R.id.issuance_imageButton_ask);  
		    mSale = (ImageButton) findViewById(R.id.issuance_imageButton_sale); 
		    ListView issuance_sale_listView = (ListView) findViewById(R.id.issuance_sale_listView);
			//mChatLinearLayout = (LinearLayout) findViewById(R.id.createAsk_ll_chat);
		    
		  //  IssuanceSaleMainTabFragment.issuanceSaleMainTabFragment.setUserid("1");
			mDatas = new ArrayList<Fragment>();

			IssuanceSaleMainTabFragment tab01 = new IssuanceSaleMainTabFragment(userid);
			IssuanceAskMainTabFragment tab02 = new IssuanceAskMainTabFragment(userid);

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
						mIssuanceSale.setTextColor(Color.parseColor("#f67480"));
						resetImg(); 
						mSale.setImageResource(R.drawable.issuancesale1);  
						break;
					case 1:
						mIssuanceAsk.setTextColor(Color.parseColor("#f67480"));
						resetImg(); 
						mAsk.setImageResource(R.drawable.issuanceask1); 
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
    		case R.id.issuance_sale:  
    			mViewPager.setCurrentItem(0);  
    		    resetImg();  
    		    mSale.setImageResource(R.drawable.issuancesale1); 
    		    break;
    		case R.id.issuance_ask:  
	            mViewPager.setCurrentItem(1);  
	            resetImg();  
	            mAsk.setImageResource(R.drawable.issuanceask1);  
	            break;  
    		case R.id.issuance_imageButton_back: 
    			finish();
    			break;
    		 default:  
 	            break; 
	    	}
	    }


		protected void resetTextView()
		{
			mIssuanceAsk.setTextColor(Color.BLACK);
			mIssuanceSale.setTextColor(Color.BLACK);
			
		}
		private void resetImg() 
		{
			mSale.setImageResource(R.drawable.issuancesale);  
	        mAsk.setImageResource(R.drawable.issuanceask);
		}
		
	}


