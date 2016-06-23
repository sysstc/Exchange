package com.example.activity;


import java.io.File;
import java.io.InputStream;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.activity.R;
import com.example.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
	private EditText login_editText_user;
	private EditText login_editText_password;
	private Button ask_button_create;
	private ImageView img_userhead;
	private String username;
	private TextView create_TextView_register;
	private String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);

		login_editText_user = (EditText) findViewById(R.id.login_editText_user);
		login_editText_password = (EditText) findViewById(R.id.login_editText_password);
		ask_button_create = (Button) findViewById(R.id.ask_button_create);
		img_userhead = (ImageView) findViewById(R.id.img_userhead);
		create_TextView_register = (TextView) findViewById(R.id.create_TextView_register);
		create_TextView_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
			}
		});
		login_editText_password.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
		@Override
			public void onFocusChange(View v, boolean hasFocus) {
			// TODO 自动生成的方法存根
				if(hasFocus&&!TextUtils.isEmpty(login_editText_user.getText())){
					File sdDir = Environment.getExternalStorageDirectory();
					String filepath = sdDir.toString()+"/"+"exchange/user/headimage/"
												+login_editText_user.getText().toString()
												+".jpg";
					//new MyAsynctask().execute(filepath);
					File file = new File(filepath);
					Toast.makeText(LoginActivity.this, "file exists = "+file.exists(), Toast.LENGTH_SHORT).show();
					if(file.exists()){
						Bitmap bitmap = BitmapFactory.decodeFile(filepath);
						Bitmap mBitmap = bitmap.createScaledBitmap(bitmap, 100, 100, true);
						img_userhead.setAlpha(0.85f);
						img_userhead.setImageBitmap(mBitmap);
						AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);  
						animation.setDuration(1000);
						img_userhead.startAnimation(animation);
					}
				}else if(hasFocus&&TextUtils.isEmpty(login_editText_user.getText())){
					img_userhead.setImageResource(R.drawable.myself1);
					Toast.makeText(LoginActivity.this, "Please enter your name!", Toast.LENGTH_SHORT).show();
				}
			}
			});
		ask_button_create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				username = login_editText_user.getText().toString();
				password = login_editText_password.getText().toString();
				Log.i("info", "username = "+username);
				Log.i("info", "password = "+password);
				if(!username.isEmpty()&&!password.isEmpty()){
					 BmobQuery<User> query_user = new BmobQuery<User>();
					 query_user.addWhereEqualTo("username", username);
					 query_user.addWhereEqualTo("userpassword", password);
					 query_user.findObjects(LoginActivity.this, new FindListener<User>() {
						
						@Override
						public void onSuccess(List<User> list) {
							// TODO 自动生成的方法存根
							User user = list.get(0);
							savePreferenceId(user.getUserid());
							savePreferenceName(username);
							Toast.makeText(LoginActivity.this, "User name = "+username, Toast.LENGTH_SHORT).show();
							if(!user.getUsername().isEmpty()){
							
								Intent intent = new Intent(LoginActivity.this,MainActivity.class);
								startActivity(intent);
								finish();
							}
						}
						
						@Override
						public void onError(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("error", arg0+" "+arg1);
						}
					});		
				}
				else{
					Toast.makeText(LoginActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}