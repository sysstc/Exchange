package com.example.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListViewForScrollView extends ListView implements android.widget.AdapterView.OnItemClickListener{

	public ListViewForScrollView(Context context) {
		super(context);
		// TODO �Զ����ɵĹ��캯�����
	}
	public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	public ListViewForScrollView(Context context, AttributeSet attrs,
	        int defStyle) {
	        super(context, attrs, defStyle);
	    }
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO �Զ����ɵķ������
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO �Զ����ɵķ������
		
	}
	
}
