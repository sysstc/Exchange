package com.example.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class MyAsynctask extends AsyncTask<String, Void, Bitmap>{

	@Override
	protected void onPreExecute() {
		// TODO �Զ����ɵķ������
		super.onPreExecute();
	}
	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO �Զ����ɵķ������
		super.onPostExecute(result);
	}
}
