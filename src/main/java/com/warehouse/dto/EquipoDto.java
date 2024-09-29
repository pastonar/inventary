package com.warehouse.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.warehouse.domain.Bodega;
import com.warehouse.domain.EstadoEquipo;
import com.warehouse.domain.PeriodoCalibracion;
import com.warehouse.domain.PrestamoEquipo;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

public class EquipoDto {


	
	private Long id;
	
	private String descripcion;
	
	private String codigoInterno;
	
	private String marca;

	private String estado;
	
	private LocalDateTime fechaCompra;
	
    private double  valorInventario;
	
	private int tiempocalibracion;
	
	private LocalDateTime fechacalibracion;
	
	private String periodoCalibracion;
	
	private String bodega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getValorInventario() {
		return valorInventario;
	}

	public void setValorInventario(double valorInventario) {
		this.valorInventario = valorInventario;
	}

	public int getTiempocalibracion() {
		return tiempocalibracion;
	}

	public void setTiempocalibracion(int tiempocalibracion) {
		this.tiempocalibracion = tiempocalibracion;
	}

	public LocalDateTime getFechacalibracion() {
		return fechacalibracion;
	}

	public void setFechacalibracion(LocalDateTime fechacalibracion) {
		this.fechacalibracion = fechacalibracion;
	}

	public String getPeriodoCalibracion() {
		return periodoCalibracion;
	}

	public void setPeriodoCalibracion(String periodoCalibracion) {
		this.periodoCalibracion = periodoCalibracion;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	
	

}
