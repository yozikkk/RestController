package kz.nbt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import kz.nbt.dispatcher.MessageDispatcher;
import kz.nbt.entity.Agents;
import kz.nbt.entity.Messages;
import kz.nbt.entity.Queue;
import kz.nbt.repo.AgentsRepo;
import kz.nbt.repo.MessagesRepo;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
public class MessagesController {
	@Autowired 
	public MessagesRepo messagesRepo;
	@Autowired 
	public AgentsRepo agentsRepo;

	
	@PostMapping(path="/addMessage",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Messages> addMessage(@RequestBody Messages newMessage) throws Exception{
		Messages messages = null;
		
	
			System.out.println("chat id ------>>>>>>"+newMessage.getChatid().toString());
			Agents chatid = agentsRepo.findAgentIdByChatId(newMessage.getChatid().toString());
			//System.out.println("agent id ------>>>>>>"+chatid.getAgentid());
			if(chatid !=null) {
				messages = new Messages();
				messages.setAgentid(chatid.getAgentid().toString());
				messages.setChannel(newMessage.getChannel());
				messages.setDate(newMessage.getDate());
				messages.setMessage(newMessage.getMessage());
				messages.setChatid(newMessage.getChatid());
				messagesRepo.save(messages);
				
			}
			else {
				messages = messagesRepo.save(newMessage);
				
			}

		
		return new ResponseEntity<>(messages,HttpStatus.CREATED);
		}

		
		
		
		

	
	@PostMapping(path="/sendMessage",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Messages> sendMessage(@RequestBody Messages message) throws Exception{


		Agents chatid = agentsRepo.findByAgentid(Long.parseLong(message.getAgentid()));
		if(chatid.getChatId() !=null){

			MessageDispatcher dispatcher = new MessageDispatcher();
			dispatcher.sendMessageToWorld(message.getMessage(), chatid.getChatId(),chatid.getChannel());
			//Bot bot = new Bot();
			//bot.sendMessage(message.getMessage(), chatid.getChatId());
			return new ResponseEntity<>(message,HttpStatus.CREATED);
		}
		else{

			return new ResponseEntity<>(message,HttpStatus.CREATED);
		}

	}

	@PostMapping(path="/recieveMessage",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Messages> recieveMessage(@RequestBody Messages message) throws Exception{
		MessageDispatcher dispatcher = new MessageDispatcher();
		dispatcher.sendMsgToAgent(message.getMessage(),message.getChatid().toString(),message.getChannel());
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Messages> listMessages() {
		return messagesRepo.findAll();
		
	}
	
	
	@GetMapping("/getQueue")
	public @ResponseBody ResponseEntity<kz.nbt.entity.Queue>  getQueueLength(){
		List<Long> list = messagesRepo.findDistinctBychatid();
		kz.nbt.entity.Queue queue = new kz.nbt.entity.Queue();
		queue.setLen(list.size());
		//  queue;
		return new ResponseEntity<>(queue,HttpStatus.CREATED);
	}


	@GetMapping(path= "/getDetailedQueue",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<kz.nbt.entity.Queue> getDetailedQueueLength() throws JsonProcessingException {
		//Queue queue = messagesRepo.calculateQueue();
		List<String[]> strings = messagesRepo.calculateQueue();

		Queue queue = new Queue();

		 for(String[] str : strings){


			 for (int i=0;str.length>i;i++){

				 if(str[i].equalsIgnoreCase("telegram")){

					 queue.setTelegram(str[1]);

				 }else if (str[i].equalsIgnoreCase("whatsapp")){

					 queue.setWhatsapp(str[1]);

				 } else if (str[i].equalsIgnoreCase("facebook")) {

					 queue.setFacebook(str[1]);
					 
				 } else if (str[i].equalsIgnoreCase("instagram")) {

					 queue.setInstagram(str[1]);
				 }


			 }

		 }


		return new ResponseEntity<>(queue,HttpStatus.CREATED);
	}





	@GetMapping(path="/getMessage",  produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public @ResponseBody Messages  getOldestMessage(@RequestParam(required=false) Long chatid) throws Exception{
	
		
		if(chatid !=null) {	
			
			System.out.println("есть активные чаты");
			List<Messages> iter = messagesRepo.findAllBychatid(chatid);
			messagesRepo.removeBychatid(chatid);
			Messages messages = new Messages();
			
			StringBuilder singleString = new StringBuilder();
			for(Messages items:iter) {	
				singleString.append(items.getMessage());
				singleString.append(";");
			}
			
			   messages.setMessage(singleString.toString());
			   messages.setChatid(chatid);
			   return messages;
				
			}

		else {
			List<Messages> id = messagesRepo.findFirstByOrderByMessage();
			Messages messages = new Messages();
			System.out.println("активных чатов нет");
			if (!id.isEmpty()) {
	
					
				List<Messages> iter = messagesRepo.findAllBychatid(id.get(0).getChatid());
				messagesRepo.removeBychatid(id.get(0).getChatid());
				StringBuilder singleString = new StringBuilder();
				for(Messages items:iter) {	
					singleString.append(items.getMessage());
					singleString.append(";");
				}
				
				   messages.setMessage(singleString.toString());
				   messages.setChatid(id.get(0).getChatid());
				   messages.setChannel(id.get(0).getChannel());
				   return messages;
					
				}
				else {
					   return messages;
					 
				}
			
		}
	
	}
		
	
		
	
	
	@GetMapping("/deleteMessage")
	public @ResponseBody Iterable<Messages>   deleteMessage(@RequestParam Integer id){	
		messagesRepo.deleteById(id);
		return messagesRepo.findAll();
	}
	

	
	
	
}
