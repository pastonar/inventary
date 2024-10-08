package com.warehouse.domain.productos;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import com.warehouse.domain.productos.EstadoProducto;
import com.warehouse.domain.productos.Presentacion;
import com.warehouse.domain.productos.Unidad;

@Entity
@Table(name = "productos")
public class Producto implements Cloneable{

	@Id
	@Column(name = "ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id_producto 		= 0;
	
	@Column(name="DESCRIPCION")
	private String	descripcion	= ""; 
    
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_PRODUCTO" )
	private EstadoProducto	estadoProducto;
	
	@Column(name="EXISTENCIAS")
	private double 	existencias	= 0;
	
	@Column(name="CANTIDAD")
	private double 	cantidad	= 0;

	@Column(name="COSTO_UNIDAD")
	private double 	costoUnidad	= 0;

	@Column(name="PRECIO_PRESENTACION")
	private double 	precioPresentacion	= 0;

	@Column(name="PRECIO_UNIDAD")
	private double 	precioUnidad	= 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_UNIDAD")
	private Unidad unidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PRESENTACION")
	private Presentacion presentacion;
	
		
	@Override
	public String toString() {
		return  descripcion + " x "+presentacion+" de "+ cantidad + unidad;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EstadoProducto getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(EstadoProducto estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getExistencias() {
		return existencias;
	}

	public void setExistencias(double existencias) {
		this.existencias = existencias;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(double costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public double getPrecioPresentacion() {
		return precioPresentacion;
	}

	public void setPrecioPresentacion(double precioPresentacion) {
		this.precioPresentacion = precioPresentacion;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_producto;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id_producto != other.id_producto)
			return false;
		return true;
	}
	

	
}
