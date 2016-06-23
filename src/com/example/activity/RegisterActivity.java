package com.example.activity;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.example.*;
import com.example.bean.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.MediaStore;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.bean.BmobSmsState;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class RegisterActivity extends BaseActivity {
	
	private String username,password,repassword,usercontact;
	private Button register_imageButton_code;
	private ImageView register_imageView_photo;
	private ImageView register_imageButton_back;
	private Button register_button_register;
	private EditText regist_editText_phone;
	private EditText register_editText_code;
	private EditText register_editText_user;
	private EditText register_editText_password;
	private EditText register_editText_password2;
	private String phone;
	private String number;
	private MyCountTimer timer;
	private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 10;
	private static final int MB = 1024*1024;
	private Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		Bmob.initialize(this, "7c9413599ddc487cd746a1c23e509637");
		BmobSMS.initialize(this, "7c9413599ddc487cd746a1c23e509637");
		register_imageButton_code=(Button) findViewById(R.id.register_imageButton_code);
		register_imageButton_back=(ImageView) findViewById(R.id.register_imageButton_back);
		regist_editText_phone=(EditText) findViewById(R.id.regist_editText_phone);
		register_button_register = (Button) findViewById(R.id.register_button_register);
		register_editText_code=(EditText) findViewById(R.id.register_editText_code);
		register_editText_user=(EditText)findViewById(R.id.register_editText_user);
		register_editText_password = (EditText) findViewById(R.id.register_editText_password);
		register_editText_password2 = (EditText) findViewById(R.id.register_editText_password2);
		register_imageView_photo = (ImageView) findViewById(R.id.register_imageView_photo);
		
		register_button_register.setBackgroundColor(Color.GRAY);
		register_button_register.setClickable(false);
		
		register_imageButton_back.setVisibility(View.VISIBLE);
		
		register_editText_code.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO 自动生成的方法存根
				verifyOrBind();
			}
		});
		
		register_imageButton_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
 				finish();
			}
		});
		register_imageButton_code.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
