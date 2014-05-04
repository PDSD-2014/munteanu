package server;

import java.util.HashMap;

public class ServerUtils {

	public static HashMap<String, String> parseResponse(String line){
		HashMap<String, String> result = new HashMap<>();

		String[] firstSplit = line.split("[&]");

		for(String param : firstSplit){
//			System.out.println(param);
			String[] secondSplit = param.split("=");
			if(secondSplit.length > 1)
				result.put(secondSplit[0], secondSplit[1]);
			else
				result.put(secondSplit[0], "");
		}
		return result;
	}
}
