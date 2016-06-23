package com.example.util;

import java.io.File;

import android.content.Context;


public class FileCache {
	private File cacheDir;

	public FileCache(Context context) {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			cacheDir = new File(
					android.os.Environment.getExternalStorageDirectory(),
					"CacheDir");
		} else {
			cacheDir = context.getCacheDir();
		}
		creatDir();
	}

	public boolean creatDir() {
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
			System.out.println("文件夹不存在创建目录");
			return true;
		} else {
			return false;
		}
	}

	public File getFile(String url) {
		String fileName = String.valueOf(url.hashCode());
		creatDir();
		File file = new File(cacheDir, fileName);
		return file;
	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		for (File f : files)
			f.delete();
	}

	public String getCacheSize() {
		long size = 0;
		if (cacheDir.exists()) {
			File[] files = cacheDir.listFiles();
			for (File f : files) {
				size += f.length();
			}
		}
		String cacheSize = String.valueOf(size / 1024 / 1024) + "M";
		return cacheSize;
	}

}
