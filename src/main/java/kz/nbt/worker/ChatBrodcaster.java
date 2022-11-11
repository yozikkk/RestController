package kz.nbt.worker;

import kz.nbt.CallSelfRest;
import kz.nbt.websocket.WebSocketClient;

public class ChatBrodcaster extends Thread {



    public void run(){

        try{

            while (isRunning){

                Thread.sleep(3000);

                CallSelfRest rest = new CallSelfRest();
                WebSocketClient socketClient = new WebSocketClient();
                socketClient.connectAndSend(rest.geDetailedQueue(),"all");

            }


        }
        catch (InterruptedException e){
            throw new RuntimeException("Broadcaster has stopped");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static boolean isRunning = true;


}
