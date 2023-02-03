package com.aruba.firma.service;

import java.io.IOException;

import com.aruba.firma.service.dto.ServiceFirmaRequest;
import com.aruba.firma.service.exception.ServiceException;
import com.aruba.firma.service.pojo.ResponseConservazioneDocumenti;

public interface ISignService {
	ResponseConservazioneDocumenti execute(ServiceFirmaRequest request)  throws IOException, ServiceException;
}
