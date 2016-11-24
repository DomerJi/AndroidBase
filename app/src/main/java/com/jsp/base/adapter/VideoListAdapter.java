package com.jsp.base.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jsp.base.R;
import com.jsp.base.base.BaseActivity;
import com.jsp.base.data.Videos;
import com.jsp.base.face.MyItemClickListener;
import com.jsp.base.util.LogUtil;
import com.jsp.base.util.UI;

/**
 * Created by admin on 2016/11/22.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private static FrameLayout tempLayout;
    public static PlayerView player;

    public MyItemClickListener itemClickListener;
    public  VideoListAdapter(MyItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(VideoListAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText("position = "+position);
        new VideoListAdapter.RecyclerViewScrollDetector();
    }



    @Override
    public int getItemCount() {
        return Videos.videos.length;
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        public TextView mTextView;
        public FrameLayout frameLayout;
        public MyItemClickListener itemClickListener;
        public ViewHolder(View view,MyItemClickListener itemClickListener){
            super(view);
            this.itemClickListener = itemClickListener;
            mTextView = (TextView) view.findViewById(R.id.item_title);
            frameLayout = (FrameLayout) view.findViewById(R.id.item_framelayout);
            frameLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(final View v) {
            onPause(v, frameLayout, getPosition());
            if(this.itemClickListener!=null){
                this.itemClickListener.onItemClick(v, getPosition());
            }
        }



    }

    /**
     * 播放视频  动态添加视频试图
     * @param v
     * @param frameLayout 视频试图 布局
     * @param position 条目id
     */
    public static void onPause(final View v,FrameLayout frameLayout,int position){
        if(tempLayout == frameLayout){
            return;
        }
        if(tempLayout!=null){
            tempLayout.removeAllViews();
        }

        tempLayout = frameLayout;
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.simple_player_view_player, null);
        frameLayout.addView(view);
        VideoijkBean bean = new VideoijkBean();
        bean.setUrl(Videos.videos[position]);
        if(player!=null){
            player.onDestroy();
        }
        player = new PlayerView((BaseActivity)v.getContext(),view);
        player.setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(v.getContext())
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.colorPrimary)
                                .error(R.color.colorAccent)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(bean)
                .startPlay();

    }


    /**
     * RecyclerView 滚动监听
     */
    public static class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int y = Math.abs(dy);
            LogUtil.e("Scroll", recyclerView.getScrollState() + "________" + y);


        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState==RecyclerView.SCROLL_STATE_IDLE){
                LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
                ViewHolder holder = (ViewHolder) recyclerView.getChildViewHolder( findFirstFullViewInScreen(recyclerView));
                onPause(recyclerView, holder.frameLayout, manager.findFirstVisibleItemPosition());
            }

        }


    }

    /**
     * 处于可视界面的播放view
     */
    public static View findFirstFullViewInScreen(RecyclerView view) {
        View childView = null;
        int childCount = view.getChildCount();

        int screenHeight = ((int) (UI.getScreenWidth( view.getContext()) * (14.0f / 25.0f)))+100;
        if (0 == childCount) {
            return childView;
        } else if (childCount >= 1) {
            for (int i = 0; i < childCount; ++i) {
                childView = view.getChildAt(i);
                if (childView.getTop() <= (view.getTop() + screenHeight)
                        && childView.getBottom() > (view.getTop() + screenHeight)) {
                    break;
                }
            }
        }
        return childView;
    }
}





