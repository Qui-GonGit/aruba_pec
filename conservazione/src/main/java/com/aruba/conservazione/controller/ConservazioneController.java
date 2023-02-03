package com.aruba.conservazione.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aruba.conservazione.controller.dto.ResponseConservazioneDocumenti;
import com.aruba.conservazione.services.FileService;
import com.aruba.conservazione.services.exception.FileServiceException;

@Controller
@RequestMapping("/api/cons-doc")
public class ConservazioneController {
	@Autowired
	private FileService fileService;
	@GetMapping("/get-documents-signed")
	public @ResponseBody List<String> getListDocumentsSigned() {
		fileService.getDocumentsByUser();
		return fileService.getDocumentsByUser();
	}

	@PostMapping(value = "/post-documents-signed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseConservazioneDocumenti> sendDocuments(
			@RequestParam("file") MultipartFile file) throws FileServiceException {
		fileService.save(file);
		ResponseConservazioneDocumenti response = new ResponseConservazioneDocumenti();
		response.setResponse("File uploaded successfull!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Profile("dev")
	@GetMapping("/exception/{exeptionid}")
	public void getException(
			@PathVariable("exeptionid") String pException) throws FileServiceException {
		if ("fileException".equals(pException)) {
			throw new FileServiceException("Impossibile salvare il file");
		}
	}
	
}
