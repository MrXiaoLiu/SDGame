package com.zhg.sdgame.Utils;

import android.app.DownloadManager;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**http网络解析
 * Created by Administrator on 2016/7/6.
 */
public class HttpClient_Utils {

    public static byte[] httpclient(String urlpath){
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            URL url = new URL(urlpath);
            Log.i("mymy","url++11111111111");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            Log.i("mymy","url++2222222222");
            httpURLConnection.setRequestMethod("GET");
            Log.i("mymy","url++3333333333");
            httpURLConnection.setDoInput(true);
            Log.i("mymy","url++4444444444");
            httpURLConnection.connect();
            Log.i("mymy","url++5555555555");
            if((httpURLConnection.getResponseCode())==200){
                Log.i("mymy","url++66666666666");
                is = httpURLConnection.getInputStream();
                bos = new ByteArrayOutputStream();
                byte[] byt = new byte[1024*4];
                int len = 0;
                while ((len = is.read(byt))!=-1){
                    bos.write(byt,0,len);
                }
                byte[] b = bos.toByteArray();
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
