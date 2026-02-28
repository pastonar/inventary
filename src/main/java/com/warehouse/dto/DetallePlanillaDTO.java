package com.warehouse.dto; 

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.warehouse.domain.clientes.Cliente;

@Entity
@Table(name = "detalle_planillas")

public class DetallePlanillaDTO implements Cloneable
{
	@Id
	@Column(name = "ID_DETALLE_PLANILLA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetallePlanilla;
	
	@Column(name = "id_planilla")
	private int		idPlanilla 	= 0;
	
	@Column(name = "horas_trabajadas")
	private LocalTime horasTrabajadas;
	
	@Column(name = "id_empleado")
	private int idEmpleado;
	
	@Column(name = "hora_entrada")
	private LocalTime		horaEntrada;
	
	@Column(name = "hora_salida")
	private LocalTime		horaSalida;
	
	@Column(name = "observaciones")
	private String		observaciones;
	
	@Transient
	private boolean selected;
	
	
	public DetallePlanillaDTO()
	  {
		this.idDetallePlanilla				= 0;
		this.horaEntrada = LocalTime.now();
		this.horaSalida  = LocalTime.now();
		this.observaciones = "Por el grupo";
	  }


	public int getIdDetallePlanilla() {
		return idDetallePlanilla;
	}


	public void setIdDetallePlanilla(int idDetallePlanilla) {
		this.idDetallePlanilla = idDetallePlanilla;
	}



	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}


	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}


	public LocalTime getHoraSalida() {
		
	
		return horaSalida;
	}


	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
		//tiempoTotal(this);
	}

	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

	public String horaEntradaFormateada()
	{
	return this.horaEntrada.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
	public LocalTime horaSalidaFormateada()
	{
	return this.horaSalida;
	//.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public int getIdPlanilla() {
		return idPlanilla;
	}


	public void setIdPlanilla(int idPlanilla) {
		this.idPlanilla = idPlanilla;
	}

	
	public LocalTime getHorasTrabajadas() {
		
		return horasTrabajadas;
	}


	public void setHorasTrabajadas(LocalTime horasTrabajadas) {
		
		this.horasTrabajadas = horasTrabajadas;
	}


	
	
	/*
	 * public static void tiempoTotal(DetallePlanillaDTO detalleplanilla){ LocalTime
	 * timeIn = detalleplanilla.horaEntrada; LocalTime timeOut =
	 * detalleplanilla.horaSalida; Duration duracion; duracion=
	 * Duration.between(timeIn, timeOut); detalleplanilla.horasTrabajadas =
	 * LocalTime.of(duracion.toHoursPart(), duracion.toMinutesPart(),
	 * duracion.toSecondsPart()); }
	 */

	
	
	public static String format1(Duration duration) {
	    return String.format("%02d:%02d:%02d", duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
	}

}