package kz.nbt;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import kz.nbt.dispatcher.MessageDispatcher;
import kz.nbt.websocket.WebSocketClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class Bot extends TelegramLongPollingBot {


	

    final private String BOT_TOKEN = "1689090919:AAF5OY0AHKbwDiHXfr6PUcmBPVXvIZnTSHw";
    final private String BOT_NAME = "Ntlab_bot";
    Storage storage;   
    
   public Bot()
    {
        storage = new Storage();
    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

   
    
    
    public void onUpdateReceived(Update update) {
    	
  
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {


                MessageDispatcher dispatcher = new MessageDispatcher();
                dispatcher.sendMsgToAgent(update.getMessage().getText(),update.getMessage().getChatId().toString(),"telegram");

                Message inMess = update.getMessage();
              
                String chatId = inMess.getChatId().toString();
              
                String response = parseMessage(inMess.getText(),chatId);
             
                SendMessage outMess = new SendMessage();

        
                outMess.setChatId(chatId);
                outMess.setText(response);
                
                
               
                execute(outMess);
            }
        } catch (TelegramApiException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String parseMessage(String textMsg,String chatid) throws InterruptedException, ExecutionException, JSONException, IOException {
    	
    	
    	String response;
    	CallSelfRest selfRest = new CallSelfRest();
    	JSONObject jsonObject = new JSONObject(selfRest.getQueueLen());
    	System.out.println("Chat id is empty ?:"+selfRest.getChatState(chatid).isEmpty());
    	if(selfRest.getChatState(chatid).isEmpty()) {
    		
    		response = "Подождите пожалуйста....";
    		/*
    		int queue = jsonObject.getInt("len");
        	if(queue <= 1 ) {
        		
        		response = "Ваш запрос принят сотрудником. В ближайшее время вам ответят.";
        		
        	}
        	else {
        		
        		 response = "Клиентов в очереди :"+queue+" Подождите пожалуйста.....";
        	}
        	*/
    		
    	}
    	else {
    		
    		response = "Aгент сейчас вам ответит";
    	}
    	
    	
    	
        return response;
    }
    
    public void sendMessage(String message,String chatid) throws TelegramApiException {
    	  SendMessage outMess = new SendMessage();
    	  outMess.setChatId(chatid);
          outMess.setText(message);
          execute(outMess);
    }
}
