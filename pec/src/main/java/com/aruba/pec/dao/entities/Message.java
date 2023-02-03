package com.aruba.pec.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "MESSAGES")
@Entity
public class Message {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="SENDER")
	private String sender;
	@Column(name="SUBJECT")
	private String subject;
	@Column(name="TEXT")
	private String text;
	@Column(name="PRIORITY")
	private String priority;
	@Column(name="ATTACHMENT")
	private String attachment;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@ManyToOne
    @JoinColumn(name="PEC_ID", nullable=false)
	private Pec pec;
	
}
