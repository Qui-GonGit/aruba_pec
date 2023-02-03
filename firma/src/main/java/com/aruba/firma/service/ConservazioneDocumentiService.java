package com.aruba.firma.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.aruba.firma.service.exception.ServiceException;
import com.aruba.firma.service.pojo.ResponseConservazioneDocumenti;

@Service
public class ConservazioneDocumentiService {
	@Value("${conservazione.documenti.server.url}")
	String baseUrl;
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(ConservazioneDocumentiService.class);
	public ResponseConservazioneDocumenti sendToRepository(String request) throws ServiceException {
		File file = new File(request);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", file);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<ResponseConservazioneDocumenti> response = restTemplate.postForEntity(
				baseUrl + "api/cons-doc/post-documents-signed", requestEntity, ResponseConservazioneDocumenti.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			logger.error("impossibile trasferire il file!");
			throw new ServiceException("impossibile trasferire il file");
		}
	}

}
