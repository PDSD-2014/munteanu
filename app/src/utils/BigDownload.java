package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class BigDownload {
	private Context myContext;

	public BigDownload(Context context){
		myContext = context;
	}

    public void getDataFromServer(){
        // Server Request URL
//        String serverURL = "http://androidexample.com/media/webservice/getPage.php";
    	String serverURL = "http://192.168.1.100:9000";
        // Create Object and call AsyncTask execute Method
        new LongOperation().execute(serverURL);
    }

    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, Void, Void> {

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(myContext);

//        TextView uiUpdate = (TextView) findViewById(R.id.output);

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //UI Element
//            uiUpdate.setText("Output : ");
            Dialog.setMessage("Downloading source..");
            Dialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            try {

                // Call long running operations here (perform background computation)
                // NOTE: Don't call UI Element here.

                // Server url call by GET method
                HttpPost httpPost = new HttpPost(urls[0]);

                /** setting post data type=content **/
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("type", "content"));
		        UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		        httpPost.setEntity(ent);

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpPost, responseHandler);

            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }

            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();
            Log.d("BigDownload", Content);

//            if (Error != null) {
//
//                uiUpdate.setText("Output : "+Error);
//
//            } else {
//
//                uiUpdate.setText("Output : "+Content);
//
//             }
        }

    }


}