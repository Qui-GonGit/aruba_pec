package com.aruba.gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.aruba.gateway.model.LoginRequest;
import com.aruba.gateway.model.LoginResponse;
import com.aruba.gateway.model.LogoutResponse;
import com.aruba.gateway.model.TokenRequest;

@Service
public class LogingService {
	@Autowired
	RestTemplate restTemplate;
	@Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
	private String issueUrl;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
	private String clientSecret;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
	private String grantType;
	public ResponseEntity<LoginResponse> login(LoginRequest loginrequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("grant_type", grantType);
		map.add("username", loginrequest.getUsername());
		map.add("password", loginrequest.getPassword());
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
		return restTemplate.postForEntity("http://localhost:8181/realms/Aruba_Realm/protocol/openid-connect/token", httpEntity,
				LoginResponse.class);
	}
	public ResponseEntity<LogoutResponse> logout(TokenRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("refresh_token", request.getToken());
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
		ResponseEntity<LogoutResponse> res = restTemplate.postForEntity("http://localhost:8181/realms/Aruba_Realm/protocol/openid-connect/logout", httpEntity,
				LogoutResponse.class);
		LogoutResponse response = new LogoutResponse();
		if (res.getStatusCode().is2xxSuccessful()) {
			response.setMessage("Log out Successfull!!");
		} else {
			response.setMessage("Log out Fail!!");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
