package com.formacion.app.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="departamentos")
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codDepartamento;
	
	
	@Column(nullable = false)
	private String nombre,ubicacion;


	public long getCodDepartamento() {
		return codDepartamento;
	}


	public void setCodDepartamento(long codDepartamento) {
		this.codDepartamento = codDepartamento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="cod_cliente")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Jefe jefe;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="cod_articulo")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Empleado empleado;
//	
//	
	

	