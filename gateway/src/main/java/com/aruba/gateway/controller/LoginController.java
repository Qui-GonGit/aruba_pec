package com.aruba.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruba.gateway.model.LoginRequest;
import com.aruba.gateway.model.LoginResponse;
import com.aruba.gateway.model.LogoutResponse;
import com.aruba.gateway.model.TokenRequest;
import com.aruba.gateway.services.LogingService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	LogingService loggingService;
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginrequest){
		return new ResponseEntity<>(loggingService.login(loginrequest).getBody(), HttpStatus.OK);
	}
	@PostMapping("/logout")
	public ResponseEntity<LogoutResponse> login (@RequestBody TokenRequest request){
		return new ResponseEntity<>(loggingService.logout(request).getBody(), HttpStatus.OK);
	}
}
