package com.warehouse.domain.planillas;

import java.time.LocalDate;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/*@Entity
@Table(name = "reporte_asistencia")*/
@Entity
@Immutable // Tells Hibernate the source is read-only
@Table(name = "reporte_asistencia") // Name of your SQL view

public class ReporteAsistencia {
	
	
	/*
	 * @Id
	 * 
	 * @Column(name = "id_reporte")
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int idReporte;
	 */
	
	@Column(name = "id_empleado")
	@Id	
	private int idEmpleado;
	
		/*
		 * @Column(name="fecha_elaboracion")
		 * 
		 * @DateTimeFormat(pattern="YYYY-MM-dd")
		 * 
		 * @Temporal(TemporalType.DATE)
		 */
		/*
		 * @Column(name="fecha_elaboracion") private LocalDate fecElaboracion;
		 */
	
	@Column(name="abreviatura") 
	private String	abreviatura	= "";   
	
	@Column(name="num_identificacion") 
	private String	numId	= "";   
	
	
	@Column(name="nombre_completo")
    private String 	nomCompleto; 

	
	@Column(name="dias_trabajados")
    private int 	diasTrabajados; 

	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	


	public String getNumId() {
		return numId;
	}


	public void setNumId(String numId) {
		this.numId = numId;
	}


	public String getNomCompleto() {
		return nomCompleto;
	}

	

	public String getAbreviatura() {
		return abreviatura;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}


	


	


	public int getDiasTrabajadas() {
		return diasTrabajados;
	}


	public void setDiasTrabajadas(int diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}
	
	
	
}
