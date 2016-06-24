package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CreateActivity extends BaseActivity{
	private String username;
	private Integer userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_new);
	}
	public void submit(View v){
		switch (v.getId()) {
		case R.id.create_imageButton_home:
			Intent intent1 = new Intent(this,MainActivity.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.create_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.create_imageButton_ask:
			Intent intent3 = new Intent(this,AskActivity.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.create_imageButton_myself:
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
		case R.id.create_button_ask:
			Intent intent5 = new Intent(CreateActivity.this,CreateAskActivity.class);
			username = getPreferenceName();
			userid = getPreferenceId();
			Log.i("info","CreateActivity id = "+userid);
			intent5.putExtra("userid", String.valueOf(userid));
			intent5.putExtra("username", username);
			startActivity(intent5);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			break;
		case R.id.create_button_sale:
			Intent intent6 = new Intent(CreateActivity.this,CreateSaleActivity.class);
			startActivity(intent6);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			break;
		default:
			break;
		}
	}
}
