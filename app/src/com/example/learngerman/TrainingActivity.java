package com.example.learngerman;

import java.util.ArrayList;
import java.util.Iterator;

import utils.DataB;
import utils.LearnItem;
import utils.MyAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
			Log.d("Training", toLearn.toString());
			totalItems = toLearn.size();
			final Button derButton = (Button)findViewById(R.id.derbutton);

			derButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					final boolean rez = checkCorrect("Der");
					if(rez)
						derButton.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					else{
						derButton.getBackground().setColorFilter(0xFFFF0000, Mode.MULTIPLY);
						getButtonByName(currentItem.correctAnswer).getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					}
					 new CountDownTimer(700, 700) {

					     public void onTick(long millisUntilFinished) {
					     }

					     public void onFinish() {
					    	 clearAllButtonColor();
					         goNext(rez);
					     }
					  }.start();
				}
			});

			final Button dasButton = (Button)findViewById(R.id.dasbutton);

			dasButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					final boolean rez = checkCorrect("Das");
					if(rez)
						dasButton.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					else{
						dasButton.getBackground().setColorFilter(0xFFFF0000, Mode.MULTIPLY);
						getButtonByName(currentItem.correctAnswer).getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					}
					 new CountDownTimer(700, 700) {

					     public void onTick(long millisUntilFinished) {
					     }

					     public void onFinish() {
					    	 clearAllButtonColor();
					         goNext(rez);
					     }
					  }.start();
				}
			});

			final Button dieButton = (Button)findViewById(R.id.diebutton);

			dieButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					final boolean rez = checkCorrect("Die");
					if(rez)
						dieButton.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					else{
						dieButton.getBackground().setColorFilter(0xFFFF0000, Mode.MULTIPLY);
						getButtonByName(currentItem.correctAnswer).getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
					}
					 new CountDownTimer(700, 700) {

					     public void onTick(long millisUntilFinished) {
					     }

					     public void onFinish() {
					    	 clearAllButtonColor();
					         goNext(rez);
					     }
					  }.start();
				}
			});
			currentItem = toLearn.remove(0);
			setData();
		}

	}

	public boolean checkCorrect(String type){
		return currentItem.correctAnswer.compareTo(type) == 0;
	}

	public void goNext(boolean correct){
		if(correct){
			learned.add(currentItem);
			counter++;
		}
		else{
			toLearn.add(currentItem);
		}
		if(!toLearn.isEmpty()){
			currentItem = toLearn.remove(0);
			setData();
		} else {
			Toast t = Toast.makeText(this, "Test finished", Toast.LENGTH_SHORT);
			t.show();
		}

	}

	public void setData(){
		TextView word = (TextView)findViewById(R.id.word);

		word.setText(currentItem.upperMessage);

		TextView trans = (TextView)findViewById(R.id.translation);

		trans.setText(currentItem.translation);

		TextView top = (TextView)findViewById(R.id.counter);

		top.setText(counter + "/" + totalItems);
	}

	public Button getButtonByName(String name){
		int Rid = -1;
		Button rez = null;

		if(name.compareTo("Der") == 0)
			Rid = R.id.derbutton;
		if(name.compareTo("Die") == 0)
			Rid = R.id.diebutton;
		if(name.compareTo("Das") == 0)
			Rid = R.id.dasbutton;

		if(Rid != -1){
			rez = (Button)findViewById(Rid);
		}
		return rez;
	}

	public void clearAllButtonColor(){
		Button bttn1 = (Button)findViewById(R.id.derbutton);

		Button bttn2 = (Button)findViewById(R.id.dasbutton);

		Button bttn3 = (Button)findViewById(R.id.diebutton);

//		bttn1.getBackground().setColorFilter(Color.GRAY, Mode.MULTIPLY);
//		bttn2.getBackground().setColorFilter(Color.GRAY, Mode.MULTIPLY);
//		bttn3.getBackground().setColorFilter(Color.GRAY, Mode.MULTIPLY);
		bttn1.getBackground().clearColorFilter();
		bttn2.getBackground().clearColorFilter();
		bttn3.getBackground().clearColorFilter();
	}
}
