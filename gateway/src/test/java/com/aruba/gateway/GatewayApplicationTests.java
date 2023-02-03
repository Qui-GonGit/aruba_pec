package com.aruba.gateway;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.aruba.gateway.config.SecurityConfig;
import com.aruba.gateway.controller.LoginController;
import com.aruba.gateway.model.LoginRequest;
import com.aruba.gateway.model.LoginResponse;
import com.aruba.gateway.model.LogoutResponse;
import com.aruba.gateway.model.TokenRequest;
import com.aruba.gateway.services.LogingService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(LoginController.class)
@AutoConfigureWebTestClient(timeout = "100000")
@TestPropertySource(properties = "eureka.client.enabled=false")
@Import(SecurityConfig.class)
class GatewayApplicationTests {
	@Autowired
	WebTestClient webTestClient;
	@MockBean
	LogingService loginService;

	@Test
	void givenUser_whenCallLoginAPI_ThenReturnToken() throws Exception {
		LoginRequest loginReq = new LoginRequest();
		LoginResponse loginRes = new LoginResponse();
		loginRes.setAccessToken("test01");
		loginRes.setExpiryIn("test02");
		loginRes.setRefreshToken("test03");
		loginRes.setTokenType("test04");
		loginReq.setPassword("test01");
		loginReq.setUsername("test02");
		Mono<LoginRequest> monoReq = Mono.just(loginReq);
		when(loginService.login(Mockito.any())).thenReturn(new ResponseEntity<>(loginRes, HttpStatus.OK));
		webTestClient.post().uri("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.body(monoReq, LoginRequest.class).exchange().expectStatus().is2xxSuccessful().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$.access_token").isEqualTo("test01").jsonPath("$.refresh_token").isEqualTo("test03");
	}
	@Test
	void givenUser_whenCallLogoutAPI_ThenStatu200() throws Exception {
		TokenRequest loginReq = new TokenRequest();
		LogoutResponse loginRes = new LogoutResponse();
		loginRes.setMessage("Log out Successfull!!");
		Mono<TokenRequest> monoReq = Mono.just(loginReq);
		when(loginService.logout(Mockito.any())).thenReturn(new ResponseEntity<>(loginRes, HttpStatus.OK));
		webTestClient.post().uri("/auth/logout").contentType(MediaType.APPLICATION_JSON)
		.body(monoReq, LoginRequest.class).exchange().expectStatus().is2xxSuccessful().expectHeader()
		.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$.message").isEqualTo("Log out Successfull!!");
	}
	@Test
	void givenUser_whenCallLogoutAPI_ThenReturnLogoutFail() throws Exception {
		TokenRequest loginReq = new TokenRequest();
		LogoutResponse loginRes = new LogoutResponse();
		loginRes.setMessage("Log out Fail!!");
		Mono<TokenRequest> monoReq = Mono.just(loginReq);
		when(loginService.logout(Mockito.any())).thenReturn(new ResponseEntity<>(loginRes, HttpStatus.BAD_REQUEST));
		webTestClient.post().uri("/auth/logout").contentType(MediaType.APPLICATION_JSON)
		.body(monoReq, LoginRequest.class).exchange().expectStatus().is2xxSuccessful().expectHeader()
		.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$.message").isEqualTo("Log out Fail!!");
	}
}
