package com.example.newscommunity.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newscommunity.R;

public abstract class BaseActivity extends AppCompatActivity {
    //访问当前应用的资源，启动一个新的activity的时候都需要提供Context
    //context 上下文;
    private Context mContext;

    //布局
    protected abstract int initLayout();
    //视图
    protected abstract void initView();
    //数据
    protected abstract void initData();

    //当一个Activity结束前，如果需要保存状态，就在onsaveInstanceState中，
    // 将状态数据以key-value的形式放入到savedInstanceState中，当一个Activity被创建时，
    // 就能从onCreate的参数savedInsanceState中获得状态数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }
}