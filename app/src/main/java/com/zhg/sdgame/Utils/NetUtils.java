package com.zhg.sdgame.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络是否链接工具类
 * Created by Administrator on 2016/7/5.
 */
public class NetUtils {

    public static boolean netUtils(Activity activity){
        boolean fal = false;
        //得到链接的管理对象
        ConnectivityManager manager=(ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //判断管理对象如果为空，则直接返回
        if(manager == null){
            return fal;
        }
        //根据链接的管理对象，得到网络的信息对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断管理对象如果不为空，或者信息对象是活动的，说明链接成功
        if(manager!=null||networkInfo.isFailover()){
            fal=true;
        }
        return  fal;
    }
}
