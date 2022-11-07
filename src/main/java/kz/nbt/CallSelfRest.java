package kz.nbt;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallSelfRest {

	public String addMessage(String message, String chatid, String channel) throws IOException {


		StringBuilder response = new StringBuilder();
		HttpClient conn = new HttpClient();
		HttpURLConnection con = conn.doHttpRequest("http://localhost:8080/addMessage", "POST");

		String pattern = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		String jsonInputString = "{ "
				+ "\"message\":\"" + message + "\","
				+ "\"chatid\":\"" + chatid + "\","
				+ "\"date\":\"" + date + "\","
				+ "\"channel\":\"" + channel + "\"}";
		System.out.println(jsonInputString);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}

		}

		con.disconnect();
		return response.toString();


	}


	public String getChatState(String chatid) throws IOException {


		StringBuilder response = new StringBuilder();
		HttpClient conn = new HttpClient();
		HttpURLConnection con = conn.doHttpRequest("http://localhost:8080/getChatState", "POST");


		String jsonInputString = "{"
				+ "\"chatId\":\"" + chatid + "\"}";
		System.out.println(jsonInputString);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}

		}

		con.disconnect();
		return response.toString();


	}


	public String getQueueLen() {

		StringBuilder response = new StringBuilder();
		HttpClient conn = new HttpClient();
		HttpURLConnection con = conn.doHttpRequest("http://localhost:8080/getQueue", "GET");

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		con.disconnect();
		return response.toString();

	}

	public String doPost(String jsonInputString,String action,String url) throws IOException {

		StringBuilder response = new StringBuilder();
		URL createurl = new URL(url + action);
		HttpURLConnection con = (HttpURLConnection) createurl.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		System.out.println(jsonInputString);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		con.disconnect();
		return response.toString();


	}

	public String doSslPost(String jsonInputString,String action,String url) throws IOException {

		StringBuilder response = new StringBuilder();
		URL createurl = new URL(url + action);
		HttpsURLConnection con = (HttpsURLConnection) createurl.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		System.out.println(jsonInputString);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		con.disconnect();
		return response.toString();


	}


	public String doPostSimple(String action) throws IOException {

		StringBuilder response = new StringBuilder();
		URL url = new URL("http://localhost:8080/" + action);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {

			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		con.disconnect();
		return response.toString();


	}
}