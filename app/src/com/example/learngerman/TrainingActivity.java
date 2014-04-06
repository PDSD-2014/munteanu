package com.example.learngerman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class TrainingActivity extends Activity {

	Context myContext;

	public TrainingActivity(){

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.training_activity);
		this.myContext = this;
	}
}
