package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.apirest.dao.JefeDAO;
import com.formacion.app.apirest.entity.Jefe;

@Service
public class JefeServiceImpl implements JefeService {

	@Autowired
	JefeDAO jefeDAO;
	

	@Transactional(readOnly = true)
	@Override
	public List<Jefe> getJefe() {
		return (List<Jefe>) this.jefeDAO.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Jefe getJefe(long id) {
		return this.jefeDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Jefe putJefe(Jefe jefe, long id) {
		Jefe toUpdateCliente = getJefe(id);

		if (toUpdateCliente == null)
			return null;

		toUpdateCliente.setNombre(jefe.getNombre());
		toUpdateCliente.setDni(jefe.getDni());
		toUpdateCliente.setSalario(jefe.getSalario());
		toUpdateCliente.setTelefono(jefe.getTelefono());
		
		return this.jefeDAO.save(toUpdateCliente);

	}
	
	@Transactional
	@Override
	public Jefe postJefe(Jefe jefe) {
		return this.jefeDAO.save(jefe);
	}

	@Transactional
	@Override
	public Jefe deleteJefe(long id) {
		Jefe deletedCliente = this.jefeDAO.findById(id).orElse(null);
		this.jefeDAO.deleteById(id);
		return deletedCliente;
	}


}
