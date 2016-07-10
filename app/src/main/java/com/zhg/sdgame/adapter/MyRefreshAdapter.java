package com.zhg.sdgame.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhg.adgame.dao.NewsDao;
import com.zhg.sdgame.R;

import java.util.HashMap;
import java.util.List;

import static com.zhg.sdgame.R.layout.activity_main_refresh;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MyRefreshAdapter extends BaseAdapter{
    Context context;

List<HashMap<String,Object>> data;

    public MyRefreshAdapter(Context context, List<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
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
        ViewHodler hodler;
        if(convertView ==null){
            hodler = new ViewHodler();
            convertView = View.inflate(context,R.layout.activity_main_refresh,null);
            hodler.img = (ImageView) convertView.findViewById(R.id.main_activity_pulltorefresh_img);
            hodler.tv1 = (TextView) convertView.findViewById(R.id.main_activity_pulltorefresh_tv01);
            hodler.tv2 = (TextView) convertView.findViewById(R.id.main_activity_pulltorefresh_tv02);
            hodler.tv3 = (TextView) convertView.findViewById(R.id.main_activity_pulltorefresh_tv03);
            convertView.setTag(hodler);
        }else {
            hodler= (ViewHodler) convertView.getTag();
        }
        hodler.tv2.setText(data.get(position).get("senddate").toString());
        hodler.tv1.setText(data.get(position).get("shorttitle").toString());
        hodler.tv3.setText(data.get(position).get("click").toString());
        hodler.img.setImageBitmap((Bitmap) data.get(position).get("litpic"));

        return convertView;
    }

class ViewHodler{
    ImageView img;
    TextView tv1,tv2,tv3;
}
}
