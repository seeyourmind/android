package com.fyzer.mmj.ui.fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fyzer.mmj.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XANXUS on 2017/11/19.
 */

public class UserFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myself, container, false);
        // 初始化菜單列表
        String[] menuStrings = {"历史账单分析", "历史账单查看"};
        //String[] menuIcons = {"&#xe65f;", "&#xe73f;"};
        // 创建Map列表
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < menuStrings.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("menuName", menuStrings[i]);
            //map.put("menuIcon", menuIcons[i]);
            mapList.add(map);
        }
        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), mapList, R.layout.menu,
                new String[]{"menuName"}, new int[]{R.id.menu_name});
        ListView listView = (ListView) view.findViewById(R.id.menuList);
        listView.setAdapter(adapter);
        TextView iconText = (TextView) view.findViewById(R.id.menu_icon);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iconfont.ttf");
        Log.i("iconfont-->", iconfont.toString());
        iconText.setTypeface(iconfont);

        iconText.setText("\ue66f");
        return view;
    }
}
