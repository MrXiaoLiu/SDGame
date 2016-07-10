package com.zhg.sdgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.zhg.sdgame.Utils.NetUtils;
import com.zhg.sdgame.service.DownLoadService;

import pl.droidsonroids.gif.GifImageView;

public class WelcomActivity extends AppCompatActivity {

    private GifImageView gifImageView;//获取图片控件
    private Animation animation;//设置动画效果
    private NetUtils newUtils;//判断网络是否链接类
    boolean netopen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        gifImageView = (GifImageView) this.findViewById(R.id.gifview_WelcomActivity_img);

        newUtils = new NetUtils();
        netopen = newUtils.netUtils(WelcomActivity.this);

        animation = new AlphaAnimation(0,1.0f);//设置为透明动画
        animation.setDuration(3000);//显示时间
        gifImageView.setAnimation(animation);
        //给动画设置监听，用于跳转页面
        animation.setAnimationListener(new Animation.AnimationListener() {
            //开始动画
            @Override
            public void onAnimationStart(Animation animation) {
                if(netopen) {
                    //进入欢迎动画页面时候就进入后台操作
                    Intent intent = new Intent(WelcomActivity.this, DownLoadService.class);
                    intent.putExtra("strurl","http://122.226.122.6/sitemap/api.php?row=20&typeid=1,2&paging=1&page=1");
                    startService(intent);
                }
            }

            //结束动画
            @Override
            public void onAnimationEnd(Animation animation) {
                if(!netopen){
                    Toast.makeText(WelcomActivity.this,"请检查网络",Toast.LENGTH_LONG).show();
                }
                isFristLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //判断是否第一次登录
    private void isFristLogin(){
        //获取共享参数对象
        SharedPreferences sharedPreferences = getSharedPreferences("isFistLogin", Context.MODE_APPEND);
        //获取sharedPreferences对象中的isLogin属性
        boolean boo = sharedPreferences.getBoolean("isLogin",false);
        //结束动画时，如果是第一次登录就进入向导Activity，如果不是就直接进入ManinActivity
        if(!boo){
            Intent guideIntent = new Intent(WelcomActivity.this,GuideActivity.class);
            startActivity(guideIntent);
            finish();
        }else {
            Intent MainIntent = new Intent(WelcomActivity.this,MainActivity.class);
            startActivity(MainIntent);
            finish();
        }
    }

}
