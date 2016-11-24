package com.jsp.base.util;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.jsp.base.view.VoiceToastView;


public class VoiceToast {

	private Context mContext;

	private WindowManager mWindowManager;

	private WindowManager.LayoutParams mLayoutParams;

	private View mShowView;

	private boolean mIsShowing;

	public VoiceToast(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mWindowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		mLayoutParams = new LayoutParams();
		mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams.format = PixelFormat.TRANSLUCENT;
		mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
		mLayoutParams.setTitle("Toast");
		mLayoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		mLayoutParams.gravity = Gravity.CENTER;
		mIsShowing = false;
	}

	public void show(View view) {
		if (null != view) {
			if (mShowView != null) {
				mWindowManager.removeView(mShowView);
			}
			mShowView = view;			
			mWindowManager.addView(mShowView, mLayoutParams);
			mIsShowing = true;
		}
	}

	/**
	 * 只针对图片
	 * 
	 * @param id
	 */
	public void updateView(int resid,int tipid) {
		if (mShowView != null) {
			if (mShowView instanceof VoiceToastView) {
				((VoiceToastView) mShowView).setData(resid,tipid);
				mWindowManager.updateViewLayout(mShowView, mLayoutParams);
			}
		}
	}
	
	public void updateView(View updateView) {
		if (mShowView != null) {
			if (updateView != null) {
				mShowView = updateView;
				mWindowManager.updateViewLayout(mShowView, mLayoutParams);
			}
		}
	}

	public void close() {
		if (mShowView != null) {
			mWindowManager.removeView(mShowView);
			mShowView = null;
			mIsShowing = false;
		}
	}

	public boolean isShow() {
		return mIsShowing;
	}

}
