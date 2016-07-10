package com.zhg.sdgame.Table;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MySQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    public MySQLiteOpenHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists news(shorttitle varchar(1000)," +
                "litpic varchar(1000)," +
                "senddate varchar(1000)," +
                "click varchar(1000)," +
                "arcurl varchar(1000))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
