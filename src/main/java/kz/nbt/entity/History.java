package kz.nbt.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Entity
public class History {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Lob
    @Column(length=512)
    private String message;
    private String chatid;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private String channel;
    private String agentid;
    private String whoSaid;
    private String state;

    private String messageid;

/*
    public History(String message, Long chatid, Date date, String channel, String agentid, String whoSaid, String state) {
        this.message = message;
        this.chatid = chatid;
        this.date = date;
        this.channel = channel;
        this.agentid = agentid;
        this.whoSaid = whoSaid;
        this.state = state;
    }

 */

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public History(){

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


    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
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

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getWhoSaid() {
        return whoSaid;
    }

    public void setWhoSaid(String whoSaid) {
        this.whoSaid = whoSaid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
