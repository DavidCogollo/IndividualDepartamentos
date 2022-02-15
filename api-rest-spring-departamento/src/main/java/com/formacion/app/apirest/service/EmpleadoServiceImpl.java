package com.formacion.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.app.apirest.dao.EmpleadoDAO;
import com.formacion.app.apirest.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoDAO empleadoDAO;
	
	@Transactional(readOnly=true)
	@Override
	public List<Empleado> getEmpleados() {
		return (List<Empleado>) this.empleadoDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public Empleado getEmpleado(long id) {
		return this.empleadoDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Empleado postEmpleado(Empleado empleado) {
		return this.empleadoDAO.save(empleado);
	}
	
		
	
	
	
	@Transactional
	@Override
	public Empleado putEmpleado(Empleado empleado, long id) {
		Empleado toUpdateEmpleado = getEmpleado(id);
		
		if (toUpdateEmpleado==null) return null;
		

		toUpdateEmpleado.setNombre(empleado.getNombre());
		toUpdateEmpleado.setDni(empleado.getDni());
		toUpdateEmpleado.setSalario(empleado.getSalario());
		toUpdateEmpleado.setTelefono(empleado.getTelefono());
		return this.empleadoDAO.save(toUpdateEmpleado);
	}	

	@Transactional
	@Override
	public Empleado deleteEmpleado(long id) {
		Empleado deletedEmpleado = this.empleadoDAO.findById(id).orElse(null);
		this.empleadoDAO.deleteById(id);
		return deletedEmpleado;
	}


	}

