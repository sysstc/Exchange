package com.example.bean;

public class FolderBean {
	/**
	 * ��ǰ�ļ��е�·��
	*/
	private String dir;
	private String firstImgPath;
	private String name;
	private int count;
	
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
		int lastIndexOf = this.dir.lastIndexOf("/");
		this.name = this.dir.substring(lastIndexOf);
	}
	public String getFirstImgPath() {
		return firstImgPath;
	}
	public void setFirstImgPath(String firstImgPath) {
		this.firstImgPath = firstImgPath;
	}
	public String getName() {
		return name;
	}
/*	public void setName(String name) {
		this.name = name;
	}*/
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
