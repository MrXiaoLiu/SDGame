package com.zhg.sdgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhg.sdgame.adapter.MyPagerViewAdapter;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private LayoutInflater layoutInflater;
    private ImageView[] dots;
    private List<View> views;
    private MyPagerViewAdapter adapter;
    int currentIndex;//当前页面索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDot();


    }

    //初始化所有的点
    private void initDot() {
        LinearLayout lay = (LinearLayout) findViewById(R.id.activity_guide_pager_lay01);
        dots = new ImageView[views.size()];
        //得到线性布局下面所有的点的对象
        for(int i=0;i<views.size();i++){
                dots[i] = (ImageView) lay.getChildAt(i);
                dots[i].setEnabled(true);//设置默认都为黑点
        }
        //初始化当前所在pager索引值
        currentIndex=0;
        //设置当前点为白色
        dots[currentIndex].setEnabled(false);
    }

    //初始化所有View
    private void initView() {
        viewPager = (ViewPager) this.findViewById(R.id.activity_guide_pager_viewpager);
        layoutInflater =  LayoutInflater.from(this);

        views = new ArrayList<>();
        View view1 = layoutInflater.inflate(R.layout.activity_guide_pager01,null);
        View view2 = layoutInflater.inflate(R.layout.activity_guide_pager02,null);
        View view3 = layoutInflater.inflate(R.layout.activity_guide_pager03,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        adapter = new MyPagerViewAdapter(views);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

}

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //设置底部点的颜色
        if(position<0||position+1>views.size()){
            return;
        }
        //设置当前位置为选中状态
        dots[position].setEnabled(false);
        //设置当前位置为非选中状态
        dots[currentIndex].setEnabled(true);
        currentIndex = position;

        //添加最后一个引导界面的Button监听
        if (position==views.size()-1){
            Button btn1 = (Button)views.get(position).findViewById(R.id.activity_guide_pager_btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setGuide();

                    Intent mainIntent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            });

        }
    }

    //保存登陆过的信息
    public void setGuide(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFistLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        editor.commit();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
