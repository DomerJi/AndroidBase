package com.jsp.base.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsp.base.R;


public class VoiceToastView extends RelativeLayout {

	private ImageView mIcoImageView;

	private TextView mTipTextView;

	private View mRootView;

	public VoiceToastView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public VoiceToastView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	private void initView() {
		mRootView = LayoutInflater.from(getContext()).inflate(
				R.layout.layout_voice_toast, this, true);
		mIcoImageView = (ImageView) mRootView.findViewById(R.id.voice_toast_iv);
		mTipTextView = (TextView) mRootView.findViewById(R.id.voice_toast_tv);
	}

	public void setData(int resid, String tip) {
		if (mIcoImageView != null) {
			mIcoImageView.setImageResource(resid);
		}

		if (mTipTextView != null) {
			if (TextUtils.isEmpty(tip)) {
				mTipTextView.setVisibility(View.INVISIBLE);
			}
			else {
				mTipTextView.setText(tip);
			}
		}
	}
	
	public void setData(int resid, int tipid) {
		if (mIcoImageView != null) {
			mIcoImageView.setImageResource(resid);
		}

		if (mTipTextView != null) {
			if (tipid == -1) {
				mTipTextView.setText("");
				mTipTextView.setVisibility(View.INVISIBLE);
			}
			else {
				mTipTextView.setText(tipid);
				mTipTextView.setVisibility(View.VISIBLE);
			}
		}
	}

}
