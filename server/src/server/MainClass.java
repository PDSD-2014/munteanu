package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.impl.DefaultBHttpServerConnection;
import org.apache.http.util.EntityUtils;

class SingleThreadedServer implements Runnable {

//	private final static String TAG = "ServerThread";

	// For a TCP connection (i. a server) we need a ServerSocket
	private ServerSocket in;
	HandleUsers hUsers;
	ContentManager cManager;
	static String rsp =
	    "HTTP/1.1 200 OK\r\n" +
	    "Server: WebServer\r\n" +
	    "Content-Type: text/html\r\n" +
	    "Content-Length: 7\r\n" +
	    "Connection: close\r\n" +
	    "\r\n" +
	    "Success";

	// In the constructor we try creating the server socket, on port 9000.
	public SingleThreadedServer() {

		hUsers = new HandleUsers();
		cManager = new ContentManager();

		try {
			// Beware: Only privileged users can use ports below 1023.
			in = new ServerSocket(9000);
		} catch (IOException e) {
			System.out.println("Cannot create socket. Due to: " + e.getMessage());
		}
	}

	@Override
	public void run() {

		// Always try serving incoming requests.
		while(true) {
			//For every request we are allocated a new socket.
			Socket incomingRequest = null;

			try {
				// Wait in blocked state for a request.
				incomingRequest = in.accept();
			} catch (IOException e) {
				System.out.println("Error when accepting connection.");
			}

			// When accept() returns a new request was received.
			// We use the incomingRequest socket for I/O
			System.out.println("New request from: " + incomingRequest.getInetAddress());
			DefaultBHttpServerConnection conn;
			HashMap<String, String> params = null;
			try{
			conn = new DefaultBHttpServerConnection(100);
			conn.bind(incomingRequest);
			HttpRequest request = conn.receiveRequestHeader();
			conn.receiveRequestEntity((HttpEntityEnclosingRequest)request);
			HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
			params = ServerUtils.parseResponse(EntityUtils.toString(entity));
			System.out.println(params.toString());
//			conn.close();
			} catch(Exception e){

			}
			// Get its associated OutputStream for writing.
			OutputStream responseStream = null;
			try {
				responseStream = incomingRequest.getOutputStream();
			} catch (IOException e) {
				System.out.println("Cannot get outputstream.");
			}

			// Wrap it with a PrinStream for convenience.
			PrintStream writer = new PrintStream(responseStream);
			writer.print(httpFormat(ParseCommand(params)));

			// Make sure data is sent and allocated resources are cleared.
			try {
				incomingRequest.close();
			} catch (IOException e) {
				System.out.println( "Error finishing request.");
			}

			System.out.println("Sent greeting.");
			// Continue the looping.
		}

	}

	public String httpFormat(String data){
		String rsp =
			    "HTTP/1.1 200 OK\r\n" +
			    "Server: WebServer\r\n" +
			    "Content-Type: text/html\r\n" +
			    "Content-Length:" + data.length() +"\r\n" +
			    "\r\n" +
			    data;
		return rsp;
	}

	public String ParseCommand(HashMap<String, String> params){
		String resp = "Fail";

		if(params.get("type").compareTo("authentication") == 0){
			if(hUsers.isValidUser(params.get("user"), params.get("pass")))
				return "Success";
			else
				return "Fail";
		}

		if(params.get("type").compareTo("content") == 0){
			return cManager.getDataInJSon();
		}


		return resp;
	}

}
public class MainClass {

//	private final static String TAG = "MainActivity";


	public static void main(String[] args){
		Thread greetingServer = new Thread(new SingleThreadedServer());
		greetingServer.start();
	}
}
