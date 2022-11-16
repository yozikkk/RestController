package kz.nbt.dispatcher;

import kz.nbt.Bot;
import kz.nbt.CallSelfRest;
import kz.nbt.websocket.WebSocketClient;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDispatcher {

    public void sendMsgToAgent(String text, String chatid ,String channel){


        try{


            CallSelfRest selfRest = new CallSelfRest();
            if(selfRest.getChatState(chatid).isEmpty()){
                System.out.println("Чат не назначен агенту");

                String pattern = "yyyy-MM-dd'T'HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                String jsonInputString =  "{ "
                        + "\"lastUpdate\":\""+date+"\","
                        + "\"channel\":\""+channel+"\","
                        + "\"chatId\":\""+chatid+"\"}";
                try {

                    JSONObject jsonObject = new JSONObject(selfRest.doPost(jsonInputString,"findAgent","http://localhost:8080/"));
                    System.out.println("Найден свободный агент :"+jsonObject.getLong("agentid"));
                    WebSocketClient webSocketClient = new WebSocketClient();
                    Long agentid = jsonObject.getLong("agentid");
                    webSocketClient.connectAndSend(text,
                            agentid.toString());
                }
                catch (Exception e){

                    e.printStackTrace();
                    System.out.println("Свободных агентов нет");
                    System.out.println("Добавляем чат в очередь");
                    selfRest.addMessage(text, chatid,channel);

                }



                /*
                System.out.println("Чат не назначен агенту");
                JSONObject jsonObject = new JSONObject(selfRest.doPostSimple("findAgent"));

                System.out.println("agent is ready:"+jsonObject.getBoolean("ready"));

                if(jsonObject.isNull("agentid")||!jsonObject.getBoolean("ready")){

                    System.out.println("Свободных агентов нет");
                    System.out.println("Добавляем чат в очередь");
                    selfRest.addMessage(text, chatid,channel);
                }
                else{
                    System.out.println("Найден свободный агент :"+jsonObject.getLong("agentid"));
                    String jsonInputString =  "{ "
                            + "\"agentid\":\""+jsonObject.getLong("agentid")+"\","
                            + "\"channel\":\""+channel+"\","
                            + "\"chatId\":\""+chatid+"\"}";
                    selfRest.doPost(jsonInputString,"assignChatToAgent","http://localhost:8080/");
                    WebSocketClient webSocketClient = new WebSocketClient();

                    Long agentid = jsonObject.getLong("agentid");
                    webSocketClient.connectAndSend(text,
                            agentid.toString());

                }

                 */

            }
            else{
                System.out.println("Чат уже назначен агенту ");
                JSONObject jsonObjectChatState = new JSONObject( selfRest.getChatState(chatid));
                Long agentid =  jsonObjectChatState.getLong("agentid");
                WebSocketClient webSocketClient = new WebSocketClient();
                webSocketClient.connectAndSend(text,
                        agentid.toString());
            }



        }

        catch (Exception e){


        }



    }


    public void sendMessageToWorld(String text, String chatid ,String channel) throws TelegramApiException, IOException {

        if (channel.equalsIgnoreCase("telegram")){
            Bot bot = new Bot();
            bot.sendMessage(text, chatid);

        }
        else{

            CallSelfRest rest = new CallSelfRest();
            String jsonInputString =  "{"
                    + "\"chatid\":\""+chatid+"\","
                    + "\"message\":\""+text+"\"}";
            rest.doSslPost(jsonInputString,"send_message","https://86.nbt.kz/");

        }


    }


}
