package utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {


	public static HashMap<String, ArrayList<LearnItem>> parseMyJson(String data){
		HashMap<String, ArrayList<LearnItem>> result = new HashMap<>();

		try {
			JSONObject jObject = new JSONObject(data);

			JSONArray mappings = jObject.names();

			for(int i = 0; i < mappings.length(); i++){
//				Log.d("JSON", mappings.get(i).toString());
				String set = mappings.get(i).toString();
				result.put(set, new ArrayList<LearnItem>());
				JSONArray content = jObject.getJSONArray(set);

				for(int j = 0; j < content.length(); j++)
				{
					String[] learnData  = content.getString(j).split(" ");
					if(learnData.length == 3)
						result.get(set).add(new LearnItem(learnData[0], learnData[1], learnData[2]));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
