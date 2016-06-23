package com.example.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity{
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static String TAG = "bmob";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO
        super.onCreate(savedInstanceState);

    }

    /**
     * 将此次登陆的账户信息存储下来
     * */
    public void savePreferenceName(String value) {
        // 获取SharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // 获取editor
        SharedPreferences.Editor editor = preference.edit();
        // 存入数据
        editor.putString(USER_NAME, value);
        // 提交存入文件中
        editor.commit();
    }
    public void savePreferenceId(Integer value) {
        // 获取SharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // 获取editor
        SharedPreferences.Editor editor = preference.edit();
        // 存入数据
        editor.putInt(USER_ID, value);
        // 提交存入文件中
        editor.commit();
    }
    /**
     * 获取存入SharedPreference中的账户
     *
     * @return
     * */
    public String getPreferenceName() {
        // 获取SharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // 获取存在SharedPreference中的用户名
        return  preference.getString(USER_NAME, "");

    }
    public Integer getPreferenceId() {
        // 获取SharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // 获取存在SharedPreference中的用户名
        return  preference.getInt(USER_ID,0);

    }
    public void deletePreference() {
        // 获取SharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // 获取editor
        SharedPreferences.Editor editor = preference.edit();
        // 存入数据
        editor.putInt(USER_ID,0);
        editor.putString(USER_NAME,"");
        // 提交存入文件中
        editor.commit();
    }
    Toast mToast;
    public void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Log.d(TAG, msg);
	}
    
    public void showToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}
}
