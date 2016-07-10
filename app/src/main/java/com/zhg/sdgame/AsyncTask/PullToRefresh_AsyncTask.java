package com.zhg.sdgame.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhg.adgame.dao.NewsDao;
import com.zhg.sdgame.Utils.HttpClient_Utils;
import com.zhg.sdgame.Utils.JSONUtils;
import com.zhg.sdgame.adapter.MyRefreshAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class PullToRefresh_AsyncTask extends AsyncTask<String,Void,List<HashMap<String,Object>>>{

    private Context context;
    private MyRefreshAdapter adapter;
    private PullToRefreshListView pullToRefreshListView;

    public PullToRefresh_AsyncTask(Context context, MyRefreshAdapter adapter, PullToRefreshListView pullToRefreshListView) {
        this.context = context;
        this.adapter = adapter;
        this.pullToRefreshListView = pullToRefreshListView;
    }

    @Override
    protected List<HashMap<String, Object>> doInBackground(final String... params) {
        List<HashMap<String,Object>> list = new ArrayList<>();
                try {
                    Thread.sleep(10000);
                   byte[] byt = HttpClient_Utils.httpclient(params[0]);
                    String strurl = new String(byt,"utf-8");
                    Log.i("aaa","http解析完毕");
                    List<HashMap<String,Object>> data = JSONUtils.Jsonutlis(strurl);
                    Log.i("aaa","json解析完毕");
                    NewsDao dao = new NewsDao(context);
                    dao.insert(data);
                    Log.i("aaa","添加数据成功");
                    return list = dao.getlist();
                } catch (Exception e) {
                    e.printStackTrace();
                }

        return null;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, Object>> hashMaps) {
        super.onPostExecute(hashMaps);


    }


}
