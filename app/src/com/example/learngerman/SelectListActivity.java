package com.example.learngerman;

import java.util.ArrayList;

import utils.DataB;
import utils.MyAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SelectListActivity extends Activity{

	Context myContext;
	DataB database;
	ArrayList<String> lessons;

	public SelectListActivity(){
		database = new DataB(this);

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_list);
		this.myContext = this;
		lessons = (ArrayList<String>)database.getIndex();

		ListView lw = (ListView)findViewById(R.id.listView1);


//    	final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
		MyAdapter mya = new MyAdapter(myContext,R.layout.row_list, lessons);

    	lw.setAdapter(mya);

    	Button bttn = (Button)findViewById(R.id.selectlistdone);

    	bttn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}


    	});
	}
}

