package com.aruba.pec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruba.pec.dao.entities.Pec;
import com.aruba.pec.repositories.PecRepository;
@Service
public class PecService implements IPecService{

	@Autowired
	private PecRepository repository;
	
	
	@Override
	public List<Pec> findAll() {
		return (List<Pec>) repository.findAll();
	}

}
