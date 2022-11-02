package kz.nbt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.nbt.entity.Agents;

public interface AgentsRepo extends CrudRepository<Agents, Long>{
	
	
	
	List<Agents> removeByagentid(Long agentid);
	Agents  findByAgentid(Long agentid);
	Agents  findAgentIdByChatId(String chatId);




	
	@Modifying(clearAutomatically = true)
	@Query("update Agents a set a.isReady =:isReady where a.agentid =:agentid")
	int updateState(@Param("agentid") Long agentid,@Param("isReady") boolean isReady);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("update Agents a set a.chatId =:chatId where a.agentid =:agentid")
	int updateChatid(@Param("agentid") Long agentid,@Param("chatId") String chatId);


}
