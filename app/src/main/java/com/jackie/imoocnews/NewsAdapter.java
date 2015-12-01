package com.jackie.imoocnews;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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

    public NewsAdapter(Context context, List<NewsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        Log.d("getCount", "getCount:" + data.size());
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView", "----------" + position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        }
        if (convertView.getTag() == null) {
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
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
