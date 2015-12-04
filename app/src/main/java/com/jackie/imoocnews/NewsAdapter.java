package com.jackie.imoocnews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.BitmapResponseHandler;

import java.util.List;

/**
 * Created by Law on 2015/12/1.
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> data;
    private Context context;
    private ImageLoader imageLoader;
    public NewsAdapter(Context context, List<NewsBean> data) {
        this.context = context;
        this.data = data;

        imageLoader = new ImageLoader();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        //防止图片错位，给imageview设置url
        viewHolder.ivIcon.setTag(data.get(position).getNewsUrl());
        imageLoader.loadImage(viewHolder.ivIcon, data.get(position).getNewsUrl());
        viewHolder.tvTitle.setText(data.get(position).getNewsTitle());
        viewHolder.tvContent.setText(data.get(position).getNewsContent());
        return convertView;
    }

    class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvContent;
    }
}
