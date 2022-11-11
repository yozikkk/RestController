package kz.nbt.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class WebSocketClient
{

    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    public void connectAndSend(String message,String room) throws Exception
    {
        WebSocket websocket = new WebSocketFactory()
                .createSocket("ws://localhost:8181/chat/"+room)
                .connect();
        websocket.sendText(message);
        websocket.disconnect();
    }
}