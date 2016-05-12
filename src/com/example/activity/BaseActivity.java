package com.example.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class BaseActivity extends Activity{
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO
        super.onCreate(savedInstanceState);

    }

    /**
     * ���˴ε�½���˻���Ϣ�洢����
     * */
    protected void savePreferenceName(String value) {
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
    protected void savePreferenceId(Integer value) {
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
    protected String getPreferenceName() {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡ����SharedPreference�е��û���
        return  preference.getString(USER_NAME, "");

    }
    protected Integer getPreferenceId() {
        // ��ȡSharedPreference
        SharedPreferences preference = getSharedPreferences("android",
                MODE_PRIVATE);
        // ��ȡ����SharedPreference�е��û���
        return  preference.getInt(USER_ID,0);

    }
    protected void deletePreference() {
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

}
