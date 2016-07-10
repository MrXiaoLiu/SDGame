package com.zhg.sdgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.zhg.adgame.dao.NewsDao;
import com.zhg.sdgame.Utils.HttpClient_Utils;
import com.zhg.sdgame.Utils.JSONUtils;
import com.zhg.sdgame.Utils.NetUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

public class DownLoadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] b = HttpClient_Utils.httpclient(intent.getStringExtra("strurl"));
                    Log.i("aaa","b++"+intent.getStringExtra("strurl"));
                    String str = new String(b,"utf-8");
                    Log.i("aaa","str++"+str);
                    List<HashMap<String,Object>> data = JSONUtils.Jsonutlis(str);
                    for(int i = 0;i<data.size();i++){
                        String zpurl = data.get(i).get("litpic").toString();
                        //获取图片名字
                        String path = zpurl.substring(zpurl.lastIndexOf("/"));
                       byte [] zpbyt = HttpClient_Utils.httpclient(zpurl);
                        SDpitcle(zpbyt,path);
                        //获取SD卡目录
                        String zpstr = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/"+path;
                        //把之前图片的网址更换为图片保存SD卡的路径
                        data.get(i).put("litpic",zpstr);
                    }
                    Log.i("aaa","保存图片成功");
                    NewsDao dao = new NewsDao(getApplicationContext());
                    dao.insert(data);
                    Log.i("aaa","表中添加数据执行了。。。。");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    //图片保存到sd卡上
    public boolean SDpitcle(byte[] byt,String name){
        boolean boo = false;
        FileOutputStream fos = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            try {
                fos = new FileOutputStream(new File(file,name));
                fos.write(byt,0,byt.length);
                boo = true;
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return boo;
    }
}
