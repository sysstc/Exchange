package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class AskActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	public void submit(View v){
		switch (v.getId()) {
		case R.id.ask_imageButton_home:
			Intent intent1 = new Intent(this,MainActivity.class);
			startActivity(intent1);
			break;
		case R.id.ask_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			break;
		case R.id.ask_imageButton_create:
			Intent intent3 = new Intent(this,CreateActivity.class);
			startActivity(intent3);
			break;
		case R.id.ask_imageButton_myself:
			Intent intent4 = new Intent(this,LoginActivity.class);
			startActivity(intent4);
			break;
			
		default:
			break;
		}
	}
}
