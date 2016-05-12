package com.example.layout;

import com.example.activity.R;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditText extends EditText{
	private final static String TAG = "MyEditText";
	private Drawable imgInable;
	private Drawable imgWarn;
	private Context mContext;

	public MyEditText(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}
	 public MyEditText(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	        mContext = context;
	        init();
	    }
	    public MyEditText(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        mContext = context;
	        init();
	    }
	    private void init() {
	    	imgWarn = mContext.getResources().getDrawable(R.drawable.warn, null);
	        addTextChangedListener(new TextWatcher() {
	            @Override
	            public void afterTextChanged(Editable s) {
	                setDrawable();
	            }
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO 自动生成的方法存根
					
				}
	        });
	        setDrawable();
	    }
	    // 设置删除图片
	    private void setDrawable() {
	        if (length() < 1) {
	            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
	        } else {
	            setCompoundDrawablesWithIntrinsicBounds(null, null, imgWarn, null);
	        }
	    }
	 // 处理删除事件
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        if (imgWarn != null && event.getAction() == MotionEvent.ACTION_UP) {
	            int eventX = (int) event.getRawX();
	            int eventY = (int) event.getRawY();
	            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
	            Rect rect = new Rect();
	            getGlobalVisibleRect(rect);
	            rect.left = rect.right - 50;
	            if (rect.contains(eventX, eventY))
	                setText("");
	        }
	        return super.onTouchEvent(event);
	    }

	    @Override
	    protected void finalize() throws Throwable {
	        super.finalize();
	    }
}
