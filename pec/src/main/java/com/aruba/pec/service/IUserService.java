package com.aruba.pec.service;

import java.util.List;

import com.aruba.pec.dao.entities.Message;
import com.aruba.pec.dao.entities.User;
import com.aruba.pec.service.exceptions.ResourceNotFoundException;

public interface IUserService {	
	User findUserById(String id) throws ResourceNotFoundException;
	List<Message> searchMessagesBySender(String idUtente, String pecName, String sender, String text, String subject,String priority,boolean hasAttach)  throws ResourceNotFoundException ;
	String getPrivateKey(String id) throws ResourceNotFoundException;
	User findUserByEmail(String email) throws ResourceNotFoundException;
}

