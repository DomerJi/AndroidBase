package com.jsp.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsp.base.R;

/**
 * @author fjh
 */
public class WebViewTitleView extends LinearLayout implements OnClickListener {

    private TextView title;
    private CircleImageView avater;
    private ImageView share,fresh;

    private TitleButtonClickListener mListener;

    public interface TitleButtonClickListener {

        void onBackClick(View view);
        
        void onCloseClick(View view);

        void onRefreshClick(View view);

        void onShareClick(View view);

        void onTitleClick(View view);
    }

    public void setTitleListener(TitleButtonClickListener mListener){
        this.mListener = mListener;
    }

    public WebViewTitleView(Context context) {
        this(context, null);
    }

    public WebViewTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.webview_title_view, this);
        setFocusable(true);
        setClickable(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.webview_back).setOnClickListener(this);
        findViewById(R.id.webview_clear).setOnClickListener(this);
        findViewById(R.id.webview_title).setOnClickListener(this);

    }

    public void setLastTitle(String lastTitle){
        ((TextView)findViewById(R.id.back_txt)).setText(lastTitle);
    }

    public void setTitle(String title){
        ((TextView)findViewById(R.id.webview_title_text)).setText(title);
    }

    public void setAvater(String url){
        Glide.with(getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ((CircleImageView) findViewById(R.id.webview_title_img)).setImageBitmap(resource);
            }
        });

    }

    public void setAvaterVisible(int visible){
        findViewById(R.id.webview_title_img).setVisibility(visible);
    }

    public void setTitleVisible(int visible){
        findViewById(R.id.webview_title_text).setVisibility(visible);
    }

    public void setBackVisible( int visible){
        findViewById(R.id.webview_back).setVisibility(visible);
    }
    public void setColseVisible( int visible){
        findViewById(R.id.webview_clear).setVisibility(visible);
    }

    public void setShareVisible(int visible){
        if(share==null){
            share = (ImageView) findViewById(R.id.webview_share);
        }
        share.setVisibility(visible);
        share.setOnClickListener(this);
    }

    public void setFreshVisible(int visible){
        if(fresh==null){
            fresh = (ImageView) findViewById(R.id.webview_refresh);
        }
        fresh.setVisibility(visible);
        fresh.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(mListener==null)
            return;
        switch (v.getId()){
            case R.id.webview_back:
                mListener.onBackClick(v);
                break;
            case R.id.webview_clear:
                mListener.onCloseClick(v);
                break;
            case R.id.webview_title:
                mListener.onTitleClick(v);
                break;
            case R.id.webview_refresh:
                mListener.onRefreshClick(v);
                break;
            case R.id.webview_share:
                mListener.onShareClick(v);
                break;
        }
    }
}
