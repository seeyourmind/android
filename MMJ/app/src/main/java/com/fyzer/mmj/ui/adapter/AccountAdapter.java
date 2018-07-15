package com.fyzer.mmj.ui.adapter;

import android.content.Context;
import android.test.mock.MockApplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fyzer.mmj.R;

import java.util.List;
import java.util.Map;

/**
 * Created by XANXUS on 2017/10/29.
 */

public class AccountAdapter extends BaseAdapter {

    private static final int[] backgrounds = new int[]{R.drawable.background01, R.drawable.background02, R.drawable.background03};
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public AccountAdapter(Context context, List<Map<String, Object>> data) {
        super();
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.account_tag, null);
        convertView.setBackgroundResource(backgrounds[position]);

        TextView date_tag = (TextView)convertView.findViewById(R.id.date_tag);
        TextView money_tag = (TextView)convertView.findViewById(R.id.money_tag);

        date_tag.setText((String)data.get(position).get("dateRange"));
        money_tag.setText((String)data.get(position).get("moneyCount"));
        return convertView;
    }
}
