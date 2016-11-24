package com.jsp.base.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by admin on 2016/11/23.
 */
public class UI {

    public static int zoomWidth(Context context, int w) {
        boolean sw = false;
        int sw1 = Math.min(getScreenWidth(context), getScreenHeight(context));
        return Math.round((float)(w * sw1) / 320.0F + 0.5F);
    }

    public static int zoomHeight(Context context, int h) {
        boolean sh = false;
        int sh1 = getScreenHeight(context);
        return (int)((float)(h * sh1) / 480.0F + 0.5F);
    }

    public static void zoomView(Context context, int w, int h, View view) {
        if(view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if(params != null) {
                params.width = zoomWidth(context, w);
                params.height = zoomWidth(context, h);
            }
        }
    }

    public static void zoomViewHeight(Context context, int h, View view) {
        if(view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if(params != null) {
                params.height = zoomWidth(context, h);
            }
        }
    }

    public static void zoomViewWidth(Context context, int w, View view) {
        if(view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if(params != null) {
                params.width = zoomWidth(context, w);
            }
        }
    }

    public static void zoomViewFull(Context context, View view) {
        if(view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if(params != null) {
                params.width = getScreenWidth(context);
                params.height = getScreenHeight(context);
            }
        }
    }

    public static int dipToPx(Context context, int dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        int pxValue = (int)((float)dipValue * scale + 0.5F);
        return pxValue;
    }

    public static int getStatusBarHeight(Activity act) {
        Rect frame = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static int getTitleBarHeight(Activity act) {
        int contentTop = act.getWindow().findViewById(16908290).getTop();
        int titleBarHeight = contentTop - getStatusBarHeight(act);
        return titleBarHeight;
    }

    public static int getContentHeight(Activity act) {
        int contentTop = act.getWindow().findViewById(16908290).getTop();
        return getScreenHeight(act) - contentTop;
    }

    public static int getScreenWidth(Context context) {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static boolean isLandscape(Activity activity) {
        int t = activity.getResources().getConfiguration().orientation;
        return t == 2;
    }

    public static void fullScreen(Activity activity) {
        activity.getWindow().setFlags(1024, 1024);
    }

    public static void notFullScreen(Activity activity) {
        activity.getWindow().clearFlags(1024);
    }

    public static void screenLandscape(Activity activity) {
        activity.setRequestedOrientation(0);
    }

    public static void screenPortrait(Activity activity) {
        activity.setRequestedOrientation(1);
    }


}
