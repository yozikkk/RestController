package kz.nbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kz.nbt.entity.Agents;
import kz.nbt.repo.AgentsRepo;

@Controller
public class AgentsController {
	
	
	@Autowired
	AgentsRepo agentsRepo;
	
	
	
/**
	@GetMapping("/all")
	public @ResponseBody Iterable<Messages> listMessages() {
		return messagesRepo.findAll();
		
	}
	**/
	
	
	
	@PostMapping(path="/getAgentState",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Agents getAgentState(@RequestBody Agents agent){	
		return agentsRepo.findByAgentid(agent.getAgentid());
	}
	

	
	@PostMapping(path="/getChatState",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Agents getChatState(@RequestBody Agents agent){	
		return agentsRepo.findAgentIdByChatId(agent.getChatId());
	}


	@PostMapping(path="/findAgent",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Agents findAgent(){

		Iterable<Agents> agents = agentsRepo.findAll();
		Agents agentsObj = new Agents();

		for(Agents agent : agents){
			if(agent.getChatId() == null){
				System.out.println("Null appeard:"+agent.getAgentid());
				System.out.println("Ready:"+agent.isReady());
				agentsObj.setAgentid(agent.getAgentid());
				agentsObj.setReady(agent.isReady());
				break;
			}
		}
		return agentsObj;
	}



	@PostMapping(path="/addAgent",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Agents> addAgent(@RequestBody Agents agent){
	   agentsRepo.save(agent);
	   return new ResponseEntity<>(agent,HttpStatus.CREATED);
	}
	
	
	
	@PostMapping(path="/updateAgentState",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	@Transactional
	public ResponseEntity<Agents> updateState(@RequestBody Agents agent){
		agentsRepo.updateState(agent.getAgentid(), agent.isReady());
		return new ResponseEntity<>(agent,HttpStatus.CREATED);
	}
	
	
	@PostMapping(path="/assignChatToAgent",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	@Transactional
	public ResponseEntity<Agents> assign(@RequestBody Agents agent){
		agentsRepo.updateChatid(agent.getAgentid(), agent.getChatId(),agent.getChannel());
		return new ResponseEntity<>(agent,HttpStatus.CREATED);
	}
	
	
	
	
	@PostMapping(path="/unAssignChatToAgent",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE) 
	@Transactional
	public ResponseEntity<Agents> unassign(@RequestBody Agents agent){
		agentsRepo.updateChatid(agent.getAgentid(), "","");
		return new ResponseEntity<>(agent,HttpStatus.CREATED);
	}
	

	
	@DeleteMapping(path="/removeAgent",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Agents> remove(@RequestBody Agents agent){
		agentsRepo.removeByagentid(agent.getAgentid());
		return new ResponseEntity<>(agent,HttpStatus.CREATED);
	}
	
	
	
	

}
