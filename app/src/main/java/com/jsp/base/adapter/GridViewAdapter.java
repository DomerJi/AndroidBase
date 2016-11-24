package com.jsp.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsp.base.R;
import com.jsp.base.util.LogUtil;

/**
 * Created by admin on 2016/11/15.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private String[] pics;

    private boolean loadPic = true;

    public void setLoadPic(boolean loadPic) {
        this.loadPic = loadPic;
    }

    public GridViewAdapter(Context mContext){
        this.mContext = mContext;
    }
    public GridViewAdapter(Context mContext,String[] pics){
        this.mContext = mContext;
        this.pics = pics;
        LogUtil.e("size",pics.length+"");
    }
    public void setPics(String[] pics){
        this.pics = pics;
    }

    @Override
    public int getCount() {
        if(pics!=null){
            return pics.length;
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return pics[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.imageview,null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        LogUtil.e("imageurl", pics[position]);
        if(loadPic){
            Glide.with(mContext).load(pics[position]).into(holder.iv);
        }

        return convertView;
    }
    class ViewHolder{
        ImageView iv;
    }
}
