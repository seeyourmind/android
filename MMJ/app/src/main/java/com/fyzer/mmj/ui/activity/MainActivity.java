package com.fyzer.mmj.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fyzer.mmj.R;
import com.fyzer.mmj.ui.adapter.AccountAdapter;
import com.fyzer.mmj.ui.fragment.HomeFragment;
import com.fyzer.mmj.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 布局UI组件
    private TextView menu_home;
    private TextView menu_new;
    private TextView menu_mine;
    private FrameLayout fl;
    // Fragment对象
    private HomeFragment homeFragment;
    private UserFragment userFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();// 去掉标题栏（PS:AppCompatActivity中对应为ActionBar）
        setContentView(R.layout.main);

        fragmentManager = getFragmentManager();
        bindView();
        // 模拟一次点击，即选中该菜单
        menu_home.performClick();

    }
    // 菜单组件初始化与事件绑定
    private void bindView() {
        menu_home = (TextView) findViewById(R.id.menuHome);
        menu_new = (TextView) findViewById(R.id.menuNew);
        menu_mine = (TextView) findViewById(R.id.menuMine);
        fl = (FrameLayout) findViewById(R.id.menuFragment);

        menu_home.setOnClickListener(this);
        menu_new.setOnClickListener(this);
        menu_mine.setOnClickListener(this);
    }
    // 重置所有菜单项的选中状态
    private void setSelected() {
        menu_home.setSelected(false);
        menu_new.setSelected(false);
        menu_mine.setSelected(false);
    }
    // 隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) fragmentTransaction.hide(homeFragment);
        if (userFragment != null) fragmentTransaction.hide(userFragment);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.menuHome:
                setSelected();
                hideAllFragment(fragmentTransaction);
                menu_home.setSelected(true);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.menuFragment, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.menuMine:
                setSelected();
                hideAllFragment(fragmentTransaction);
                menu_mine.setSelected(true);
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    fragmentTransaction.add(R.id.menuFragment, userFragment);
                } else {
                    fragmentTransaction.show(userFragment);
                }
                break;
            case R.id.menuNew:
                setSelected();
                menu_new.setSelected(true);
                startActivity(new Intent().setClass(MainActivity.this, BillActivity.class));
                // 关闭当前activity，添加了该语句后，用户通过点击返回键是无法返回该activity的
                //MainActivity.this.finish();
        }
        fragmentTransaction.commit();
    }
}
