package com.fyzer.mmj.ui.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fyzer.mmj.R;
import com.fyzer.mmj.ui.adapter.AccountAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        TextView date_day = (TextView)findViewById(R.id.date_day);
        TextView date_month = (TextView) findViewById(R.id.date_month);

        // 设置账单列表
        String[] date_tag = new String[]{"日消费", "周消费", "月消费"};
        Double[]  money_count = new Double[]{20.00, 140.00, 600.00};

        List<Map<String, Object>> listitem = new ArrayList<>();
        for (int i=0; i<3; i++){
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("dateRange", date_tag[i]);
            showitem.put("moneyCount", Double.toString(money_count[i])+"元");
            listitem.add(showitem);
        }

        AccountAdapter adapter = new AccountAdapter(getApplicationContext(), listitem);

        ListView listView = (ListView)findViewById(R.id.account_three);
        listView.setAdapter(adapter);
    }
}
