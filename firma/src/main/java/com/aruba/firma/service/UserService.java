package com.aruba.firma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aruba.firma.service.pojo.User;

@Service
public class UserService implements IUserService {
	@Autowired
	RestTemplate restTemplate;

	@Override
	public User getUtenteById(String id) {
		HttpEntity<String> httpEntity = new HttpEntity<>(id);
		return restTemplate.postForEntity("http://localhost:9080/api/pec/get-user-by-id-aruba", httpEntity, User.class).getBody();
	}

}
