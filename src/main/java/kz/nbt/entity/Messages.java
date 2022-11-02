package kz.nbt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Lob
	@Column(length=512)
	private String message;
	private Long chatid;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date date;
	private String channel;
	private String agentid;
	
	
	
	
	
	
	public Messages() {
		
		
	}
	
	
	
	public Messages(String message, Long chatid, Date date, String channel,String agentid) {
		this.message = message;
		this.chatid = chatid;
		this.date = date;
		this.channel = channel;
		this.agentid = agentid;
	}
	
	
	
	
	
	
	public String getAgentid() {
		return agentid;
	}



	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getChatid() {
		return chatid;
	}
	public void setChatid(Long chatid) {
		this.chatid = chatid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

	
	
	

}
