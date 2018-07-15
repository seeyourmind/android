package com.fyzer.mmj.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fyzer.mmj.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XANXUS on 2017/11/18.
 */

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);

        // 初始化菜單列表
        String[] menuStrings = {"历史账单分析", "历史账单查看"};
        // 创建Map列表
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < menuStrings.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("menuName", menuStrings[i]);
            mapList.add(map);
        }
        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), mapList, R.layout.menu,
                new String[]{"menuName"}, new int[]{R.id.menu_name});
        ListView listView = (ListView) findViewById(R.id.menuList);
        listView.setAdapter(adapter);
    }
}
