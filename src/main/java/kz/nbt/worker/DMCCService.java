package kz.nbt.worker;

import kz.nbt.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class DMCCService {

    public String putToQueue(String chatid){

        StringBuilder response = new StringBuilder();
        HttpClient conn = new HttpClient();
        HttpURLConnection con = conn.doHttpRequest("http://localhost:8282/call?chatid="+chatid, "GET");

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


    }



