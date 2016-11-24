package com.jsp.base.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jsp.base.R;
import com.jsp.base.adapter.ListViewAdapter;
import com.jsp.base.base.BaseActivity;
import com.jsp.base.data.Images;
import com.jsp.base.util.LogUtil;
import com.jsp.base.util.ToastUtil;
import com.jsp.base.util.VoiceToast;
import com.jsp.base.view.MyListView;
import com.jsp.base.view.VoiceToastView;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MainActivity extends BaseActivity {

    private ListViewAdapter adapter;
    private boolean listViewflag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
        /**
         * 得到数据
         */
        ArrayList<String[]> lists = Images.getArrayPics();
        LogUtil.e("pics",lists.toString());
        adapter = new ListViewAdapter(lists, mContext);
        lv.setAdapter(adapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {//快速滑动

                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {//停止滑动

                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {//触摸

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount == (firstVisibleItem + visibleItemCount) && listViewflag) {//滑动到底部后
                    listViewflag = false;
                    toast("到底啦!");
                } else if (totalItemCount != (firstVisibleItem + visibleItemCount) && !listViewflag) {
                    listViewflag = true;
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    VoiceToast toast = new VoiceToast(mContext);
                    VoiceToastView toastView = new VoiceToastView(mContext);
                    toast.show(toastView);
                }

                toast("DD");
            }
        });

    }



}
