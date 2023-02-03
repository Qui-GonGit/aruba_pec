package com.aruba.firma.service;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruba.firma.service.dto.ServiceFirmaRequest;
import com.aruba.firma.service.exception.ServiceException;
import com.aruba.firma.service.exception.SignException;
import com.aruba.firma.service.pojo.ResponseConservazioneDocumenti;
import com.aruba.firma.service.pojo.User;

@Service
public class SignService implements ISignService {

	@Autowired
	ConservazioneDocumentiService documentiService;
	@Autowired
	CreateSignature sign;
	@Autowired
	UserService service;
	
	@Override
	public ResponseConservazioneDocumenti execute(ServiceFirmaRequest request)
			throws IOException, ServiceException {
		User user = service.getUtenteById(request.getIdUtente());
		try {
			
			return documentiService.sendToRepository(firmaDocumento(user));
		} catch (SignException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private String firmaDocumento(User user) throws SignException {
		return sign.signDetached(user);
	}

}
