package com.aruba.conservazione.controller.dto;

import org.springframework.web.multipart.MultipartFile;

public class WebRequestSendDocument {
	private MultipartFile document;
	private User idUtente;
	public MultipartFile getDocument() {
		return document;
	}
	public void setDocument(MultipartFile document) {
		this.document = document;
	}
	public User getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(User idUtente) {
		this.idUtente = idUtente;
	}
	
}
