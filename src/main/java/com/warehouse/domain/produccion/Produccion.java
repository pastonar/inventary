/**
 * @(#)cliente.java
 *
 *
 * @juan carlos pazos 
 * @version 1.00 2012/8/16
 */

package com.warehouse.domain.produccion; 



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
@Table(name = "produccion")

	public class Produccion implements Cloneable
	{
  
	@Id
	@Column(name = "id_produccion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduccion;
	
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="fecha_elaboracion")
	@DateTimeFormat(pattern="YYYY-MM-dd")
	@Temporal(TemporalType.DATE) 
	
	private LocalDate fecRegistro;
	
	@Column(name="kilos_mina")
	   private double kilosMina;
	
	@Column(name="kilos_esteril")
	   private int kilosEsteril;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empleado")
	private Empleado responsable;
	
	@Column(name="frenteTrabajo" )
	private String frenteTrabajo;
	

	public Produccion()
  	{
	  this.fecRegistro =LocalDate.now();
	  this.idProduccion = 0;
	  
  	}


public int getIdProduccion() {
		return idProduccion;
	}

	public void setIdProduccion(int idProduccion) {
		this.idProduccion = idProduccion;
	}

	public LocalDate getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(LocalDate fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	public double getKilosMina() {
		return kilosMina;
	}


	public void setKilosMina(double kilosMina) {
		this.kilosMina = kilosMina;
	}


	public int getKilosEsteril() {
		return kilosEsteril;
	}


	public void setKilosEsteril(int kilosEsteril) {
		this.kilosEsteril = kilosEsteril;
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
