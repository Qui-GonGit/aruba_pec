package com.aruba.pec.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebRequestPecFiltered extends WebRequestPec{	
	String sender = "";
	String subject = "";
	String text = "";
	String priority="";
	String pecName="";
	@JsonProperty(value="attachment")
	boolean hasAttach = false;
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
	public boolean isHasAttach() {
		return hasAttach;
	}
	public void setHasAttach(boolean hasAttach) {
		this.hasAttach = hasAttach;
	}
	public String getPecName() {
		return pecName;
	}
	public void setPecName(String pecName) {
		this.pecName = pecName;
	}


	
}
