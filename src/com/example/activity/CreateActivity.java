package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new);
	}
	public void submit(View v){
		switch (v.getId()) {
		case R.id.create_imageButton_home:
			Intent intent1 = new Intent(this,MainActivity.class);
			startActivity(intent1);
			finish();
			break;
		case R.id.create_imageButton_sale:
			Intent intent2 = new Intent(this,SaleActivity.class);
			startActivity(intent2);
			finish();
			break;
		case R.id.create_imageButton_ask:
			Intent intent3 = new Intent(this,AskActivity.class);
			startActivity(intent3);
			finish();
			break;
		case R.id.create_imageButton_myself:
			Intent intent4 = new Intent(this,LoginActivity.class);
			startActivity(intent4);
			finish();
			break;
			
		default:
			break;
		}
	}
}
