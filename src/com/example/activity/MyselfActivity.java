package com.example.activity;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyselfActivity extends BaseActivity{
	private TextView myself_textView_name1;//真实姓名
	private TextView myself_textView_name2;//昵称
	private ImageView myself_imageView;//头像
	private ImageButton imgbutton_exit;
	private String username;
	private String userheadUrl;
	
	private LinearLayout myself_menu3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myself);
		myself_textView_name1 = (TextView) findViewById(R.id.myself_textView_name1);
		myself_textView_name2 = (TextView) findViewById(R.id.myself_textView_name2);
		myself_imageView = (ImageView) findViewById(R.id.myself_imageView);
		myself_menu3 = (LinearLayout) findViewById(R.id.myself_menu3);
		imgbutton_exit = (ImageButton) findViewById(R.id.imgbutton_exit);
		if (!getPreferenceName().isEmpty()) {
			username = getPreferenceName();	
			Toast.makeText(MyselfActivity.this, "username = "+username, Toast.LENGTH_SHORT).show();
		}
		myself_menu3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(MyselfActivity.this,PublishNewsActivity.class);
				startActivity(intent);
			}
		});
		imgbutton_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(MyselfActivity.this)
				.setTitle("注销")
				.setMessage("确定要注销吗？")
				.setPositiveButton("注销", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						savePreferenceName("");
						username="";
						savePreferenceId(0);
						
						finish();
						Intent intent = new Intent(MyselfActivity.this,MainActivity.class);
						startActivity(intent);
						finish();
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).show();
			}
		});
		
		
		BmobQuery<User> query_user = new BmobQuery<User>();
		query_user.addWhereEqualTo("username", username);
		query_user.findObjects(this, new FindListener<User>() {
			@Override
			public void onSuccess(List<User> users) {
				// TODO 自动生成的方法存根
				myself_textView_name1.setText(users.get(0).getUsernick());
				myself_textView_name2.setText(users.get(0).getUsername());
				File sdDir = Environment.getExternalStorageDirectory();
				String filepath = sdDir.toString()+"/"+"exchange/user/headimage/"
										+username+".jpg";
				File file = new File(filepath);
				if(file.exists()){
					Bitmap bitmap = BitmapFactory.decodeFile(filepath);
					Bitmap mBitmap = bitmap.createScaledBitmap(bitmap, 70, 70, true);
					myself_imageView.setImageBitmap(mBitmap);	
				}else if(users.get(0).getUserhead()!=null){
					BmobFile bmobFile =  users.get(0).getUserhead();
					File saveFile = new File(Environment.getExternalStorageDirectory()
										+"/exchange/user/headimage/"+username+".jpg");
					bmobFile.download(MyselfActivity.this, saveFile,new DownloadFileListener() {
						@Override
						public void onSuccess(String savePath) {
							// TODO 自动生成的方法存根
							Bitmap bitmap = BitmapFactory.decodeFile(savePath);
							Bitmap mBitmap = bitmap.createScaledBitmap(bitmap, 70, 70, true);
							myself_imageView.setImageBitmap(mBitmap);	
						}
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("info", arg0+arg1);
						}
					});
					
				}
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Log.i("info", arg0+arg1);
			}
		});
	}
	
	public void submit(View v){
		switch (v.getId()) {
		case R.id.home_imageButton_home:
			Intent intent1 = new Intent(this,MainActivity.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_ask:
			Intent intent2 = new Intent(this,AskActivity.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_sale:
			Intent intent3 = new Intent(this,SaleActivity.class);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
		case R.id.home_imageButton_creat:
			Intent intent4 = new Intent(this,CreateActivity.class);
			startActivity(intent4);
			overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			finish();
			break;
			
		default:
			break;
		}
	}
}
