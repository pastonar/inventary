/**
 * @(#)cliente.java
 *
 *
 * @juan carlos pazos 
 * @version 1.00 2012/8/16
 */

package com.warehouse.domain.planillas; 



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import com.warehouse.domain.planillas.DetallePlanilla;
import com.warehouse.domain.ventas.DetalleVentas;

/*import java.util.Date;
import java.util.List;*/
import java.time.LocalDate;
   
@Entity
@Table(name = "planillas")

@NamedEntityGraph(name="asistentesEntityGraph", attributeNodes={
		@NamedAttributeNode("asistentes")})


	public class Planilla implements Cloneable
	{
  
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
	
	@OneToMany(fetch = FetchType.EAGER )
	@JoinColumn(name = "ID_PLANILLA")
	private List<DetallePlanilla> asistentes = new ArrayList<DetallePlanilla>();
	
	@Column(name="responsable" )
	private String responsable;

	public Planilla()
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

	public List<DetallePlanilla> getAsistentes() {
		return asistentes;
	}


	public void setAsistentes(List<DetallePlanilla> asistentes) {
		this.asistentes = asistentes;
	}

	public void addPersonal(DetallePlanilla detalleplanilla)
	{
		this.asistentes.add(detalleplanilla);
		this.totalAsistentes+=1;
	}
	
	public void removePersonal(DetallePlanilla detalleplanilla)
	{
		this.asistentes.remove(detalleplanilla);
		this.totalAsistentes-=1;
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



	
    }
