package com.aruba.pec.controller.dto;

import javax.validation.constraints.NotNull;

public class WebRequestPec {
	@NotNull(message="the field idUtente can't be null!")
	String idUtente;
	
	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	
}
