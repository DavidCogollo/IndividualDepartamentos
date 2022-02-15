package com.formacion.app.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.apirest.entity.Departamento;
import com.formacion.app.apirest.service.DepartamentoServiceImpl;


@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	DepartamentoServiceImpl DepartamentoServiceImpl;
	
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Departamento>> getAllClientes() {
		return new ResponseEntity<>(DepartamentoServiceImpl.getDepartamento(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Long id) {
		Departamento jefe = null;
		Map<String, Object> response = new HashMap<>();
		try {
			jefe = DepartamentoServiceImpl.getDepartamento(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (jefe == null) {
			response.put("mensaje", "El cliente ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departamento>(jefe, HttpStatus.OK);

	}
	
	@PostMapping("")
	public ResponseEntity<?> postCliente(@RequestBody Departamento jefe) {
		Departamento newCliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newCliente = DepartamentoServiceImpl.postDepartamento(jefe);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (newCliente == null) {
			response.put("mensaje", "El departamento no se ha guardado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("cliente", newCliente);
		response.put("mensaje", "Se ha guardado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	
	
	

	
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putCliente(@RequestBody Departamento jefe, @PathVariable long id) {
		Departamento editCliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			editCliente = DepartamentoServiceImpl.putDepartamento(jefe, id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al editar al usuario");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (editCliente == null) {
			response.put("mensaje", "No se han hecho cambios para este cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("cliente", editCliente);
		response.put("mensaje", "Se ha editado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable long id) {
		Departamento deleteCliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			deleteCliente = DepartamentoServiceImpl.deleteDepartamento(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			if (deleteCliente == null) {
				response.put("error2", "No existe el usuario: " + id);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			response.put("mensaje", "Error al eliminar al usuario");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("cliente", deleteCliente);
		response.put("mensaje", "Se ha eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
