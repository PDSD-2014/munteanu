package com.example.learngerman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class StatisticsActivity extends Activity {

	Context myContext;
	public StatisticsActivity(){

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics_activity);
		this.myContext = this;
	}
}
