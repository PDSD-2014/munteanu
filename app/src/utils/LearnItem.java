package utils;

import java.util.ArrayList;

public class LearnItem {

	public String upperMessage;
	public ArrayList<String> options;
	public String translation;
	public String correctAnswer;
	boolean checked;

	public LearnItem(String correctAnswer, String upperMessage, String translation){
		options = new ArrayList<>();
		options.add("Der");
		options.add("Die");
		options.add("Das");

		this.upperMessage = upperMessage;
		this.translation = translation;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString(){
		return correctAnswer + " " + upperMessage + " " + translation;
	}
}
