package com.example.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Bitmap;

public class MemoryCache {
	private Map<String, Bitmap> cacheMap = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
	private long size = 0;
	private long limit = 0;

	public MemoryCache() {
		this.limit = Runtime.getRuntime().maxMemory() / 10;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public Bitmap get(String url) {
		if (!cacheMap.containsKey(url))
			return null;
		return cacheMap.get(url);
	}

	public void put(String url, Bitmap bitmap) {
		if (cacheMap.containsKey(url))
			size -= getSizeInBytes(cacheMap.get(url));
		cacheMap.put(url, bitmap);
		size += bitmap.getRowBytes() * bitmap.getHeight();
		checkSize();
	}

	private void checkSize() {
		if (size > limit) {
			Iterator<Entry<String, Bitmap>> iterator = cacheMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Bitmap> entry = iterator.next();
				iterator.remove();
				size -= getSizeInBytes(entry.getValue());
				if (size < limit)
					break;
			}
		}

	}

	private long getSizeInBytes(Bitmap bitmap) {
		if (bitmap == null)
			return 0;
		return bitmap.getRowBytes() * bitmap.getHeight();

	}

	public void clear() {
		cacheMap.clear();
	}

}
