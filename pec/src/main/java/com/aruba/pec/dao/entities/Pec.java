package com.aruba.pec.dao.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Table(name = "PEC")
@Entity
public class Pec {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="NAME")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
	private User user;
	
	@OneToMany(mappedBy="pec")
	private Set<Message> messages;

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
