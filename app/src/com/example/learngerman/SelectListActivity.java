package com.example.learngerman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class SelectListActivity extends Activity{

	Context myContext;

	public SelectListActivity(){

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_list);
		this.myContext = this;
	}
}

