package com.aruba.firma.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aruba.firma.service.SignService;
import com.aruba.firma.service.dto.ServiceFirmaRequest;
import com.aruba.firma.service.exception.ServiceException;

@Controller()
@RequestMapping("/api/firma")
public class FirmaController {
	@Autowired
	private SignService signService;
	private static final Logger logger = LoggerFactory.getLogger(FirmaController.class);
	@PostMapping(value = "/execute")
	public @ResponseBody ResponseEntity<String> firmaDocumento(@RequestPart("id_utente") String idUtente,
			@RequestPart("password") String password,
			@RequestPart("file") MultipartFile request) throws IOException, ServiceException {
		logger.debug("FirmaController: execute Firma");
		ServiceFirmaRequest serviceRequest = new ServiceFirmaRequest(idUtente,request,password);
		return new ResponseEntity<>(signService.execute(serviceRequest).getResponse(), HttpStatus.OK);
	}
}
