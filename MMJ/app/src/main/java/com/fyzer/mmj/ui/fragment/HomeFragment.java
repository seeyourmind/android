package com.fyzer.mmj.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fyzer.mmj.R;
import com.fyzer.mmj.service.entity.DateClass;
import com.fyzer.mmj.ui.adapter.AccountAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by XANXUS on 2017/11/19.
 */

public class HomeFragment extends Fragment {
    private PieChartView pieChartView;
    private TextView dateDay;
    private TextView dateMonth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        // 初始化 设置日期与饼图数据显示
        getUI(view);
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
        AccountAdapter adapter = new AccountAdapter(getActivity(), listitem);
        ListView listView = (ListView)view.findViewById(R.id.account_three);
        listView.setAdapter(adapter);
        return view;
    }
    // UI组件初始化
    private void getUI(View view){
        pieChartView = (PieChartView) view.findViewById(R.id.pie);
        dateDay = (TextView) view.findViewById(R.id.date_day);
        dateMonth = (TextView) view.findViewById(R.id.date_month);

        // 设置饼图
        setPieChartData(pieChartView);
        // 设置时间
        setDate();
    }

    /**
     * 设置饼图
     * @param pieChartView
     */
    private void setPieChartData(PieChartView pieChartView) {
        final PieChartData pieChartData = new PieChartData();
        List<SliceValue> values = new ArrayList<>();
        // 定义数据
        int[] data = {21, 20, 9, 2, 8};
        final int[] colorData = {Color.parseColor("#ec063d"),
                Color.parseColor("#f1c704"),
                Color.parseColor("#c9c9c9"),
                Color.parseColor("#2bc208"),
                Color.parseColor("#333333")};
        final String[] stateChar = {"报警", "故障", "离线", "正常", "未激活"};
        for(int i = 0; i < data.length; i++) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            values.add(sliceValue);
        }
        // 初始化
        // PieCharData设置项
        pieChartData.setHasLabels(false);// 显示标题
        pieChartData.setHasLabelsOnlyForSelected(false);// 不用点击 显示所占百分比
        pieChartData.setHasLabelsOutside(false);// 所占百分比是否显示在饼图外
        pieChartData.setHasCenterCircle(true); // 是否为环形显示
        pieChartData.setCenterCircleColor(Color.WHITE);// 设置环形中心颜色
        pieChartData.setCenterCircleScale(0.5f);// 设置环形大小级别
        pieChartData.setValues(values);// 填充数据
        // PieCharView设置项
        pieChartView.setPieChartData(pieChartData);
        pieChartView.setValueSelectionEnabled(true);// 选择某块时变大
        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                // 选择对应图形后，在中间显示对应信息
                pieChartData.setCenterText1(stateChar[arcIndex]);
                pieChartData.setCenterText1Color(colorData[arcIndex]);
                pieChartData.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int)getResources().getDimension(R.dimen.pie_text_1)));
                pieChartData.setCenterText2(value.getValue() + "元");
                pieChartData.setCenterText2Color(colorData[arcIndex]);
                pieChartData.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int)getResources().getDimension(R.dimen.pie_text_2)));
            }

            @Override
            public void onValueDeselected() {

            }
        });// 设置点击事件
    }

    /**
     * 设置日期显示
     */
    private void setDate () {
        DateClass dateClass = new DateClass();
        dateDay.setText(dateClass.getDay());
        dateMonth.setText(dateClass.getMonth());
    }
}
