package utils;

import java.util.ArrayList;

public class LearnItem {

	String upperMessage;
	ArrayList<String> options;
	String translation;
	String correctAnswer;

	public LearnItem(String correctAnswer, String upperMessage, String translation){
		options = new ArrayList<>();
		options.add("Der");
		options.add("Die");
		options.add("Das");

		this.upperMessage = upperMessage;
		this.translation = translation;
		this.correctAnswer = correctAnswer;
	}
}
