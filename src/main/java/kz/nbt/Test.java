package kz.nbt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;



public class Test {
	
	public static void main(String[] args) throws IOException {
	
		
		
    
		//doGet("getMessage");
		//System.out.println(doGet("getMessage").split(";").length);
		
		long chatid= 495241981;
		
		JSONObject jsonObject = new JSONObject(doGet("getMessage",chatid));
		
		if(jsonObject.isNull("message")) {
			
			System.out.println("Ничего не делаем");
		}
		else {
			
			String fullMesage = jsonObject.getString("message");
			
			for(String message :fullMesage .split(";") ) {
				System.out.println(message);
				
			}
		}
	

		

	}
	
	
	public static String doGet(String action,Long chatid) throws IOException{
		 
		  URL url;
	      StringBuilder result = new StringBuilder();
	      
	      if(chatid == null) {
	    	  
	    	  url = new URL("http://localhost:8080/"+action);  
	      }
	      else {
	    	  
	    	  url = new URL("http://localhost:8080/"+action+"?chatid="+chatid);
	    	  
	      }
	      
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      try (BufferedReader reader = new BufferedReader(
	                  new InputStreamReader(conn.getInputStream()))) {
	          for (String line; (line = reader.readLine()) != null; ) {
	              result.append(line);
	              System.out.println(line+ "\n");
	          }
	      }
		
	  return result.toString();
	}
	

}
