package com.example.activity;

import com.example.adapter.ImagesUrl;
import com.example.layout.ListViewForScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AskActivity extends BaseActivity{
	private Button  ask_button_create;
	private ListViewForScrollView askgoodlists;
	private ImageLoader mImageLoader;
	private int currentUserid;
	private EditText ask_editText_search;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ask);
		mImageLoader = ImageLoader.getInstance();
		currentUserid = getPreferenceId();
		Log.i("info", "AskActivity userid = "+currentUserid);
		ask_button_create = (Button) findViewById(R.id.ask_button_create);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		ask_button_create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(AskActivity.this,CreateAskActivity.class);
				intent.putExtra("username", getPreferenceName());
				intent.putExtra("userid", String.valueOf(getPreferenceId()));
				Log.i("info", "AskActivity userid = "+String.valueOf(getPreferenceId()));
				startActivity(intent);
			}
		});
		
		askgoodlists = (ListViewForScrollView) findViewById(R.id.ask_search_user_list);
		
		askgoodlists.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(AskActivity.this,GoodDetail.class);
				intent.putExtra("position", String.valueOf(position));
				startActivity(intent);

				Toast.makeText(AskActivity.this, "Activity!!!!!!!", Toast.LENGTH_SHORT).show();
				Log.i("info", "position = "+position);
			}
		});
		ask_editText_search = (EditText) findViewById(R.id.ask_editText_search);
		
		ask_editText_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,KeyEvent event)  {                          
				if (!ask_editText_search.getText().toString().equals("")&&!ask_editText_search.getText().toString().contains(" ")&&(actionId==EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))) {             //   
					String findwords = ask_editText_search.getText().toString();
					ImagesUrl imageUrls = new ImagesUrl(AskActivity.this, askgoodlists,mImageLoader,currentUserid,0,findwords);
					toast("11111111111111111");//do something;              
					return true;             
				}               
			return false;           
			}       
		});
		
		Log.i("info", "AskActivity userid = "+currentUserid);
		ImagesUrl imageUrls = new ImagesUrl(this, askgoodlists,mImageLoader,currentUserid,0,null);
	}
	
	public void submit(View v){
		switch (v.getId()) {
		case R.id.ask_imageButton_home:
			Intent intent1 = new Intent(this,MainActivity.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.ask_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.ask_imageButton_create:
			Intent intent3 = new Intent(this,CreateActivity.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.ask_imageButton_myself:
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
