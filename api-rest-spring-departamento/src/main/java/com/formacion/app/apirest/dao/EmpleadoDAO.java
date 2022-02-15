package com.formacion.app.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.app.apirest.entity.Empleado;

@Repository
public interface EmpleadoDAO extends CrudRepository<Empleado,Long> {

}
