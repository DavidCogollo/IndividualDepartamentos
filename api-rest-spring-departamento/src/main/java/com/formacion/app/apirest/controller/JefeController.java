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
import com.formacion.app.apirest.entity.Jefe;
import com.formacion.app.apirest.service.DepartamentoServiceImpl;
import com.formacion.app.apirest.service.JefeServiceImpl;

@RestController
@RequestMapping("/jefes")
public class JefeController {
	
	@Autowired
	JefeServiceImpl jefeServiceImpl;

	
	@GetMapping("/all")
	public ResponseEntity<List<Jefe>> getAllClientes() {
		return ( new ResponseEntity<>(jefeServiceImpl.getJefe(), HttpStatus.OK));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getJefe(@PathVariable Long id) {
		Jefe departamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			departamento = jefeServiceImpl.getJefe(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (departamento == null) {
			response.put("mensaje", "El cliente ID: " + id.toString() + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Jefe>(departamento, HttpStatus.OK);

	}	
	
	@PostMapping("")
	public ResponseEntity<?> postJefe(@RequestBody Jefe departamento) {
		Jefe newCompra = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newCompra = jefeServiceImpl.postJefe(departamento);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (newCompra == null) {
			response.put("mensaje"," no se ha guardado en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("empleado", newCompra);
		response.put("mensaje", "Se ha guardado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	@PutMapping("/{id}")
	public ResponseEntity<?> putCompra(@RequestBody Jefe jefe, @PathVariable long id) {
		Jefe editCompra = null;
		Map<String, Object> response = new HashMap<>();
		try {
			editCompra = jefeServiceImpl.putJefe(jefe, id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al editar la comrpa");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (editCompra == null) {
			response.put("mensaje", "No se han hecho cambios para esta compra");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
		response.put("compra", editCompra);
		response.put("mensaje", "Se ha editado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCompra(@PathVariable long id) {
		Jefe deleteCompra = null;
		Map<String, Object> response = new HashMap<>();
		try {
			deleteCompra = jefeServiceImpl.deleteJefe(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			if (deleteCompra == null) {
				response.put("error2", "No existe la jefe: " + id);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
			}
			response.put("mensaje", "Error al eliminar la jefe");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("jefe", deleteCompra);
		response.put("mensaje", "Se ha eliminado exitosamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
