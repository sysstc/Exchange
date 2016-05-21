package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class SaleActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	public void submit(View v){
		switch (v.getId()) {
		case R.id.sale_imageButton_ask:
			Intent intent1 = new Intent(this,AskActivity.class);
			startActivity(intent1);
			finish();
			break;
		case R.id.sale_imageButton_create:
			Intent intent2 = new Intent(this,CreateActivity.class);
			startActivity(intent2);
			finish();
			break;
		case R.id.sale_imageButton_home:
			Intent intent3 = new Intent(this,MainActivity.class);
			startActivity(intent3);
			finish();
			break;
		case R.id.sale_imageButton_myself:
			Intent intent4 = new Intent(this,LoginActivity.class);
			startActivity(intent4);
			finish();
			break;
			
		default:
			break;
		}
	}
}
