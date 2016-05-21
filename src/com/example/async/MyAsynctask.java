package com.example.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class MyAsynctask extends AsyncTask<String, Void, Bitmap>{

	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		super.onPreExecute();
	}
	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO 自动生成的方法存根
		return null;
	}
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO 自动生成的方法存根
		super.onPostExecute(result);
	}
}
