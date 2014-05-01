package com.example.learngerman;

import utils.ServerRequests;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Context myContext;
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.myContext = this;



		Button skip  = (Button)findViewById(R.id.skip);

		skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(myContext, SelectCourseActivity.class);

				startActivity(in);
			}

		});

		Button login  = (Button)findViewById(R.id.login);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String loading = "Checking data";
				ServerRequests sr = new ServerRequests();

				sr.makeRequest();
					dialog =new ProgressDialog(myContext);
					dialog.setMessage(loading);
					dialog.setCancelable(false);
					dialog.setInverseBackgroundForced(false);
					dialog.show();


			}

		});
	}

	public void startNextActivity(){

		dialog.hide();
		Intent in = new Intent(myContext, SelectCourseActivity.class);
		startActivity(in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}