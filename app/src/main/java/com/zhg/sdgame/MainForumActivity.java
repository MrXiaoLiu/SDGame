package com.zhg.sdgame;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MainForumActivity extends AppCompatActivity {
    ActionBar actionBar;
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forum);
        webview = (WebView) this.findViewById(R.id.main_forum_activity_webview);
        actionBar=getSupportActionBar();

        actionBar.setIcon(R.drawable.main_forum_bar);
        //设置标题能够被显示
        actionBar.setDisplayShowHomeEnabled(true);
        //设置标题能够被点击
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("论坛");
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);

    }
}
