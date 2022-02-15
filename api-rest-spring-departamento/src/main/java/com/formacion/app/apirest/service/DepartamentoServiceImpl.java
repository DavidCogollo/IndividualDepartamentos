package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.apirest.dao.DepartamentoDAO;
import com.formacion.app.apirest.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	DepartamentoDAO departamentoDAO;

	@Transactional(readOnly=true)
	@Override
	public List<Departamento> getDepartamento() {
		return (List<Departamento>) this.departamentoDAO.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Departamento getDepartamento(long id) {
		return this.departamentoDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Departamento postDepartamento(Departamento departamento) {
		return this.departamentoDAO.save(departamento);
	}

	
	@Transactional
	@Override
	public Departamento putDepartamento(Departamento departamento, long id) {
		Departamento toUpdateCompra = getDepartamento(id);
		
		if (toUpdateCompra==null) return null;
		
		toUpdateCompra.setNombre(departamento.getNombre());
		toUpdateCompra.setUbicacion(departamento.getUbicacion());
		return this.departamentoDAO.save(toUpdateCompra);
	}
	

	@Transactional
	@Override
	public Departamento deleteDepartamento(long id) {
		Departamento deletedCompra = this.departamentoDAO.findById(id).orElse(null);
		this.departamentoDAO.deleteById(id);
		return deletedCompra;
	}
}
