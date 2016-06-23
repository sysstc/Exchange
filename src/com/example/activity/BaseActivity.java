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
     * ���˴ε�½���˻���Ϣ�洢����
     * */
    public void savePreferenceName(String value) {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡeditor
        SharedPreferences.Editor editor = preference.edit();
        // ��������
        editor.putString(USER_NAME, value);
        // �ύ�����ļ���
        editor.commit();
    }
    public void savePreferenceId(Integer value) {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡeditor
        SharedPreferences.Editor editor = preference.edit();
        // ��������
        editor.putInt(USER_ID, value);
        // �ύ�����ļ���
        editor.commit();
    }
    /**
     * ��ȡ����SharedPreference�е��˻�
     *
     * @return
     * */
    public String getPreferenceName() {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡ����SharedPreference�е��û���
        return  preference.getString(USER_NAME, "");

    }
    public Integer getPreferenceId() {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡ����SharedPreference�е��û���
        return  preference.getInt(USER_ID,0);

    }
    public void deletePreference() {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡeditor
        SharedPreferences.Editor editor = preference.edit();
        // ��������
        editor.putInt(USER_ID,0);
        editor.putString(USER_NAME,"");
        // �ύ�����ļ���
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
