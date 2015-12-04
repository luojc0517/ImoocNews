package com.jackie.imoocnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.JSONObjectResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView mListView;
    public List<NewsBean> dataList;
    public NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lvMain);
        dataList = new ArrayList<NewsBean>();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient("http://www.imooc.com/api/teacher?type=4&num=30");
        asyncHttpClient.get(new JSONObjectResponseHandler() {


            @Override
            public void onSuccess() {

            }

            @Override
            public void onFinish() {
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
                        NewsBean newsBean = new NewsBean(picSmall, name, description);
                        dataList.add(newsBean);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                newsAdapter = new NewsAdapter(MainActivity.this, dataList, mListView);
                mListView.setAdapter(newsAdapter);
            }
        });

    }
}
