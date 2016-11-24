package com.jsp.base.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jsp.base.R;
import com.jsp.base.base.BaseActivity;
import com.jsp.base.bean.UrlBean;
import com.jsp.base.data.Images;
import com.jsp.base.util.ToastUtil;

public class MasterActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private String[] Items = new String[]{
            "图片",
            "网页",
            "Toast",
            "视频",
            "弹幕"
    };

    private Class[] ItemClass = new Class[]{
            MainActivity.class,
            WebViewActivity.class,
            null,
            VideoListActivity.class,
            DanmakuActivity.class

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        ListView lv = (ListView) findViewById(R.id.main_lv);
        lv.setAdapter(new ArrayAdapter<String>(MasterActivity.this,android.R.layout.simple_list_item_1,Items));
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(position==1){
            WebViewActivity.lauchWebViewActivity(mContext,
                    new UrlBean("https://www.baidu.com/","百度", Images.imageUrls[0]));
        }else if(position==2){
            ToastUtil.showMyView(mContext,R.mipmap.ic_launcher,"提示", Color.RED);
        }else{
            startActivity(new Intent(mContext,ItemClass[position]));
        }
    }
}
