package com.jsp.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

/**
 * Created by admin on 2016/11/18.
 */
public class DragBackScrollView extends ScrollView {
    // 这个值控制可以把ScrollView包裹的控件拉出偏离顶部或底部的距离。
    private static final int MAX_OVERSCROLL_Y = 200;

    private Context mContext;
    private int newMaxOverScrollY;


    public DragBackScrollView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public DragBackScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        newMaxOverScrollY = (int) (density * MAX_OVERSCROLL_Y);

        //false:隐藏ScrollView的滚动条。
        this.setVerticalScrollBarEnabled(false);

        //不管装载的控件填充的数据是否满屏，都允许橡皮筋一样的弹性回弹。  
        this.setOverScrollMode(ScrollView.OVER_SCROLL_ALWAYS);
    }


    // 最关键的地方。
    //支持到SDK8需要增加@SuppressLint("NewApi")。
    @SuppressLint("NewApi")
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, newMaxOverScrollY,
                isTouchEvent);
    }


}
