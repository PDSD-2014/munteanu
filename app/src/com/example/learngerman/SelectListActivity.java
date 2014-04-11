package com.example.learngerman;

import java.util.ArrayList;

import utils.MyAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SelectListActivity extends Activity{

	Context myContext;

    String[] strmovies = new String[] {
    		"Basic 50 WOrds",
    		"Animals",
    		"House",
    		"People",
    		"Party"
        };

	public SelectListActivity(){

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_list);
		this.myContext = this;

		ListView lw = (ListView)findViewById(R.id.listView1);

        final ArrayList<String> movies = new ArrayList<String>();
        for(int i=0; i < strmovies.length; i++)
        	movies.add(strmovies[i]);

    	final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
		MyAdapter mya = new MyAdapter(myContext,R.layout.row_list, movies);

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

