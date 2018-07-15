package com.example.bb_test1;

import java.util.LinkedList;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	private HomeFragment homeFragment;
	private PersonFragment personFragment;
	private FindFragment findFragment;
	private FragmentManager fm;
	private FragmentTransaction transaction;
	private Context mContext;
	private LinkedList<DynamicInformation> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		findViewById(R.id.home_button).setOnClickListener(this);
		findViewById(R.id.person_button).setOnClickListener(this);
		findViewById(R.id.find_button).setOnClickListener(this);

		datas = new LinkedList<DynamicInformation>();
		datas.add(new DynamicInformation("xanxus", "今天9:57:41"));

		setDefaultFragment(datas);

		findViewById(R.id.new_message_button).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(MainActivity.this,
								SendMessageActivity.class));
					}
				});
	}

	private void setDefaultFragment(LinkedList<DynamicInformation> datas) {
		// TODO Auto-generated method stub
		mContext = MainActivity.this;
		fm = getFragmentManager();
		transaction = fm.beginTransaction();
		homeFragment = new HomeFragment(fm, datas);
		transaction.replace(R.id.id_fragment, homeFragment);
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		// 开启Fragment事务
		FragmentTransaction transaction = fm.beginTransaction();

		LinkedList<DynamicInformation> datas = new LinkedList<DynamicInformation>();
		datas.add(new DynamicInformation("xanxus01", "今天9:57:41"));

		switch (v.getId()) {
		case R.id.home_button:
			if (homeFragment == null) {
				homeFragment = new HomeFragment(fm, datas);
			}
			// 使用当前Fragment的布局替代id_content的控件
			transaction.replace(R.id.id_fragment, homeFragment);
			break;
		case R.id.find_button:
			if (findFragment == null) {
				findFragment = new FindFragment();
			}
			transaction.replace(R.id.id_fragment, findFragment);
			break;
		case R.id.person_button:
			if (personFragment == null) {
				personFragment = new PersonFragment();
			}
			transaction.replace(R.id.id_fragment, personFragment);
			break;
		}
		// transaction.addToBackStack();
		// 事务提交
		transaction.commit();
	}

	// 点击回退键的处理：判断Fragment栈中是否有Fragment
	// 没，双击退出程序，否则像是Toast提示
	// 有，popbackstack弹出栈
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

}
