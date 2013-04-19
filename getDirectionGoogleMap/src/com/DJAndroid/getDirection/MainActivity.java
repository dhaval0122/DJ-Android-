package com.DJAndroid.getDirection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText edits1;
	private EditText edits2;
	private EditText editd1;
	private EditText editd2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		edits1 = (EditText) findViewById(R.id.s1);
		edits2 = (EditText) findViewById(R.id.s2);

		editd1 = (EditText) findViewById(R.id.d1);
		editd2 = (EditText) findViewById(R.id.d2);

	}

	public void Btnclick(View v) {
		if (v.getId() == R.id.button1) {
			finish();
		}

		if (v.getId() == R.id.button2) {
			Intent i = new Intent(MainActivity.this, showMaps.class);
			i.putExtra("spFrom_lat", edits1.getText().toString());
			i.putExtra("spFrom_long", edits2.getText().toString());
			i.putExtra("spTo_lat", editd1.getText().toString());
			i.putExtra("spTo_long", editd2.getText().toString());
			startActivity(i);
		}
	}

}
