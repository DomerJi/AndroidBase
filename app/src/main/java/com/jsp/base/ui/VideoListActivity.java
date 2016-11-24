package com.jsp.base.ui;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsp.base.R;
import com.jsp.base.adapter.VideoListAdapter;
import com.jsp.base.base.BaseActivity;
import com.jsp.base.face.MyItemClickListener;

import java.util.Locale;

public class VideoListActivity extends BaseActivity {

    private VideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        initView();
    }

    private void initView() {
        final XRecyclerView xRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(manager);
        adapter = new VideoListAdapter(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                toast("position = "+postion);
            }
        });
        VideoListAdapter.RecyclerViewScrollDetector recyclerViewScrollDetector = new VideoListAdapter.RecyclerViewScrollDetector();
        xRecyclerView.setOnScrollListener(recyclerViewScrollDetector);

        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScale);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (VideoListAdapter.player != null) {
            VideoListAdapter.player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (VideoListAdapter.player != null) {
            VideoListAdapter.player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
//        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (VideoListAdapter.player != null) {
            VideoListAdapter.player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (VideoListAdapter.player != null) {
            VideoListAdapter.player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (VideoListAdapter.player != null && VideoListAdapter.player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}

        System.out.printf(Locale.CANADA,"","");
    }


}
