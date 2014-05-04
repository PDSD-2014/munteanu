package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class HandleUsers {

	String filename = "user.data";
	HashMap<String, String> userList;
	HandleUsers(){
		System.out.println("Hello");
		userList = new HashMap<>();

		try {
			FileReader fl = new FileReader(filename);
			BufferedReader bf = new BufferedReader(fl);
			String str = bf.readLine();
			System.out.println(str);
			while(str != null){
				String[] mySplit = str.split(" ");
				if(mySplit.length == 2)
					userList.put(mySplit[0], mySplit[1]);

				str = bf.readLine();
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userList.toString());
	}

	public void addNewUser(String username, String password)
	{
		userList.put(username, password);
		saveData();
	}

	public boolean isValidUser(String username, String password)
	{
		if(!userList.containsKey(username))
			return false;

		if(password.compareTo(userList.get(username)) == 0)
			return true;

		return false;
	}
	private void saveData(){

		try{
		File file = new File(filename);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		Set<String> keySet = userList.keySet();

		for(String user : keySet){
			String temp = user + " " + userList.get(user) + "\n";
			bw.write(temp);
		}

		bw.close();
		} catch( IOException e){
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}
