package com.jsp.base.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jsp.base.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Toast util class.
 * 
 * @author admin
 * 
 */
public class ToastUtil {

	private static boolean isShow = false;
	private static Timer timer = new Timer();
	private static Toast toast = null;

	/**
	 * msg is String type
	 * 
	 * @param act
	 * @param msg
	 */
	public static void showMessage(final Context act, String msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	/**
	 * msg is resId type
	 * 
	 * @param act
	 * @param msg
	 */
	public static void showMessage(final Context act, final int msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context act, String msg, int gravity,
			int xOffset, int yOffset) {
		showMessage(act, msg, Toast.LENGTH_SHORT, gravity, xOffset, yOffset);
	}

	private static void showMessage(final Context act, final String msg,
			final int len) {
		if (toast != null) {
			toast.setText(msg);
			toast.setDuration(len);
		} else {
			toast = Toast.makeText(act, msg, len);
		}
		toast.show();
	}

	private static void showMessage(final Context act, final int msg,
			final int len) {
		if (toast != null) {
			toast.setText(msg);
			toast.setDuration(len);
		} else {
			toast = Toast.makeText(act, msg, len);
		}
		toast.show();
	}

	private static void showMessage(final Context act, String msg, int len,
			int gravity, int xOffset, int yOffset) {
		if (isShow) {
			return;
		} else {
			isShow = true;
			Toast toast = Toast.makeText(act, msg, len);
			toast.setGravity(gravity, xOffset, yOffset);
			toast.show();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					isShow = false;
				}
			}, 2000);
		}
	}
	public static Toast toast1;
	public static void showMyView(Context context,@DrawableRes int res,String msg,@ColorInt int colorBg){
		View layout = LayoutInflater.from(context).inflate(R.layout.layout_voice_toast,null);
		layout.setBackgroundColor(colorBg);
		TextView text = (TextView) layout.findViewById(R.id.voice_toast_tv);
		ImageView mImageView = (ImageView) layout.findViewById(R.id.voice_toast_iv);
		mImageView.setBackgroundResource(res);
		text.setText(msg);
		if(toast1==null) {
			toast1 = new Toast(context);
		}
		toast1.setGravity(Gravity.TOP, 0, 0);
		toast1.setDuration(Toast.LENGTH_LONG);
		toast1.setView(layout);
		initTN(context, toast1);
		toast1.show();
	}

	/**
	 * 反射修改Toast动画
	 * @param mContext
	 * @param toast
	 */
	private  static void initTN(Context mContext,Toast toast) {
		try {
			Field tnField = toast.getClass().getDeclaredField("mTN");
			tnField.setAccessible(true);
			Object mTN = tnField.get(toast);
			Method show = mTN.getClass().getMethod("show");
			Method hide = mTN.getClass().getMethod("hide");
			Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
			tnParamsField.setAccessible(true);
			WindowManager.LayoutParams params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
			params.windowAnimations = R.style.MyToast;
			params.width = ViewGroup.LayoutParams.MATCH_PARENT;

			/**调用tn.show()之前一定要先设置mNextView*/
			Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
			tnNextViewField.setAccessible(true);
			tnNextViewField.set(mTN, toast.getView());

			WindowManager mWM = (WindowManager)mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}