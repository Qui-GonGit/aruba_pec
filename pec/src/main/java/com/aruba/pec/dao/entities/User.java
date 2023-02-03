package com.aruba.pec.dao.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "USERS")
@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "IDARUBA")
	private String idAruba;
	@JsonIgnore()
	@Column(name = "PRIVATE_KEY")
	private String privateKey;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@OneToMany(mappedBy="user")
	private Set<Pec> pecs;

	public Set<Pec> getPecs() {
		return pecs;
	}

	public void setPecs(Set<Pec> pecs) {
		this.pecs = pecs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdAruba() {
		return idAruba;
	}

	public void setIdAruba(String idAruba) {
		this.idAruba = idAruba;
	}
		

}
