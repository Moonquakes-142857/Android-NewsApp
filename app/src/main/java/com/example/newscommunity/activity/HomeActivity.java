package com.example.newscommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

import com.example.newscommunity.R;
import com.example.newscommunity.fragment.AttentionFragment;
import com.example.newscommunity.fragment.FindFragment;
import com.example.newscommunity.fragment.MyFragment;
import com.example.newscommunity.fragment.TodayFragment;

public class HomeActivity extends BaseActivity {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab, TodayFragment.newInstance("今日"));
        mFragmentSparseArray.append(R.id.attention_tab, AttentionFragment.newInstance("关注"));
        mFragmentSparseArray.append(R.id.find_tab, FindFragment.newInstance("发现"));
        mFragmentSparseArray.append(R.id.my_tab, MyFragment.newInstance("我的"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.today_tab)).commit();
        findViewById(R.id.sign_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ReleaseActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }
}