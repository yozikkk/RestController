package kz.nbt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import kz.nbt.worker.ChatBrodcaster;
import org.json.JSONObject;



public class Test {
	
	public static void main(String[] args) throws IOException {



		ChatBrodcaster chatBrodcaster = new ChatBrodcaster();
		chatBrodcaster.start();

	}
	


}
