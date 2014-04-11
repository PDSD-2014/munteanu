package com.example.learngerman;

import java.io.File;
import java.util.ArrayList;

import utils.LearnItem;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class TrainingActivity extends Activity {

	Context myContext;
	ArrayList<String> paths;
	ArrayList<LearnItem> toLearn;
	ArrayList<LearnItem> learned;

	public TrainingActivity(){
		File data = new File("res/localres/SimpleWords");
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.training_activity);
		this.myContext = this;
	}
}
