package com.zhg.sdgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainWebViewActivity extends AppCompatActivity {

    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_view);
        Intent intent = getIntent();
       final String str =  intent.getStringExtra("url");
        wv = (WebView) findViewById(R.id.main_webview_activity_wv);
        wv.loadUrl(str);
    }
}
