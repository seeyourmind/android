package com.example.bb_test1;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicInformationAdapter extends BaseAdapter {

	private LinkedList<DynamicInformation> di_data;
	private Context di_context;
	//��ʼ��������������һ���б����ݺ���ʾ�б��context
	public DynamicInformationAdapter(LinkedList<DynamicInformation> did, Context dic) {
		// TODO Auto-generated constructor stub
		this.di_data = did;
		this.di_context = dic;
	}
	//�õ��б����ݵĳ���
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return di_data.size();
	}
	//�õ���position���б�Ԫ��
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	//�õ���position���б�Ԫ�ص�id				
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	//����б����ͼ��������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(di_context).inflate(R.layout.layout_dynamic_information, parent, false);
		
		ImageView user_image = (ImageView)convertView.findViewById(R.id.ldi_userbar_userimage);
		TextView user_name = (TextView)convertView.findViewById(R.id.ldi_userbar_username);
		TextView time = (TextView)convertView.findViewById(R.id.ldi_userbar_time);
		TextView content_text = (TextView)convertView.findViewById(R.id.ldi_content_text);
		LinearLayout content_image = (LinearLayout)convertView.findViewById(R.id.layout_dynamic_information_content_image);
		Button reprint = (Button)convertView.findViewById(R.id.ldi_bottombar_reprint);
		Button comment = (Button)convertView.findViewById(R.id.ldi_bottombar_comment);
		Button praise = (Button)convertView.findViewById(R.id.ldi_bottombar_praise);
		
		user_name.setText(di_data.get(position).getUsername());
		time.setText(di_data.get(position).getContentText());
		
		
		return convertView;
	}

}
