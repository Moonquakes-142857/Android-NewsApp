package com.example.newscommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.newscommunity.activity.BaseActivity;
import com.example.newscommunity.activity.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    private int recLen = 5;//跳过倒计时提示5秒
    private TextView textView;
    Timer timer = new Timer();
    //用来更新UI的一套机制，也是一套消息处理机制，我们可以发消息，也可以通过它处理消息
    //最根本的目的就是为了解决多线程并发的问题
    private Handler handler;
    private Runnable runnable;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.main_skip_btn);
    }

    @Override
    protected void initData() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.main_skip_btn){
                    //从闪屏界面跳转到首界面
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    if (runnable!=null){
                        handler.removeCallbacks(runnable);
                    }
                }
            }
        });
        timer.schedule(task, 1000, 1000);//等待时间0秒，停顿时间一秒
        //正常情况下不点跳过
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    textView.setText("跳过(" + recLen + "S)");
                    if (recLen < 0) {
                        timer.cancel();
                        textView.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };
}