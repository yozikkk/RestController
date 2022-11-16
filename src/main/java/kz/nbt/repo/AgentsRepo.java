package kz.nbt.repo;

import java.util.List;

import kz.nbt.entity.Messages;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.nbt.entity.Agents;

import javax.persistence.LockModeType;

public interface AgentsRepo extends CrudRepository<Agents, Long> {


	List<Agents> removeByagentid(Long agentid);

	Agents findByAgentid(Long agentid);

	Agents findAgentIdByChatId(String chatId);


	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Agents findFirstBychatIdIsNullOrderByLastUpdateAsc();


	@Modifying(clearAutomatically = true)
	@Query("update Agents a set a.isReady =:isReady where a.agentid =:agentid")
	int updateState(@Param("agentid") Long agentid, @Param("isReady") boolean isReady);


	@Modifying(clearAutomatically = true)
	@Query("update Agents a set a.chatId =:chatId, a.channel =:channel where a.agentid =:agentid")
	int updateChatid(@Param("agentid") Long agentid, @Param("chatId") String chatId, @Param("channel") String channel);

}