package com.jsp.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.jsp.base.R;
import com.jsp.base.ui.ImageActivity;
import com.jsp.base.util.LogUtil;
import com.jsp.base.util.ToastUtil;
import com.jsp.base.util.VoiceToast;
import com.jsp.base.view.MyGridView;
import com.jsp.base.view.VoiceToastView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 2016/11/15.
 */
public class ListViewAdapter extends BaseAdapter {

    private ArrayList<String[]> pics;
    private Context mContext;
    GridViewAdapter gridAdapter;

    public ListViewAdapter(ArrayList<String[]> pics,Context context){
        this.pics = pics;
        this.mContext = context;
    }

    private boolean loadPic = true;

    public void setLoadPic(boolean loadPic) {
        this.loadPic = loadPic;
        if(gridAdapter!=null){
            gridAdapter.setLoadPic(loadPic);
        }
    }

    @Override
    public int getCount() {
        if(pics!=null){
            return pics.size();
        }else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return pics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layoutgridview,null);
            holder = new ViewHolder();
            gridAdapter = new GridViewAdapter(mContext);
            holder.gridView = (MyGridView) convertView.findViewById(R.id.gridview);
            convertView.setTag(R.id.holder,holder);
            convertView.setTag(R.id.adpter,gridAdapter);
        }else {
            holder = (ViewHolder) convertView.getTag(R.id.holder);
            gridAdapter = (GridViewAdapter) convertView.getTag(R.id.adpter);
        }
        gridAdapter.setPics(pics.get(position));
        holder.gridView.setAdapter(gridAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position2, long id) {
//                VoiceToast toast = new VoiceToast(mContext);
//                VoiceToastView toastView = new VoiceToastView(mContext);
//                toast.show(toastView);
                ToastUtil.showMessage(mContext,position2+"item");
                //查看大图
                ImageActivity.lauchImageActivity(mContext,position2,pics.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder{
        MyGridView gridView;
    }
}
