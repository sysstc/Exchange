package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.Certification;
import com.example.bean.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class SchoolCertificationActivity extends BaseActivity {
	private Button schoolidenti_button_bound;
	private EditText identi_editText_school;
	private EditText schoolidenti_editText_number;
	private EditText schoolidenti_editText_name;
	private ImageButton schoolidenti_imageButton_back;
	String schoolname;
	String username;
	String usernum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.school_identification);
		
		schoolidenti_editText_number=(EditText) findViewById(R.id.schoolidenti_editText_number);
		schoolidenti_button_bound=(Button) findViewById(R.id.schoolidenti_button_bound);
		identi_editText_school=(EditText) findViewById(R.id.identi_editText_school);
		schoolidenti_editText_name=(EditText) findViewById(R.id.schoolidenti_editText_name);
		schoolidenti_imageButton_back=(ImageButton) findViewById(R.id.schoolidenti_imageButton_back);
		schoolidenti_imageButton_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		schoolidenti_button_bound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				schoolname=identi_editText_school.getText().toString();
				username=schoolidenti_editText_name.getText().toString();
				usernum=schoolidenti_editText_number.getText().toString();
				
				if(!schoolname.isEmpty()&&!username.isEmpty()&&!usernum.isEmpty()){
					BmobQuery<Certification> eq1 = new BmobQuery<Certification>();
					eq1.addWhereEqualTo("schoolname", schoolname);
					BmobQuery<Certification> eq2 = new BmobQuery<Certification>();
					eq2.addWhereEqualTo("username", username);
					BmobQuery<Certification> eq3 = new BmobQuery<Certification>();
					eq3.addWhereEqualTo("usernum", usernum);
					List<BmobQuery<Certification>> andQuerys=new ArrayList<BmobQuery<Certification>>();
					andQuerys.add(eq1);
					andQuerys.add(eq2);
					andQuerys.add(eq3);
					BmobQuery<Certification> query_certification=new BmobQuery<Certification>();
					query_certification.and(andQuerys);
					query_certification.findObjects(SchoolCertificationActivity.this, new FindListener<Certification>() {
					
						@Override
						public void onSuccess(List<Certification> list) {
							// TODO Auto-generated method stub
							if(list.size()==1){
								
							Toast.makeText(SchoolCertificationActivity.this, "噢耶，验证成功了呢", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(SchoolCertificationActivity.this,MyselfActivity.class);
							startActivity(intent);
							finish();
							}
							else
							Toast.makeText(SchoolCertificationActivity.this, "呜呜，验证失败啦，再试一次", Toast.LENGTH_SHORT).show();
						}
						
						@Override
						public void onError(int arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(SchoolCertificationActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
						}
					});
				}
				else{
					Toast.makeText(SchoolCertificationActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
			}}
		});
		
		
	}

	
}
