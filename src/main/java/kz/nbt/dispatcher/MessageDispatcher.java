package kz.nbt.dispatcher;

import kz.nbt.CallSelfRest;
import kz.nbt.websocket.WebSocketClient;
import kz.nbt.worker.DMCCService;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MessageDispatcher {

    public Long agentid;

    public void sendMsgToAgent(String text,String chatid,String channel,String messageid){

        String textJson =  "{ "
                + "\"from\":\""+chatid+"\","
                + "\"content\":\""+text+"\"}";

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
                    if(jsonObject.isNull("agentid")||!jsonObject.getBoolean("ready")){
                        System.out.println("Свободных агентов нет");
                        System.out.println("Добавляем чат в очередь");
                        DMCCService service = new DMCCService();
                        JSONObject dmccPhone = new JSONObject(service.putToQueue(chatid));
                        selfRest.addMessage(text,chatid,channel,messageid,dmccPhone.getString(chatid));

                    }
                    else{
                        System.out.println("Найден свободный агент :"+jsonObject.getLong("agentid"));
                        DMCCService service = new DMCCService();
                        service.putToQueue(chatid);
                        WebSocketClient webSocketClient = new WebSocketClient();
                        Long agentid = jsonObject.getLong("agentid");
                        this.agentid = agentid;
                        webSocketClient.connectAndSend(textJson,
                                agentid.toString());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Свободных агентов нет");
                    System.out.println("Добавляем чат в очередь");
                    DMCCService service = new DMCCService();
                    JSONObject dmccPhone = new JSONObject(service.putToQueue(chatid));
                    selfRest.addMessage(text,chatid,channel,messageid,dmccPhone.getString(chatid));
                }
            }
            else{
                System.out.println("Чат уже назначен агенту ");
                JSONObject jsonObjectChatState = new JSONObject( selfRest.getChatState(chatid));
                Long agentid =  jsonObjectChatState.getLong("agentid");
                this.agentid = agentid;
                WebSocketClient webSocketClient = new WebSocketClient();
                webSocketClient.connectAndSend(textJson,
                        agentid.toString());
            }
        }
        catch (Exception e){

        }

    }


    public void sendMessageToWorld(String text, String chatid ,String channel) throws TelegramApiException, IOException {
            CallSelfRest rest = new CallSelfRest();
            String jsonInputString =  "{"
                    + "\"chatid\":\""+chatid+"\","
                    + "\"channel\":\""+channel+"\","
                    + "\"message\":\""+text+"\"}";
            rest.doSslPost(jsonInputString,"send_message","https://86.nbt.kz/");

        }


    }



