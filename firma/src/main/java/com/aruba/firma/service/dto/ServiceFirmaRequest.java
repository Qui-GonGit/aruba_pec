package com.aruba.firma.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class ServiceFirmaRequest {
	private String idUtente;
	private MultipartFile request;
	private String password;

	public ServiceFirmaRequest(String idUtente, MultipartFile request, String password) {
		super();
		this.idUtente = idUtente;
		this.request = request;
		this.password = password;
	}



	public String getIdUtente() {
		return idUtente;
	}



	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}



	public MultipartFile getRequest() {
		return request;
	}

	public void setRequest(MultipartFile request) {
		this.request = request;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
