package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.activity.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


public class ImageLoader {
	private MemoryCache memoryCache = new MemoryCache();
	private FileCache fileCache;
	private ExecutorService executorService;
	private Context context;
	private Map<ImageView, String> imageViews = Collections
			.synchronizedMap(new WeakHashMap<ImageView, String>());

	private class PhotoToLoad {
		public String url;
		public ImageView imageView;

		public PhotoToLoad(String u, ImageView i) {
			url = u;
			imageView = i;
		}
	}

	public ImageLoader(Context context) {
		this.context = context;
		fileCache = new FileCache(context);
		executorService = Executors.newFixedThreadPool(6);
	}

	public void displayImage(String url, ImageView imageView, boolean busy) {
		imageViews.put(imageView, url);

		Bitmap bitmap = memoryCache.get(url);

		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else if (!busy) {
			LoadRunnable r = new LoadRunnable(new PhotoToLoad(url, imageView));
			executorService.submit(r);
		}
	}

	class LoadRunnable implements Runnable {
		private PhotoToLoad p;

		public LoadRunnable(PhotoToLoad p) {
			this.p = p;
		}

		@Override
		public void run() {
			loadimage(p);
		}

	}


	private void loadimage(PhotoToLoad p) {
		if (imageViewReused(p))
			return;
		Bitmap b = getBitmap(p.url);
		if (b != null) {
			memoryCache.put(p.url, b);
			if (imageViewReused(p))
				return;
			SetBitmap sb = new SetBitmap(b, p);
			Activity a = (Activity) p.imageView.getContext();
			a.runOnUiThread(sb);
		} else {
			System.out.println("鍔犺浇鍥剧墖鍑虹幇寮傚父" + p.url);
			loadimage(p);
		}
	}

	class SetBitmap implements Runnable {
		Bitmap b;
		PhotoToLoad p;

		public SetBitmap(Bitmap b, PhotoToLoad p) {
			this.b = b;
			this.p = p;
		}

		@Override
		public void run() {
			if (imageViewReused(p))
				return;
			if (b != null)
				p.imageView.setImageBitmap(b);
		}

	}

	private boolean imageViewReused(PhotoToLoad p) {
		String u = imageViews.get(p.imageView);
		if (u == null || !u.equals(p.url))
			return true;
		return false;
	}

	private Bitmap getBitmap(String url) {
		File f = fileCache.getFile(url);
		Bitmap b = null;
		if (f != null && f.exists()) {
			b = decodeFile(f);
		}
		if (b != null) {
			System.out.println("Url = "+ url);
			return b;
		} else {
			try {
				URL imageUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) imageUrl
						.openConnection();
				conn.setConnectTimeout(300000);
				conn.setReadTimeout(300000);
				conn.setInstanceFollowRedirects(true);

				InputStream is = conn.getInputStream();
				OutputStream os = new FileOutputStream(f);
				copyStream(is, os);
				os.close();
				b = decodeFile(f);
				System.out.println("Url = "+ url);
				return b;
			} catch (FileNotFoundException e) {
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				b = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.loading);
				return b;
			}
		}
	}

	private Bitmap decodeFile(File f) {
		try {
			return (BitmapFactory.decodeStream(new FileInputStream(f)));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static void copyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			for (;;) {
				byte[] bytes = new byte[buffer_size];
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void clearCache() {
		memoryCache.clear();
		fileCache.clear();
	}

}
