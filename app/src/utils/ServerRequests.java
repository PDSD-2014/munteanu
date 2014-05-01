package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.widget.TextView;

public class ServerRequests {
	TextView tw;
	private class RequestMaker implements Runnable{

		String data;
		@Override
		public void run() {
			try {
		        HttpClient client = new DefaultHttpClient();
		        String getURL = "http://192.168.1.100:9000";
		        HttpGet get = new HttpGet(getURL);
		        HttpResponse rasp_get= client.execute(get);
		        HttpEntity rasp_entity = rasp_get.getEntity();

		        if (rasp_entity!= null) {
		                    //do something with the response
		        			data = EntityUtils.toString(rasp_entity);
		                    Log.i("GET RESPONSE",data);
//		            		tw = (TextView)findViewById(R.id.textView1);
//		            		tw.post(new Runnable() {
//
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//				                    tw.setText(data);
//								}
//
//		            		});
		                }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		}

	}

	public void makeRequest(){
	RequestMaker rm = new RequestMaker();
	Thread tr = new Thread(rm);
	tr.start();
	}
}
