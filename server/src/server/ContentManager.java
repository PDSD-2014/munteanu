package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class ContentManager {

	HashMap<String, ArrayList<String>> content;
	ContentManager(){
		content = new HashMap<>();
		final File folder = new File("training");
		List<String> files= listFilesForFolder(folder);

		for(String file : files){
			readFile(file);
		}

		System.out.println(content.toString());
	}

		public List<String> listFilesForFolder(final File folder) {
			ArrayList<String> fileNames = new ArrayList<>();

		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            fileNames.addAll(listFilesForFolder(fileEntry));
		        } else {
		            fileNames.add(fileEntry.getName());
		        }
		    }
		    return fileNames;
		}

		private void readFile(String file){
			String filename = "training/" + file;
			try {
				FileReader fl = new FileReader(filename);
				BufferedReader bf = new BufferedReader(fl);
				String str = bf.readLine();
				System.out.println(str);
				while(str != null){
					if(!content.containsKey(file))
						content.put(file, new ArrayList<String>());

					content.get(file).add(str);
					str = bf.readLine();
				}
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	public String getDataInJSon(){
		Gson gs = new Gson();
			return gs.toJson(content);

	}

}
