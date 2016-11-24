package com.jsp.base.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jsp.base.R;
import com.jsp.base.base.BaseActivity;
import com.jsp.base.base.BaseSwipeBackActivity;

import java.io.Serializable;
import java.util.ArrayList;

import cn.kejin.ximageview.XImageView;

public class ImageActivity extends BaseSwipeBackActivity implements View.OnClickListener {

    public static int currentItem = 0;
    private TextView currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        currentView = ((TextView) findViewById(R.id.currentitem));
        findViewById(R.id.close).setOnClickListener(this);

        Intent intent = getIntent();

        if(intent.hasExtra("ImageUrlsArray")){
            String[] imageUrls = intent.getStringArrayExtra("ImageUrlsArray");
            initViewPager(imageUrls);
        }else if(intent.hasExtra("ImageUrlsList")){
            ArrayList<String> imageUrls = (ArrayList<String>) intent.getSerializableExtra("ImageUrlsList");
            initViewPager((String[])imageUrls.toArray());
        }else{
            toast("非法打开");
            return;
        }





    }

    /**
     * 启动ImageActivity 数组传递数据 并指定显示条目
     * @param context
     * @param visiblePosition
     * @param imageUrls
     */
    public static void lauchImageActivity(Context context,int visiblePosition,String... imageUrls){
        if(imageUrls!=null){
            visiblePosition = Math.abs(visiblePosition);
            currentItem = visiblePosition;
            if(imageUrls.length<=visiblePosition){
                currentItem = imageUrls.length-1;
            }
            lauchImageActivity(context,imageUrls);
        }

    }
    /**
     * 启动ImageActivity 集合传递数据 并指定显示条目
     * @param context
     * @param visiblePosition
     * @param imageUrls
     */
    public static void lauchImageActivity(Context context,int visiblePosition,ArrayList<String> imageUrls){
        if(imageUrls!=null){
            visiblePosition = Math.abs(visiblePosition);
            currentItem = visiblePosition;
            if(imageUrls.size()<=visiblePosition){
                currentItem = imageUrls.size()-1;
            }
            lauchImageActivity(context,imageUrls);
        }

    }

    /**
     * 启动 数组方式
     * @param context
     * @param imageUrls
     */
    public static void lauchImageActivity(Context context,String... imageUrls){
        if(context!=null&&context instanceof BaseActivity&&imageUrls!=null&&imageUrls.length>0){
            Intent intent = new Intent(context,ImageActivity.class);
            intent.putExtra("ImageUrlsArray",imageUrls);
            context.startActivity(intent);
            ((BaseActivity)context).overridePendingTransition(R.anim.alpha_start, R.anim.alpha_end);
        }
    }

    /**
     * 启动 集合方式
     * @param context
     * @param imageUrls
     */
    public static void lauchImageActivity(Context context,ArrayList<String> imageUrls){
        if(context!=null&&context instanceof BaseActivity&&imageUrls!=null&&imageUrls.size()>0){
            Intent intent = new Intent(context,ImageActivity.class);
            intent.putExtra("ImageUrlsList",(Serializable)imageUrls);
            context.startActivity(intent);
            ((BaseActivity)context).overridePendingTransition(R.anim.alpha_start,R.anim.alpha_end);
        }
    }

    private void initViewPager(final String[] imageUrls) {

        ViewPager imageVp = (ViewPager) findViewById(R.id.image_vp);
        imageVp.setOnClickListener(this);
        currentView.setText((currentItem+1) + "/" + imageUrls.length);
        imageVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageUrls.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final View view = View.inflate(mContext, R.layout.layout_page_img, null);
                final XImageView image = (XImageView) view.findViewById(R.id.xImageView);
//                image.setOnClickListener(ImageActivity.this);
                Glide.with(mContext).load(imageUrls[position]).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ((ProgressBar) view.findViewById(R.id.progress)).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        image.setImage(resource);
                        ((ProgressBar) view.findViewById(R.id.progress)).setVisibility(View.GONE);
                    }

                    @Override
                    public void onStop() {
                        ((ProgressBar) view.findViewById(R.id.progress)).setVisibility(View.GONE);
                        super.onStop();
                    }
                });

                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        imageVp.setCurrentItem(currentItem, false);
        imageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentView.setText((position+1) + "/" + imageUrls.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                colseActivity();
                break;
            case R.id.xImageView:
                colseActivity();
                break;
        }
    }

    /**
     * 透明度渐变关闭Activity
     */
    private void colseActivity(){
        this.finish();
        overridePendingTransition(R.anim.alpha_start, R.anim.alpha_end);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            colseActivity();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
