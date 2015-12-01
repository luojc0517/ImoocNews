package com.jackie.imoocnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.JSONObjectResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private SimpleAdapter mSimpleAdapter;
    //private List<Map<String, Object>> list;
    private List<NewsBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lvMain);
        //list = new ArrayList<Map<String, Object>>();
        dataList = new ArrayList<NewsBean>();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient("http://www.imooc.com/api/teacher?type=4&num=30");
        asyncHttpClient.get(new JSONObjectResponseHandler() {
            @Override
            public void onSuccess() {

                JSONObject jsonObject = getContent();
                try {
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        int id = obj.getInt("id");
                        String name = obj.getString("name");
                        String picSmall = obj.getString("picSmall");
                        String picBig = obj.getString("picBig");
                        String description = obj.getString("description");
                        String learner = obj.getString("learner");
                        //Log.d("jackie", "--------------------------" + i + "----------------------------------");
                        //Log.d("jackie", id + "\n" + name + "\n" + picSmall + "\n" + picBig + "\n" + description + "\n" + learner);
//                        Map<String, Object> map = new HashMap<String, Object>();
//                        map.put("name", name);
//                        map.put("description", description);
//                        list.add(map);
                        NewsBean newsBean = new NewsBean(picSmall, name, description);
                        dataList.add(newsBean);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        //SimpleAdapter用于显示简单的列表内容还不错，不过使用图片就比较麻烦
//        mSimpleAdapter = new SimpleAdapter(
//                MainActivity.this, list,
//                R.layout.layout_item,
//                new String[]{"name", "description"},
//                new int[]{R.id.tvTitle, R.id.tvContent});
//        mListView.setAdapter(mSimpleAdapter);
        NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, dataList);
        mListView.setAdapter(newsAdapter);
    }
}
