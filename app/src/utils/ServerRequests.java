package utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learngerman.MainActivity;

public class ServerRequests {
	TextView tw;
	Handler h = new Handler();

	private class RequestMaker implements Runnable{

		String data;
		List<NameValuePair> params;
		MainActivity callBack;

		RequestMaker(){
			params = new ArrayList<>();
		}

		@Override
		public void run() {
			try {
		        HttpClient client = new DefaultHttpClient();
		        String getURL = "http://192.168.1.100:9000";
		        HttpPost post = new HttpPost(getURL);

		        UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		        post.setEntity(ent);

		        HttpResponse rasp_get= client.execute(post);
		        HttpEntity rasp_entity = rasp_get.getEntity();

		        if (rasp_entity!= null) {
		                    //do something with the response
		        			data = EntityUtils.toString(rasp_entity);
		                    Log.i("GET RESPONSE",data);

		                    if(data.contains("Success"))
		                    	callBack.startNextActivity();
		                    else {
		                    	h.post(new Runnable(){

									@Override
									public void run() {
										Toast toast = Toast.makeText(callBack,
												"Username or password incorrect",
												Toast.LENGTH_SHORT);
										toast.show();
									}

		                    	});
		                    	callBack.loginFailed("");
		                    }

		                }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		}

	}

	public void makeRequest(String username, String password, MainActivity callback){
	RequestMaker rm = new RequestMaker();
	rm.params.add(new BasicNameValuePair("user", username));
	rm.params.add(new BasicNameValuePair("pass", password));
	rm.params.add(new BasicNameValuePair("type", "authentication"));
	rm.callBack = callback;

	Thread tr = new Thread(rm);
	tr.start();
	}
}
