package com.aruba.pec.service;

import java.util.List;

import com.aruba.pec.dao.entities.Pec;

public interface IPecService {	
	List<Pec> findAll();
}
