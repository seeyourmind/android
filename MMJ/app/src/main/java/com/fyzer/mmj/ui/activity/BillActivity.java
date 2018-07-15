package com.fyzer.mmj.ui.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.fyzer.mmj.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_page);

        // 设置账单列表
        String[] times = new String[]{"2017-06-03 13:14:47", "2016-06-03 13:14:47", "2015-06-03 13:14:47"};
        String[] descriptions = new String[]{"吃饭", "吃饭三餐", "无形装逼最为致命"};
        String[] moneys = new String[]{"10.00", "10.00", "10.00"};

        List<Map<String, Object>> listitem = new ArrayList<>();
        for (int i=0; i<times.length; i++){
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("time", times[i]);
            showitem.put("description", descriptions[i]);
            showitem.put("money", moneys[i]);
            listitem.add(showitem);
        }

        // 创建一个simpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.bill_tag,
                new String[]{"time", "description", "money"}, new int[]{R.id.time, R.id.description, R.id.money});
        ListView listView = (ListView)findViewById(R.id.bill_list);
        listView.setAdapter(adapter);

        // 设置Icon Font图标
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "fonts/iconfont.ttf");
        Log.i("iconfont-->", iconfont.toString());
        Button button_submit = (Button) findViewById(R.id.button_submit);
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        Button button_add = (Button) findViewById(R.id.button_add);
        button_submit.setTypeface(iconfont);
        button_cancel.setTypeface(iconfont);
        button_add.setTypeface(iconfont);
    }
}
