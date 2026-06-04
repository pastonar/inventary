/**
 * @(#)cliente.java
 *
 *
 * @juan carlos pazos 
 * @version 1.00 2012/8/16
 */

package com.warehouse.domain.sostenimiento; 



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import com.warehouse.domain.empleados.Empleado;
import com.warehouse.domain.productos.EstadoProducto;

/*import java.util.Date;
import java.util.List;*/
import java.time.LocalDate;
   
@Entity
@Table(name = "sostenimiento")

	public class Sostenimiento implements Cloneable
	{
  
	@Id
	@Column(name = "id_sostenimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSostenimiento;
	
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="fecha_elaboracion")
	@DateTimeFormat(pattern="YYYY-MM-dd")
	@Temporal(TemporalType.DATE) 
	
	private LocalDate fecRegistro;
	
	@Column(name="absisa")
	   private double absisa;
	
	@Column(name="descripcion")
	   private int descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empleado")
	private Empleado responsable;
	
	@Column(name="frenteTrabajo" )
	private String frenteTrabajo;

	public int getIdSostenimiento() {
		return idSostenimiento;
	}

	public void setIdSostenimiento(int idSostenimiento) {
		this.idSostenimiento = idSostenimiento;
	}

	public LocalDate getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(LocalDate fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	public double getAbsisa() {
		return absisa;
	}

	public void setAbsisa(double absisa) {
		this.absisa = absisa;
	}

	public int getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(int descripcion) {
		this.descripcion = descripcion;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}

	public String getFrenteTrabajo() {
		return frenteTrabajo;
	}

	public void setFrenteTrabajo(String frenteTrabajo) {
		this.frenteTrabajo = frenteTrabajo;
	}
	

	





    }
