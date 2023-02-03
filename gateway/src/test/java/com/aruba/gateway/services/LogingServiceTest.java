package com.aruba.gateway.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.aruba.gateway.model.LoginRequest;
import com.aruba.gateway.model.LoginResponse;
import com.aruba.gateway.model.LogoutResponse;
import com.aruba.gateway.model.TokenRequest;

class LogingServiceTest {
	@Mock
	RestTemplate restTemplate;
	@InjectMocks
	LogingService loginService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(loginService, "issueUrl", "value");
		ReflectionTestUtils.setField(loginService, "clientId", "value");
		ReflectionTestUtils.setField(loginService, "clientSecret", "value");
		ReflectionTestUtils.setField(loginService, "grantType", "value");
	}

	@Test
	void loginServiceUnitTest() {
		LoginResponse loginRes = new LoginResponse();
		loginRes.setAccessToken("test01");
		loginRes.setExpiryIn("test02");
		loginRes.setRefreshToken("test03");
		loginRes.setTokenType("test04");
		LoginRequest loginReq = new LoginRequest();
		loginReq.setPassword("test01");
		loginReq.setUsername("test02");
		when(restTemplate.postForEntity(
				Mockito.eq("http://localhost:8181/realms/Aruba_Realm/protocol/openid-connect/token"),
				Mockito.any(HttpEntity.class), Mockito.eq(LoginResponse.class)))
						.thenReturn(new ResponseEntity<>(loginRes, HttpStatus.OK));
		assertEquals(loginRes.getAccessToken(), loginService.login(loginReq).getBody().getAccessToken());
		assertEquals(loginRes.getExpiryIn(), loginService.login(loginReq).getBody().getExpiryIn());
		assertEquals(loginRes.getRefreshToken(), loginService.login(loginReq).getBody().getRefreshToken());
	}

	@Test
	void logoutServiceUnitTest() {
		LogoutResponse logoutResponse = new LogoutResponse();
		TokenRequest loginReq = new TokenRequest();
		logoutResponse.setMessage("Log out Successfull!!");
		loginReq.setToken(
				"eyJleHAiOjE2NzUyMzc0OTQsImlhdCI6MTY3NTIzNzE5NCwianRpIjoiYmI5NTk4ZjktNzI4Ni00NGU2LWJlODAtYzQ2MzVmNzZkZjZkIiwiaXNzIj");
		when(restTemplate.postForEntity(
				Mockito.eq("http://localhost:8181/realms/Aruba_Realm/protocol/openid-connect/logout"),
				Mockito.any(HttpEntity.class), Mockito.eq(LogoutResponse.class)))
						.thenReturn(new ResponseEntity<>(logoutResponse, HttpStatus.OK));
		assertEquals(logoutResponse.getMessage(), loginService.logout(loginReq).getBody().getMessage());
	}

}
