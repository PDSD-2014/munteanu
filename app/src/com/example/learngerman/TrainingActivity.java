package com.example.learngerman;

import java.util.ArrayList;
import java.util.Iterator;

import utils.DataB;
import utils.LearnItem;
import utils.MyAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TrainingActivity extends Activity {

	Context myContext;
	ArrayList<LearnItem> toLearn;
	ArrayList<LearnItem> learned;
	LearnItem currentItem;
	DataB database;
	int counter;
	int totalItems;

	public TrainingActivity(){
		database = new DataB(this);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.training_activity);
		this.myContext = this;
		counter = 0;
		learned = new ArrayList<>();

		ArrayList<String> sel = new ArrayList<>();

		Iterator<String> it = MyAdapter.selected.iterator();

		while(it.hasNext()){
			sel.add(it.next());
		}

		if(!sel.isEmpty()){
			toLearn = (ArrayList<LearnItem>) database.getSelectedItems(sel);
			totalItems = toLearn.size();
			Button derButton = (Button)findViewById(R.id.derbutton);

			derButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goNext(checkCorrect("Der"));
				}
			});

			Button dasButton = (Button)findViewById(R.id.dasbutton);

			dasButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goNext(checkCorrect("Das"));
				}
			});

			Button dieButton = (Button)findViewById(R.id.diebutton);

			dieButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					goNext(checkCorrect("Die"));
				}
			});
			currentItem = toLearn.remove(0);
			setData();
		}

	}

	public boolean checkCorrect(String type){
		return currentItem.correctAnswer == type;
	}

	public void goNext(boolean correct){
		if(correct){
			learned.add(currentItem);
			counter++;
		}
		else{
			toLearn.add(currentItem);
		}
		currentItem = toLearn.remove(0);
		setData();
	}

	public void setData(){
		TextView word = (TextView)findViewById(R.id.word);

		word.setText(currentItem.upperMessage);

		TextView trans = (TextView)findViewById(R.id.translation);

		trans.setText(currentItem.translation);

		TextView top = (TextView)findViewById(R.id.counter);

		top.setText(counter + "/" + totalItems);
	}
}
