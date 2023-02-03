package com.aruba.pec.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruba.pec.controller.dto.UserWebRequest;
import com.aruba.pec.controller.dto.WebRequestPec;
import com.aruba.pec.controller.dto.WebRequestPecFiltered;
import com.aruba.pec.dao.entities.Message;
import com.aruba.pec.dao.entities.Pec;
import com.aruba.pec.dao.entities.User;
import com.aruba.pec.service.IPecService;
import com.aruba.pec.service.IUserService;
import com.aruba.pec.service.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/pec/")
@CrossOrigin(origins = "http://localhost:4200")
public class PecController {
	@Autowired
	private IPecService pecService;
	@Autowired
	private IUserService userService;

	@GetMapping("/get-all")
	public @ResponseBody ResponseEntity<List<Pec>> getAllPecs() {
		return new ResponseEntity<>(pecService.findAll(), HttpStatus.OK);
	}
	@PostMapping("/get-user-by-email")
	public @ResponseBody ResponseEntity<User> getUserByEmail(@Valid @RequestBody UserWebRequest userRequest) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.findUserByEmail(userRequest.getEmail()), HttpStatus.OK);
	}
	
	@PostMapping("/get-user-by-id-aruba")
	public @ResponseBody ResponseEntity<User> getPecByUser(@Valid @RequestBody  WebRequestPec request) throws ResourceNotFoundException  {
		return new ResponseEntity<>(userService.findUserById(request.getIdUtente()), HttpStatus.OK);
	}
	@PostMapping("/get-messages-by-filter")
	public @ResponseBody ResponseEntity<List<Message>> getMessagesFiltered(@Valid @RequestBody  WebRequestPecFiltered request) throws ResourceNotFoundException  {
		return new ResponseEntity<>(userService.searchMessagesBySender(request.getIdUtente(), request.getPecName(), request.getSender(), request.getText(), request.getSubject(), request.getPriority(),request.isHasAttach()), HttpStatus.OK);
	}
	
}
