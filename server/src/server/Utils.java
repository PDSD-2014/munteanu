package server;

import java.util.HashMap;

public class Utils {

	public static HashMap<String, String> parseResponse(String line){
		HashMap<String, String> result = new HashMap<>();

		String[] firstSplit = line.split("[&]");

		for(String param : firstSplit){
			System.out.println(param);
		}
		return result;
	}
}
