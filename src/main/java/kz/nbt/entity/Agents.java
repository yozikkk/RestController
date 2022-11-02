package kz.nbt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Agents {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Long agentid;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date loginTime;
	private boolean isReady;
	private String chatId;
	
	public Agents() {
		
	}
	
	
	public Agents(Long agentid, Date loginTime, boolean isReady, String chatId) {
		this.agentid = agentid;
		this.loginTime = loginTime;
		this.isReady = isReady;
		this.chatId = chatId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getAgentid() {
		return agentid;
	}
	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	
	

}
