package com.zhg.adgame.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.zhg.sdgame.Table.MySQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class NewsDao {
MySQLiteOpenHelper helper;

    public NewsDao(Context context) {
        helper = new MySQLiteOpenHelper(context);

    }

    //填充表
    public boolean insert(List<HashMap<String,Object>> data){
        SQLiteDatabase db=null;
        try{
            db = helper.getReadableDatabase();
        HashMap<String,Object> map = new HashMap<>();
        for(int i =0;i<data.size();i++) {
            map = data.get(i);
            db.execSQL("insert into news(shorttitle,litpic,senddate,click,arcurl) values(?,?,?,?,?)",
                    new Object[]{map.get("shorttitle"),map.get("litpic"),map.get("senddate"),map.get("click"),map.get("arcurl")});
        }
        return true;
    }catch (Exception e){

        }finally{
            if (db !=null&&db.isOpen()){
                db.close();
            }
        }
                return false;
    }

    //取出数据
    public List<HashMap<String,Object>> getlist(){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<HashMap<String,Object>> data;
        try {
            db = helper.getReadableDatabase();
            data = new ArrayList<HashMap<String,Object>>();
            cursor = db.rawQuery("select * from news",null);
            while (cursor.moveToNext()){
                String shorttitle = cursor.getString(cursor.getColumnIndex("shorttitle"));
                //得到图片路径
                String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                byte[] b = ToBitmap(litpic);
                //压缩图片
                Bitmap bitmap=ImageCompression.getcompressbitmap(b,20,20);
                //  得到时间
                String senddate = cursor.getString(cursor.getColumnIndex("senddate"));
                String click = cursor.getString(cursor.getColumnIndex("click"));
                String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put("shorttitle",shorttitle);
                map.put("litpic",bitmap);
                map.put("senddate",senddate);
                map.put("click",click);
                map.put("arcurl",arcurl);
                data.add(map);
            }
            return data;
        } catch (Exception e) {

        }finally {
            if (cursor != null&&!cursor.isClosed()){
                cursor.close();
            }if(db != null&&db.isOpen()){
                db.close();
            }
        }
        return null;
    }

    //得到图片方法
    public byte[] ToBitmap(String path){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            byte[] byt = new byte[1024];
            int len = 0;
            while ((len = fis.read(byt))!=-1){
                bos.write(byt,0,len);

            }return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            return null;
    }

    //删除
    public boolean delete(){
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.execSQL("delete from news");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db !=null&&db.isOpen()){
                db.close();
            }
        }
return false;
    }


}
