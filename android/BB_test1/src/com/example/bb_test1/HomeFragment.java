package com.example.bb_test1;

import java.util.LinkedList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{

	private FragmentManager fManager;
	private LinkedList<DynamicInformation> di_data = null;
	private DynamicInformationAdapter di_adapter = null;
	private ListView di_list;
	
	public HomeFragment(FragmentManager fManager, LinkedList<DynamicInformation> datas) {
		// TODO Auto-generated constructor stub
		this.fManager = fManager;
		this.di_data = datas;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		di_list = (ListView)view.findViewById(R.id.list_view);
		di_adapter = new DynamicInformationAdapter(di_data, getActivity());
		di_list.setAdapter(di_adapter);
		di_list.setOnItemClickListener(this);
		return view;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}
