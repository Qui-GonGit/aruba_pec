package com.aruba.firma.service.pojo;

public class User {

	private String firstName;
	private String lastName;
	private String privateKey;
	private String docRelPath;
	private String passwordKey;
	
	public String getPasswordKey() {
		return passwordKey;
	}
	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}
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
	public String getDocRelPath() {
		return docRelPath;
	}
	public void setDocRelPath(String docRelPath) {
		this.docRelPath = docRelPath;
	}

	
	
}
