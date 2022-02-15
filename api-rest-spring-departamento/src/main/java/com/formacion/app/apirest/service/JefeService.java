package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formacion.app.apirest.entity.Jefe;

@Service
public interface JefeService {

	List<Jefe> getJefe();

	Jefe getJefe(long id);

	Jefe postJefe(Jefe jefe);
	
	
	Jefe putJefe(Jefe jefe, long id);
	
	
	Jefe deleteJefe(long id);
}
