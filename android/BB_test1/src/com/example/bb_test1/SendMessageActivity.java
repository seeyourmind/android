package com.example.bb_test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SendMessageActivity extends Activity {

	EditText mText = (EditText) findViewById(R.id.message);
	DynamicInformation di = new DynamicInformation();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
		
		findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SendMessageActivity.this, MainActivity.class));
				finish();
			}
		});
		
		findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				di.setContentText(mText.getText().toString());
				di.setUsername("xanxus");
				di.setTime("½ñÌì9:57:41");
			}
		});
	}
}
