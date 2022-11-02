package kz.nbt;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
	
	
	public HttpURLConnection doHttpRequest(String urlInput,String method) {
		
		
		 HttpURLConnection con = null;
		try {
		      URL url = new URL (urlInput);
		      con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod(method);
		      con.setRequestProperty("Content-Type", "application/json; utf-8");
		      con.setRequestProperty("Accept", "application/json");
		      con.setDoOutput(true);
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	
	}
	

}
