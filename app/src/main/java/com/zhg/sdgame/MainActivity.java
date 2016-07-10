package com.zhg.sdgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhg.adgame.dao.NewsDao;
import com.zhg.sdgame.Fragment.ArticleFragment;
import com.zhg.sdgame.Utils.HttpClient_Utils;
import com.zhg.sdgame.Utils.JSONUtils;
import com.zhg.sdgame.adapter.MyFragmentPagerAdapter;
import com.zhg.sdgame.service.DownLoadService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup top_rg;
    private RadioButton main_top_btn1,main_top_btn2,main_top_btn3,main_top_btn4,main_top_btn5,main_top_btn6,main_top_btn7,main_top_btn8,main_top_btn9,main_top_btn10;
    private RadioGroup bottom_rg;
    private RadioButton main_bottom_btn1,main_bottom_btn2,main_bottom_btn3;
    private ViewPager main_center_viewpager;

    private MyFragmentPagerAdapter fragmentadapter;
    private List<Fragment> fragmentList;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        actionBar = getSupportActionBar();
//        actionBar.setTitle("文章");
//        actionBar.setIcon(R.drawable.main_forum_bar);
//        actionBar.setDisplayShowTitleEnabled(true);
        initview();
        initListener();
        initData();
    }

    private void initData() {
                    fragmentList = new ArrayList<>();
                    ArticleFragment af1 = new ArticleFragment(getApplicationContext());
                    ArticleFragment af2 = new ArticleFragment(getApplicationContext());
                    ArticleFragment af3 = new ArticleFragment(getApplicationContext());
                    fragmentList.add(af1);
                    fragmentList.add(af2);
                    fragmentList.add(af3);
                    fragmentadapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
                    main_center_viewpager.setAdapter(fragmentadapter);


    }

    private void initListener() {
        top_rg.setOnCheckedChangeListener(this);
        bottom_rg.setOnCheckedChangeListener(this);
        main_center_viewpager.addOnPageChangeListener(this);
    }

    private void initview() {
        horizontalScrollView = (HorizontalScrollView) this.findViewById(R.id.main_top_hsv);
        top_rg = (RadioGroup)this.findViewById(R.id.main_top_rg);
        main_top_btn1 = (RadioButton)this.findViewById(R.id.main_top_btn1);
        main_top_btn2 = (RadioButton)this.findViewById(R.id.main_top_btn2);
        main_top_btn3 = (RadioButton)this.findViewById(R.id.main_top_btn3);
        main_top_btn4 = (RadioButton)this.findViewById(R.id.main_top_btn4);
        main_top_btn5 = (RadioButton)this.findViewById(R.id.main_top_btn5);
        main_top_btn6 = (RadioButton)this.findViewById(R.id.main_top_btn6);
        main_top_btn7 = (RadioButton)this.findViewById(R.id.main_top_btn7);
        main_top_btn8 = (RadioButton)this.findViewById(R.id.main_top_btn8);
        main_top_btn9 = (RadioButton)this.findViewById(R.id.main_top_btn9);
        main_top_btn10 = (RadioButton)this.findViewById(R.id.main_top_btn10);
        main_top_btn1.setChecked(true);
        bottom_rg = (RadioGroup)this.findViewById(R.id.main_bottom_rg);
        main_bottom_btn1 = (RadioButton)this.findViewById(R.id.main_bottom_btn1);
        main_bottom_btn2 = (RadioButton)this.findViewById(R.id.main_bottom_btn2);
        main_bottom_btn3 = (RadioButton)this.findViewById(R.id.main_bottom_btn3);
        main_bottom_btn1.setBackgroundColor(Color.GREEN);
        main_center_viewpager = (ViewPager)this.findViewById(R.id.main_center_viewpager);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_top_btn1:
                break;
            case R.id.main_top_btn2:
                Intent intent2 = new Intent(MainActivity.this, DownLoadService.class);
                intent2.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=2,2&paging=1&page=1");
                startService(intent2);
                break;
            case R.id.main_top_btn3:
                Intent intent3 = new Intent(MainActivity.this, DownLoadService.class);
                intent3.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=151,2&paging=1&page=1");
                startService(intent3);
                break;
            case R.id.main_top_btn4:
                Intent intent4 = new Intent(MainActivity.this, DownLoadService.class);
                intent4.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=152,2&paging=1&page=1");
                startService(intent4);
                break;
            case R.id.main_top_btn5:
                Intent intent5 = new Intent(MainActivity.this, DownLoadService.class);
                intent5.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=153,2&paging=1&page=1");
                startService(intent5);
                break;
            case R.id.main_top_btn6:
                Intent intent6 = new Intent(MainActivity.this, DownLoadService.class);
                intent6.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=154,2&paging=1&page=1");
                startService(intent6);
                break;
            case R.id.main_top_btn7:
                Intent intent7 = new Intent(MainActivity.this, DownLoadService.class);
                intent7.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=196,2&paging=1&page=1");
                startService(intent7);
                break;
            case R.id.main_top_btn8:
                Intent intent8 = new Intent(MainActivity.this, DownLoadService.class);
                intent8.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=197,2&paging=1&page=1");
                startService(intent8);
                break;
            case R.id.main_top_btn9:
                Intent intent9 = new Intent(MainActivity.this, DownLoadService.class);
                intent9.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=199,2&paging=1&page=1");
                startService(intent9);
                break;
            case R.id.main_top_btn10:
                Intent intent10 = new Intent(MainActivity.this, DownLoadService.class);
                intent10.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=25,2&paging=1&page=1");
                startService(intent10);
                break;
            case R.id.main_bottom_btn1:
                main_bottom_btn1.setBackgroundColor(Color.GREEN);
                main_bottom_btn2.setBackgroundColor(Color.BLACK);
                main_bottom_btn3.setBackgroundColor(Color.BLACK);
                break;
            case R.id.main_bottom_btn2:
                Intent intent = new Intent(MainActivity.this,MainForumActivity.class);
                intent.putExtra("url","http://bbs.3dmgame.com/forum.php");
                startActivity(intent);
                main_bottom_btn2.setBackgroundColor(Color.GREEN);
                main_bottom_btn1.setBackgroundColor(Color.BLACK);
                main_bottom_btn3.setBackgroundColor(Color.BLACK);
                break;
            case R.id.main_bottom_btn3:
                main_bottom_btn3.setBackgroundColor(Color.GREEN);
                main_bottom_btn2.setBackgroundColor(Color.BLACK);
                main_bottom_btn1.setBackgroundColor(Color.BLACK);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //顶部的滚动条出现移动效果
        horizontalScrollView.setVisibility(View.VISIBLE);
        top_rg.setVisibility(View.VISIBLE);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        new NewsDao(getApplicationContext()).delete();
//    }
}
