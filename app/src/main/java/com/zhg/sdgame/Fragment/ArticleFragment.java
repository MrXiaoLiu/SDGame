package com.zhg.sdgame.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhg.adgame.dao.NewsDao;
import com.zhg.sdgame.AsyncTask.PullToRefresh_AsyncTask;
import com.zhg.sdgame.MainWebViewActivity;
import com.zhg.sdgame.R;
import com.zhg.sdgame.Utils.JSONUtils;
import com.zhg.sdgame.adapter.MyArticleFragmentAdapter;
import com.zhg.sdgame.adapter.MyRefreshAdapter;
import com.zhg.sdgame.customview.MainArticleFragmentViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ArticleFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{
    private MyArticleFragmentAdapter adapter;
    private Context context;
    int currentIndex;//当前页面索引
    List<View> viewdata;

    MyRefreshAdapter myAdapter;
    PullToRefreshListView pullToRefreshListView;

    NewsDao dao;

    public ArticleFragment(Context context) {
        this.context = context;
        dao = new NewsDao(context);
    }
    public ArticleFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得Fragment整体布局
        View view = inflater.inflate(R.layout.activity_main_fragmentpagerview,null);
        //获得Fragment中的Viewpager
        MainArticleFragmentViewPager mainArticleFragmentViewPager =(MainArticleFragmentViewPager) view.findViewById(R.id.main_article_fragment);
        //获得Fragment中PullToRefresh
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.main_activity_pulltorefresh);
        //获得Fragment中View
        viewdata = new ArrayList<>();
        View v1 = view.findViewById(R.id.main_article_view01);
        View v2 = view.findViewById(R.id.main_article_view02);
        View v3 = view.findViewById(R.id.main_article_view03);
        viewdata.add(v1);
        viewdata.add(v2);
        viewdata.add(v3);
        currentIndex = 0;
        //设置当前点为红色
        viewdata.get(currentIndex).setBackgroundColor(Color.RED);


        //创建PullToRefresh自定义适配器
        myAdapter = new MyRefreshAdapter(context,dao.getlist());
        pullToRefreshListView.setAdapter(myAdapter);
        //设置PullToRefresh点击监听
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MainWebViewActivity.class);
//                intent.putExtra("url","http://www.3dmgame.com/news/201607/3576178.html");
                startActivity(intent);
            }
        });

        final int imagRsID[]= {R.drawable.main_fragment_pager1,R.drawable.main_fragment_pager2,R.drawable.main_fragment_pager3};
       List<ImageView> imglist = new ArrayList<>();
        for(int i=0;i<3;i++){
            ImageView img = new ImageView(getActivity());
            //设置图片缩放类型  铺满全屏
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(imagRsID[i]);
            imglist.add(img);
        }
        adapter = new MyArticleFragmentAdapter(imglist);
        mainArticleFragmentViewPager.setAdapter(adapter);

        //设置Viewpager监听
        mainArticleFragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewdata.get(position).setBackgroundColor(Color.RED);
                viewdata.get(currentIndex).setBackgroundColor(Color.WHITE);
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


    //下拉刷新监听
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        PullToRefresh_AsyncTask asyncTask = new PullToRefresh_AsyncTask(context,myAdapter,pullToRefreshListView);
//        asyncTask.execute("http://122.226.122.6/sitemap/api.php?row=1&typeid=1,2&paging=1&page=5");
    }

    //上拉刷新监听
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
