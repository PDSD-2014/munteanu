package server;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class ContentManager {

	HashMap<String, ArrayList<String>> content;
	ContentManager(){
		content = new HashMap<>();
	}


	private void readFromFile(){

	}

	public String getDataInJSon(){
		Gson gs = new Gson();
			return gs.toJson(content);

	}

}
