package com.example.learngerman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectCourseActivity extends Activity{

	Context myContext;

	public SelectCourseActivity(){

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_courses);
		this.myContext = this;

		Button selectWords = (Button)findViewById(R.id.selectwords);

		selectWords.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(myContext, SelectListActivity.class);
				startActivity(in);
			}

		});

		Button startTraining = (Button)findViewById(R.id.starttraining);

		startTraining.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent in = new Intent(myContext, TrainingActivity.class);
				startActivity(in);

			}

		});

		Button showStatistics = (Button)findViewById(R.id.viewstatistics);

		showStatistics.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent in = new Intent(myContext, StatisticsActivity.class);
				startActivity(in);
			}

		});
	}
}
