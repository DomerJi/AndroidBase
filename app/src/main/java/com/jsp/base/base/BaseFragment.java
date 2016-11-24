package com.jsp.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jsp.base.util.ToastUtil;

/**
 * Created by admin on 2016/11/15.
 */
public class BaseFragment extends Fragment{
    public Context mContext;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    protected void toast(String msg){
        ToastUtil.showMessage(mContext, msg);
    }

    protected void toast(String msg, int gravity,
                         int xOffset, int yOffset) {
        ToastUtil.showMessage(mContext, msg, gravity, xOffset, yOffset);
    }
}
