package kz.nbt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kz.nbt.entity.Messages;


@Repository
public interface MessagesRepo extends CrudRepository<Messages,Integer>{
	
	
	List<Messages>  findFirstByOrderByMessage();
	List<Messages>  findAllBychatid(Long chatid);
	Integer  removeBychatid(Long chatid);
	@Query("select distinct c.chatid from Messages c")
	List<Long> findDistinctBychatid();

	@Query("select c.channel, count(distinct(c.chatid)) as len from Messages c group by c.channel")
	List<String[]> calculateQueue();


}
