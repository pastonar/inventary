package com.warehouse.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.warehouse.domain.planillas.EstadoPlanilla;
//import com.warehouse.domain.planillas.detallePlanilla;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "planillas")

public class PlanillaDTO {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_planilla")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlanilla;
	
	
	
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="fecha_elaboracion")
	@DateTimeFormat(pattern="YYYY-MM-dd")
	@Temporal(TemporalType.DATE) 
	
	private LocalDate fecElaboracion;
	
	@Column(name="total_asistentes")
	   private int totalAsistentes;

	//@Enumerated(EnumType.STRING)
	@Column(name="estado" )
	private int estado;
	
	@Column(name="responsable" )
	private String responsable;
	
	public PlanillaDTO()
  	{
	  this.fecElaboracion =LocalDate.now();
	  this.idPlanilla = 0;
	  this.estado = 0; 
	  this.totalAsistentes =0;
  	}


	public int getIdPlanilla() {
		return idPlanilla;
	}


	public void setIdPlanilla(int idPlanilla) {
		this.idPlanilla = idPlanilla;
	}


	

	public LocalDate getFecElaboracion() {
		return fecElaboracion;
	}


	public void setFecElaboracion(LocalDate fecElaboracion) {
		this.fecElaboracion = fecElaboracion;
	}



	public int getTotalAsistentes() {
		return totalAsistentes;
	}


	public void setTotalAsistentes(int totalAsistentes) {
		this.totalAsistentes = totalAsistentes;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
public boolean isSaved()
{
	return this.idPlanilla > 0;
}


public String getResponsable() {
	return responsable;
}


public void setResponsable(String responsable) {
	this.responsable = responsable;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}


@Override
public String toString() {
	return "PlanillaDTO [idPlanilla=" + idPlanilla + ", fecElaboracion=" + fecElaboracion + ", totalAsistentes="
			+ totalAsistentes + ", estado=" + estado + ", responsable=" + responsable + "]";
}




}
