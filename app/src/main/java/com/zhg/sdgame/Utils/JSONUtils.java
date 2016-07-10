package com.zhg.sdgame.Utils;

import android.os.Handler;
import android.util.Log;

import com.zhg.sdgame.article.Article;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class JSONUtils {

    public static List<HashMap<String,Object>> Jsonutlis(String url){
        List<HashMap<String,Object>> data;
        HashMap<String,Object> map;
        try {

            JSONObject object = new JSONObject(url);
            JSONObject obj = object.getJSONObject("data");
            data = new ArrayList<>();
            for (int i =0;i<20;i++){
                JSONObject jsonObject = obj.getJSONObject(""+i);
                map = new HashMap<>();
                String shorttitle = jsonObject.getString("shorttitle");
                String litpic = "http://122.226.122.6"+jsonObject.getString("litpic");
                String senddate = jsonObject.getString("senddate");
                String feedback = jsonObject.getString("click");
                String arcurl = jsonObject.getString("arcurl");
                map.put("shorttitle",shorttitle);
                map.put("litpic",litpic);
                map.put("senddate",senddate);
                map.put("click",feedback);
                map.put("arcurl",arcurl);
                data.add(map);
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

return null;
    }
}
