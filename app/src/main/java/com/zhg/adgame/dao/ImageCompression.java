package com.zhg.adgame.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ImageCompression {

    public static Bitmap getcompressbitmap(byte[] data,int picwidth,int picheight){

        //原始图片大小
        Bitmap initBitmap = BitmapFactory.decodeByteArray(data,0,data.length);
        Log.i("aaa","原始的图片大小是："+initBitmap.getByteCount());
        //获取图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(data,0,data.length,options);

        //原始图片宽高
        int width = options.outWidth;
        int height = options.outHeight;

        //得压缩比
        options.inSampleSize = getcompressbitmapsize(options,picwidth,picheight);
        Log.i("aaa","inSampleSize="+options.inSampleSize);
        //开始压缩图片
        options.inJustDecodeBounds=false;
        Bitmap bitmapsize = BitmapFactory.decodeByteArray(data,0,data.length,options);
        Log.i("aaa","压缩后图片大小"+bitmapsize.getByteCount());
        return bitmapsize;
    }

    public static int getcompressbitmapsize(BitmapFactory.Options options,int picwidth,int picheight){
        int width = options.outWidth;
        int height = options.outHeight;
        //设置初始缩放比例大小为1
        int xy=1;
        //判断原始图片宽高大于期望宽高才缩放
        if (width>picwidth||height>picheight){
            int compresswidth = Math.round((float)width/picwidth);
            int compressheight = Math.round((float)height/picheight);
            xy = compressheight>compresswidth?compresswidth:compressheight;

        }

        return xy;
    }
}