/*				register_imageButton_code.setClickable(false);
				register_imageButton_code.setBackgroundColor(Color.GRAY);*/
				requestSMSCode();
		}
		});
		register_button_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(verifyOrBind()){
					save();
					
					/*username = register_editText_user.getText().toString();
					phone = regist_editText_phone.getText().toString();
					password = register_editText_password.getText().toString();
					repassword = register_editText_password2.getText().toString();
					System.out.println("username = "+username+" password = "+password);
					if(username.equals("")|password.equals("")|repassword.equals("")){
						Toast.makeText(RegisterActivity.this,"昵称或密码不能为空",Toast.LENGTH_LONG).show();
					}else if(!password.equals(repassword)){
			            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
			        }else{
			        	Log.i("info", "用户插入！");
			        	BmobQuery<User> query_user = new BmobQuery<User>();
			        	User user = new User();
			        	user.setUsername(username);
			        	user.setUserpassword(password);
			        	user.setUsercontact(phone);
			        	user.setUserpassword(password);
			        	user.save(RegisterActivity.this, new SaveListener() {
							
							@Override
							public void onSuccess() {
								// TODO 自动生成的方法存根
								 savePreferenceName(username);
								 //BmobQuery<User> queryId = new BmobQuery<User>();
								 Log.i("info", "用户插入成功了！！");
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO 自动生成的方法存根
								if(arg0==401){
									Toast.makeText(RegisterActivity.this, "用户名重复了！", Toast.LENGTH_SHORT).show();
								}
							}
						});
			        }	*/				
				}	
				Intent intent = new Intent(RegisterActivity.this,MyselfActivity.class);
				startActivity(intent);
			}
		});
		register_imageView_photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	
	class MyCountTimer extends CountDownTimer{
		
		 public MyCountTimer(long millisInFuture, long countDownInterval) {  
	            super(millisInFuture, countDownInterval);  
	        }  
			@Override  
	        public void onTick(long millisUntilFinished) {  
				register_imageButton_code.setText((millisUntilFinished / 1000) +"秒后重发");  
				register_imageButton_code.setBackgroundColor(Color.GRAY);
				register_imageButton_code.setClickable(false);
	        }  
	        @Override  
	        public void onFinish() {  
	        	register_imageButton_code.setText("发送验证码");
	        	register_imageButton_code.setTextSize(16);
	        	register_imageButton_code.setBackgroundResource(R.drawable.background);
	        	register_imageButton_code.setClickable(true);
	}
	
}

	
	public boolean verifyOrBind() {
		// TODO Auto-generated method stub
		phone = regist_editText_phone.getText().toString();
		String code = register_editText_code.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast("手机号码不能为空");
			return false;
	}

		if (TextUtils.isEmpty(code)) {
			showToast("验证码不能为空");
			return false;
		}
		final ProgressDialog progress = new ProgressDialog(this);
		progress.setMessage("正在验证短信验证码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		progress.dismiss();
		MyVerify verify = new MyVerify();
		BmobSMS.verifySmsCode(this, phone, code,verify);
		return true;
	}
	class MyVerify extends VerifySMSCodeListener{
		@Override
		public void done(BmobException ex) {
			// TODO 自动生成的方法存根
			if(ex==null){
				toast("手机号码已验证");
				register_button_register.setClickable(true);
				register_button_register.setBackgroundResource(R.drawable.background);
				//bindMobilePhone(phone);
			}else if(ex.getErrorCode()==9019){
				toast("验证码输入错误！");
				Log.i("info", "验证失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
				register_button_register.setClickable(false);
				register_button_register.setBackgroundColor(Color.GRAY);
			}
		}		
	} 
	protected void requestSMSCode() {
		// 发送手机验证码
		number = regist_editText_phone.getText().toString();
		if (!TextUtils.isEmpty(number)) {
			timer = new MyCountTimer(60000, 1000);   
			timer.start();
			BmobSMS.requestSMSCode(this, number, "手机号码登陆模板", new RequestSMSCodeListener() {
				
				@Override
				public void done(Integer smsId, BmobException ex) {
					// TODO Auto-generated method stub
					if (ex == null) {// 验证码发送成功
						toast("验证码发送成功");// 用于查询本次短信发送详情
					}else{//如果验证码发送错误，可停止计时
						
						if(ex.toString().equals("cn.bmob.sms.exception.BmobException: mobilePhoneNumber Must be valid mobile number")){
							toast("请输入准确的电话号码");
							Log.i("info", "请输入准确的电话号码");
							timer.cancel();
						}else if(ex.toString().equals("cn.bmob.sms.exception.BmobException: mobile '"+number+"' send message limited.")){
							toast("一小时只能接收5条验证码信息");
							Log.i("info", "一小时只能接收5条验证码信息");
						}
						register_imageButton_code.setText("发送验证码");
						register_imageButton_code.setClickable(true);
						register_imageButton_code.setBackgroundResource(R.drawable.background);
						Log.i("info", "ex = "+ex.toString());
					}
				}
			});
		} else {
			toast("请输入手机号码");
		}
	}
	/**
	 * 图片上传
	 * */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自动生成的方法存根
		if(requestCode==1){
			 uri = data.getData();
			ContentResolver cr = this.getContentResolver();
			
			try{
				int mColor = 0;
				Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
				if(bitmap==null){
					register_imageView_photo.setImageResource(R.drawable.ic_launcher);
					return;
				}
				Bitmap mBitmap = null;
				
				mBitmap = Bitmap.createScaledBitmap(bitmap, register_imageView_photo.getWidth(),
						register_imageView_photo.getHeight(), true);
				if(mBitmap!=null&&bitmap!=null){
					saveBitmap(bitmap);
					register_imageView_photo.setImageBitmap(mBitmap);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void saveBitmap(Bitmap bm){
		if(bm==null){
			Toast.makeText(RegisterActivity.this, "313 ----- bm is null",Toast.LENGTH_SHORT).show();
			return ;
		}
		if(FREE_SD_SPACE_NEEDED_TO_CACHE>freeSpaceOnSd()){
			Toast.makeText(RegisterActivity.this, "317----SD卡空间不足！", Toast.LENGTH_SHORT).show();
			return;
		}
		username = register_editText_user.getText().toString();
		if(username==null)
			Toast.makeText(RegisterActivity.this, "322-----名字不能为空！", Toast.LENGTH_SHORT).show();


		File sdDir = Environment.getExternalStorageDirectory();
		String dirname = sdDir.toString()+"/"+"exchange/user/headimage";
		Toast.makeText(RegisterActivity.this,"327-----file is "+ dirname, Toast.LENGTH_SHORT).show();
		File dirFile = new File(dirname);

		File file = new File(dirFile+"/"+username+".jpg");
		RegisterActivity.this.deleteDatabase(dirFile+"/"+username+".jpg");
		Toast.makeText(RegisterActivity.this,"332------file is "+ file.toString(), Toast.LENGTH_SHORT).show();
		if(!dirFile.exists())
			dirFile.mkdirs();
		try {
			file.createNewFile();
			OutputStream outStream = new FileOutputStream(file);
			
			bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			Toast.makeText(RegisterActivity.this,"344-----error is "+ e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	private int freeSpaceOnSd(){
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		double sdFreeMB = ((double)stat.getAvailableBlocks()*(double)stat.getBlockSize())/MB;
		return (int)sdFreeMB;
	}
	
	private void save(){
		String filestr = UriToFile();
		File file = new File(filestr);
		Toast.makeText(RegisterActivity.this,"356----"+filestr,Toast.LENGTH_SHORT).show();
		
		final BmobFile bmobFile = new BmobFile(file);
		bmobFile.uploadblock(RegisterActivity.this, new UploadFileListener() {
			
			@Override
			public void onSuccess() {
				// TODO 自动生成的方法存根
				Toast.makeText(RegisterActivity.this,"364-----文件收藏"+bmobFile.getFileUrl(RegisterActivity.this),Toast.LENGTH_SHORT).show();;
				password = register_editText_password.getText().toString();
				Toast.makeText(RegisterActivity.this, "366------"+username+" "+password,Toast.LENGTH_SHORT).show();
				insertObject(new User(register_editText_user.getText().toString(),null,null,regist_editText_phone.getText().toString(),
						register_editText_password.getText().toString(),bmobFile,null,null,0));
				//insertObject(new UserBean(name, password, bmobFile));
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Toast.makeText(RegisterActivity.this, "375----"+arg0+" "+arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void insertObject(final BmobObject obj){
		obj.save(RegisterActivity.this, new SaveListener() {
			@Override
			public void onSuccess() {
				// TODO 自动生成的方法存根
				savePreferenceName(register_editText_user.getText().toString());
				Intent intent = new Intent(RegisterActivity.this,MyselfActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(RegisterActivity.this, "384-----创建数据成功"+obj.getObjectId(), Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Toast.makeText(RegisterActivity.this, "389------创建数据成功"+arg0+" "+arg1, Toast.LENGTH_SHORT).show();
			}
		});
	}
	public String UriToFile(){
		String myImageUri = uri.toString();
		Toast.makeText(RegisterActivity.this, "395----"+myImageUri, Toast.LENGTH_SHORT).show();
		String[] proj = {MediaStore.Images.Media.DATA};
		Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);
		int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		actualimagecursor.moveToFirst();
		String imag_path = actualimagecursor.getString(actual_image_column_index);
		return imag_path;
	}	
}

